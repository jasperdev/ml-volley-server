package server;

import java.util.Random;

// These values are roughly the same as those in Blobby Volley.
public class GameProperties {
   private Random rand = new Random(43);
   public long sideWidth = 400000;
   public long netHeight = 200000; // ???

   public long ballRadius = 20000;
   public long ballInitX = sideWidth/2;
   public long ballInitY = netHeight;

   public long playerRadius = 30000;

   public int maxHits = 4;

   public int autoDropFrames = 600;

   public long getBallInitX() {
      return rand.nextInt((int)sideWidth/2) + sideWidth/4;
   }
}
