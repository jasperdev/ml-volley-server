public class FacadeGameState implements GameStateInterface {
   public PhysicsObject lPlayer, rPlayer, ball;
   public int lPlayerScore, lPlayerHits;
   public int rPlayerScore, rPlayerHits;

   public FacadeGameState(GameStateInterface source, boolean flip) {
      if (!flip) {
         lPlayer = source.getLPlayer().copy();
         rPlayer = source.getRPlayer().copy();
         ball = source.getBall().copy();
         lPlayerScore = source.getLPlayerScore();
         lPlayerHits = source.getLPlayerHits();
         rPlayerScore = source.getRPlayerScore();
         rPlayerHits = source.getRPlayerHits();
      } else {
         lPlayer = source.getLPlayer().flipX();
         rPlayer = source.getRPlayer().flipX();
         ball = source.getBall().flipX();
         lPlayerScore = source.getRPlayerScore();
         lPlayerHits = source.getRPlayerHits();
         rPlayerScore = source.getLPlayerScore();
         rPlayerHits = source.getLPlayerHits();
      }
   }

   @Override
   public PhysicsObjectInterface getLPlayer() { return lPlayer; }
   @Override
   public int getLPlayerScore() { return lPlayerScore; }
   @Override
   public int getLPlayerHits() { return lPlayerHits; }

   @Override
   public PhysicsObjectInterface getRPlayer() { return rPlayer; }
   @Override
   public int getRPlayerScore() { return rPlayerScore; }
   @Override
   public int getRPlayerHits() { return rPlayerHits; }

   @Override
   public PhysicsObjectInterface getBall() { return ball; }
}
