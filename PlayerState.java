public class PlayerState {
   public enum Side { 
      LEFT(-1),
      RIGHT(1);

      private int scale;
      Side(int scale) { this.scale = scale; }
      public long translateX(long x) { return scale*x; }
   }

   public PhysicsProperties physics;
   public GameProperties game;
   public Side side;

   public long sideWidth, playerRadius;
   public boolean playerBounce;

   public PhysicsObject pCircle = new PhysicsObject();

   public PlayerState(Side side_, GameProperties gameProps, PhysicsProperties physProps) {
      physics = physProps;
      game = gameProps;
      side = side_;

      sideWidth = game.sideWidth;
      playerRadius = game.playerRadius;
      playerBounce = game.playerBounce;

      reset();
   }

   public void reset() {
      pCircle.posX = side.translateX(sideWidth/2);
      pCircle.posY = playerRadius;
   }

   public void step(PlayerInput input) {
      if (input.up) {
         if (posY == 0) {
            pCircle.velY = phsyics.playerJumpVelocity;
         }
         pCircle.accY = -physics.playerReducedGravity;
      } else {
         pCircle.accY = -physics.playerGravity;
      }

      pCircle.velX = (input.right ? phsyics.playerHorizontalSpeed : 0) -
                  (input.left  ? physics.playerHorizontalSpeed : 0);

      pCircle.step();

      pCircle.collideHorzPlane(0, playerBounce);
      pCircle.collideVertPlane(0, false);
      pCircle.collideVertPlane(-sideWidth, false);
      pCircle.collideVertPlane(sideWidth, false);
   }
}
