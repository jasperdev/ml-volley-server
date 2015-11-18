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

   public PlayerInputProvider input;
   
   public long sideWidth, playerRadius;
   public boolean playerBounce;

   public PhysicsCircle pCircle;

   public PlayerState(Side side_, PlayerInputProvider input_, GameProperties gameProps, PhysicsProperties physProps) {
      physics = physProps;
      game = gameProps;
      side = side_;
      
      input = input_;

      sideWidth = game.sideWidth;
      playerBounce = physics.playerBounce;

      pCircle = new PhysicsCircle(game.playerRadius);

      reset();
   }

   public void reset() {
      pCircle.posX = side.translateX(sideWidth/2);
      pCircle.posY = pCircle.radius;
      pCircle.velX = pCircle.velY = 0;
   }

   public void step() {
      PlayerInput in = input.getInput();
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

      pCircle.collideFloor(0, playerBounce);
      pCircle.collideVertPlane(0, false);
      pCircle.collideVertPlane(-sideWidth, false);
      pCircle.collideVertPlane(sideWidth, false);
   }
}
