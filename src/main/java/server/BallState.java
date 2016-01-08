package server;
import physics.PhysicsCircle;

public class BallState {
   public PhysicsProperties physics;
   public GameProperties game;

   public long initX, initY;
   public long minX, maxX, netHeight;
   public boolean firstHit = true;
   public boolean hitGround = false;
   public Side side;

   public int frameCount;

   public PhysicsCircle pCircle;

   public BallState(GameProperties gameProps, PhysicsProperties physProps) {
      physics = physProps;
      game = gameProps;

      //initX = game.ballInitX;
      initX = game.ballInitX;
      initY = game.ballInitY;
      minX = -game.sideWidth;
      maxX = game.sideWidth;
      netHeight = game.netHeight;

      pCircle = new PhysicsCircle(game.ballRadius);
      pCircle.accY = -physics.ballGravity;
      side = Side.RIGHT;

      reset();
   }

   public void reset() {
      //pCircle.posX = side.translateX(game.getBallInitX());
      pCircle.posX = side.translateX(initX);
      pCircle.posY = initY;
      pCircle.velX = pCircle.velY = 0;
      frameCount = game.autoDropFrames;
      firstHit = true;
      hitGround = false;
   }

   public void step() {
      if (frameCount > 0) {
         frameCount--;
         if (firstHit) {
            return;
         }
      }
      long prevX = pCircle.posX;

      pCircle.step();

      hitGround |= pCircle.collideFloor(0, true);
      pCircle.collideLeftWall(minX, true);
      pCircle.collideRightWall(maxX, true);
      if (pCircle.posY <= netHeight) {
         if (prevX < 0) {
            pCircle.collideRightWall(0, true);
         } else {
            pCircle.collideLeftWall(0, true);
         }
      } else {
         pCircle.collideCircle(0, netHeight, 0, 0);
      }
   }
}
