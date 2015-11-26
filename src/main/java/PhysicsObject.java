public class PhysicsObject implements PhysicsObjectInterface {
   long posX = 0, posY = 0;
   long velX = 0, velY = 0;
   long accX = 0, accY = 0;

   public PhysicsObject() {}

   public PhysicsObject(long x, long y) {
      posX = x;
      posY = y;
   }

   public PhysicsObject(PhysicsObject o) {
      posX = o.posX;
      posY = o.posY;
      velX = o.velX;
      velY = o.velY;
      accX = o.accX;
      accY = o.accY;
   }

   public void step() {
      // dx = a/2 * dt^2 + v * dt, dt = 1
      posX += accX/2 + velX;
      posY += accY/2 + velY;
      // dv = a * dt
      velX += accX;
      velY += accY;
   }

   @Override
   public PhysicsObject copy() {
      return new PhysicsObject(this);
   }

   @Override
   public PhysicsObject flipX() {
      PhysicsObject out = copy();
      out.posX *= -1;
      out.velX *= -1;
      out.accX *= -1;
      return out;
   }

   @Override
   public long getPosX() { return posX; }
   @Override
   public long getVelX() { return velX; }
   @Override
   public long getPosY() { return posY; }
   @Override
   public long getVelY() { return velY; }
}
