package server;

import java.util.Random;

// These values are roughly the same as those in Blobby Volley.
public class GameProperties {
   //private Random rand = new Random(1231235);
   private Random rand = new Random(1231236);
   public long sideWidth = 400000;
   public long netHeight = 200000; // ???

   public long ballRadius = 20000;
   public long ballInitX = sideWidth/2;
   public long ballInitY = netHeight;

   public long playerRadius = 30000;

   public int maxHits = 2;

   public int autoDropFrames = 200;

   public void setRandomSeed(long seed) {rand.setSeed(seed);};
   public long getBallInitX() {
      return -(long)(rand.nextInt((int)(sideWidth * .5)) + sideWidth *.25);
   };
   public long getBallInitY() {
      return (long)(rand.nextInt((int)(netHeight)) + netHeight *0.5d);
   };
   public void setBallInitX(long x) {ballInitX = x;};
}
