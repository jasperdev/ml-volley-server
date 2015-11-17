public class PhysicsCircle extends PhysicsObject {
   public long radius;

   public PhysicsCircle(long radius_) {
      radius = radius_;
   }

   public PhysicsCircle(long x, long y, long radius_) {
      super(x,y);
      radius = radius_;
   }

   public boolean collideVertPlane(long x, boolean elastic) {
      long dx = posX - x;
      if (Math.abs(dx) > radius) {
         return false;
      }

      posX = x + (dx > 0 ? radius : -radius);
      velX = elastic ? -velX : 0;

      return true;
   }

   public boolean collideHorzPlane(long y, boolean elastic) {
      long dy = posY - y;
      if (Math.abs(dy) > radius) {
         return false;
      }

      posY = y + (dy > 0 ? radius : -radius);
      velY = elastic ? -velY : 0;

      return true;
   }

   public boolean collideCircle(long x, long y, long r, long goalVel) {
      //TODO
      return false;
   }

   public boolean collideCircle(PhysicsCircle o, long goalVel) {
      return collideCircle(o.posX, o.posY, o.radius, goalVel);
   }
}
