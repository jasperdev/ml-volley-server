package physics;
public class PhysicsCircle extends PhysicsObject {
   public long radius;

   public PhysicsCircle(long radius_) {
      radius = radius_;
   }

   public PhysicsCircle(long x, long y, long radius_) {
      super(x,y);
      radius = radius_;
   }

   public boolean collideLeftWall(long x, boolean elastic) {
      if (posX - radius > x) {
         return false;
      }

      System.out.println("Here "+posX+" "+velX);
      if (!elastic) {
         posX = x + radius;
         velX = 0;
      } else {
         posX = 2*(x+radius) - posX;
         if (velX < 0) {
            velX *= -1;
         }
      }
      System.out.println("Here After "+posX+" "+velX);

      return true;
   }

   public boolean collideRightWall(long x, boolean elastic) {
      if (posX + radius < x) {
         return false;
      }

      if (!elastic) {
         posX = x - radius;
         velX = 0;
      } else {
         posX = 2*(x-radius) - posX;
         if (velX > 0) {
            velX *= -1;
         }
      }

      return true;
   }

   public boolean collideFloor(long y, boolean elastic) {
      if (posY > y + radius) {
         return false;
      }

      if (!elastic) {
         posY = y + radius;
      }
      velY = elastic ? -velY : 0;

      return true;
   }

   public boolean collideVertPlane(long x, boolean elastic) {
      long dx = posX - x;
      if (Math.abs(dx) > radius) {
         return false;
      }

      if (!elastic) {
         posX = x + (dx > 0 ? radius : -radius);
      }
      velX = elastic ? -velX : 0;

      return true;
   }

   public boolean collideHorzPlane(long y, boolean elastic) {
      long dy = posY - y;
      if (Math.abs(dy) > radius) {
         return false;
      }

      if (!elastic) {
         posY = y + (dy > 0 ? radius : -radius);
      }
      velY = elastic ? -velY : 0;

      return true;
   }

   public boolean collideCircle(long x, long y, long r, long goalSpeed) {
      long dx = posX-x, dy = posY-y;
      if (dx*dx + dy*dy > (r+radius)*(r+radius)) {
         return false;
      }
      if (goalSpeed == 0) {
         goalSpeed = Math.round(Math.sqrt(velX*velX + velY*velY));
      }
      
      
      //TODO Is this good enough?
      double scaleFactor = goalSpeed / Math.sqrt(dx*dx + dy*dy);
      velX = Math.round(scaleFactor * dx);
      velY = Math.round(scaleFactor * dy);

      while (((posX-x)*(posX-x) + (posY-y)*(posY-y)) <= (r+radius)*(r+radius)) {
         step();
      }

      return true;
   }

   public boolean collideCircle(PhysicsCircle o, long goalSpeed) {
      return collideCircle(o.posX, o.posY, o.radius, goalSpeed);
   }
}
