package impl;

public class PhysicsObject {
   public long posX = 0, posY = 0;
   public long velX = 0, velY = 0;
   public long accX = 0, accY = 0;

   public PhysicsObject() {}

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
