public class PhysicsProperties {
   // Default gravity that affects the player. This should be positive.
   public long playerGravity;

   // Gravity that affects the player when the Up key is presed. This should be positive.
   public long playerReducedGravity;

   // Initial velocity when a player jumps.
   public long playerJumpVelocity;

   // Horizontal movement speed when a player presses the Left or Right key.
   public long playerHorizontalSpeed;

   // Fun parameter... should players "bounce" off the floor?
   public boolean playerBounce;

   // Gravity that affects the ball. This should be positive.
   public long ballGravity;
}
