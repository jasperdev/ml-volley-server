public class PhysicsCircle extends PhysicsObject {
   public long radius;

   public PhysicsCircle(long radius_) {
      radius = radius_;
   }

   public PhysicsCircle(long x, long y, long radius_) {
      super(x,y);
      radius = radius_;
   }

   public boolean collideFloor(long y, boolean elastic) {
      if (posY > y + radius) {
         return false;
      }

      posY = y + radius;
      velY = elastic ? -velY : 0;

      return true;
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

   public boolean collideCircle(long x, long y, long r, long goalSpeed) {
      long dx = x-posX, dy = y-posY;
      if (dx*dx + dy*dy > r*r) {
         return false;
      }
      if (goalSpeed == 0) {
         goalSpeed = velX*velX + velY*velY;
      }
      
      
      //TODO Is this good enough?
      double scaleFactor = goalSpeed / Math.sqrt(dx*dx + dy*dy);
      velX = Math.round(scaleFactor * dx);
      velY = Math.round(scaleFactor * dy);
      return true;
   }

   public boolean collideCircle(PhysicsCircle o, long goalSpeed) {
      return collideCircle(o.posX, o.posY, o.radius, goalSpeed);
   }
}
