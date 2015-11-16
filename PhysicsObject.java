public class PhysicsObject {
   long posX = 0, posY = 0;
   long velX = 0, velY = 0;
   long accX = 0, accY = 0;

   public PysicsObject() {}

   public PhysicsObject(long x, long y) {
      posX = x;
      posY = y;
   }

   public void step() {
      // dx = a/2 * dt^2 + v * dt, dt = 1
      posX += accX/2 + velX;
      posY += accY/2 + velY;
      // dv = a * dt
      velX += accX;
      velY += accY;
   }
}
