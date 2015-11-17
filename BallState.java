public class BallState {
   public PhysicsProperties physics;
   public GameProperties game;

   public long initX, initY;
   public long minX, maxX, netHeight;
   public boolean firstHit = true;
   public boolean hitGround = false;

   public PhysicsCircle pCircle;

   public BallState(GameProperties gameProps, PhysicsProperties physProps) {
      physics = physProps;
      game = gameProps;

      initX = game.ballInitX;
      initY = game.ballInitY;
      minX = -game.sideWidth;
      maxX = game.sideWidth;
      netHeight = game.netHeight;

      pCircle = new PhysicsCircle(game.ballRadius);
      pCircle.accY = -physics.ballGravity;

      reset();
   }

   public void reset() {
      pCircle.posX = initX;
      pCircle.posY = initY;
      firstHit = true;
      hitGround = false;
   }

   public void step() {
      if (firstHit) {
         return;
      }

      pCircle.step();

      hitGround |= pCircle.collideHorzPlane(0, true);
      pCircle.collideVertPlane(minX, true);
      pCircle.collideVertPlane(maxX, true);
      if (pCircle.posY <= netHeight) {
         pCircle.collideVertPlane(0, true);
      } else {
         pCircle.collideCircle(0, netHeight, 0, 0);
      }
   }
}
