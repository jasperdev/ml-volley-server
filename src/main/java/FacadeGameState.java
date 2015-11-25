public class FacadeGameState implements GameStateInterface {
   public PhysicsObject lPlayer, rPlayer, ball;
   public int lPlayerScore, lPlayerHits;
   public int rPlayerScore, rPlayerHits;

   public FacadeGameState(GameState source, boolean reflect) {
      MatchState m = source.match;
      if (!reflect) {
         lPlayer = m.lPlayer.pCircle.copy();
         rPlayer = m.rPlayer.pCircle.copy();
         ball = m.ball.pCircle.copy();
         lPlayerScore = source.lScore;
         lPlayerHits = source.lHits;
         rPlayerScore = source.rScore;
         rPlayerHits = source.rHits;
      } else {
         lPlayer = m.rPlayer.pCircle.reflectX();
         rPlayer = m.lPlayer.pCircle.reflectX();
         ball = m.ball.pCircle.reflectX();
         lPlayerScore = source.rScore;
         lPlayerHits = source.rHits;
         rPlayerScore = source.lScore;
         rPlayerHits = source.lHits;
      }
   }
   public PhysicsObject getLPlayer()    { return lPlayer; }
   public int getLPlayerScore() { return lPlayerScore; }
   public int getLPlayerHits() { return lPlayerHits; }

   public PhysicsObject getRPlayer()    { return rPlayer; }
   public int getRPlayerScore() { return rPlayerScore; }
   public int getRPlayerHits() { return rPlayerHits; }

   public PhysicsObject getBall()    { return ball; }
}
