package server;
// These values should be roughly the same as those in Blobby Volley.
public class PhysicsProperties {
   public long playerMaxHeight = 250000;

   // Initial velocity when a player jumps.
   public long playerJumpVelocity = 15100;

   // Default gravity that affects the player. This should be positive.
   public long playerGravity = playerJumpVelocity * playerJumpVelocity / playerMaxHeight;

   // Gravity that affects the player when the Up key is presed. This should be positive.
   // TODO: Test this.
   public long playerReducedGravity = playerGravity/2;


   // Horizontal movement speed when a player presses the Left or Right key.
   public long playerHorizontalSpeed = 4500;

   // Gravity that affects the ball. This should be positive.
   public long ballGravity = 287;

   public long playerCollisionVelocity = playerJumpVelocity; // ???
}
