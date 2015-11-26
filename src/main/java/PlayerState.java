public class PlayerState {
   public PhysicsProperties physics;
   public GameProperties game;
   public Side side;

   public long sideWidth, playerRadius;

   public PhysicsCircle pCircle;

   public PlayerState(Side side_, GameProperties gameProps, PhysicsProperties physProps) {
      physics = physProps;
      game = gameProps;
      side = side_;
      
      sideWidth = game.sideWidth;

      pCircle = new PhysicsCircle(game.playerRadius);

      reset();
   }

   public void reset() {
      pCircle.posX = side.translateX(sideWidth/2);
      pCircle.posY = pCircle.radius;
      pCircle.velX = pCircle.velY = 0;
   }

   public void step(PlayerInput in) {
      if (in.up) {
         if (pCircle.velY == 0) {
            pCircle.velY = physics.playerJumpVelocity;
         }
         pCircle.accY = -physics.playerReducedGravity;
      } else {
         pCircle.accY = -physics.playerGravity;
      }

      pCircle.velX = (in.right ? physics.playerHorizontalSpeed : 0) -
                  (in.left  ? physics.playerHorizontalSpeed : 0);

      pCircle.step();

      pCircle.collideFloor(0, false);
      pCircle.collideVertPlane(0, false);
      pCircle.collideVertPlane(-sideWidth, false);
      pCircle.collideVertPlane(sideWidth, false);
   }
}
