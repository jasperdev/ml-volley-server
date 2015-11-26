public class GameStateSnapshot implements GameStateInterface {
   public PhysicsObject me, opponent, ball;
   public int myScore, myHits;
   public int opponentScore, opponentHits;

   public GameStateSnapshot(GameStateInterface source, boolean flip) {
      if (!flip) {
         me = source.getMe().copy();
         opponent = source.getOpponent().copy();
         ball = source.getBall().copy();
         myScore = source.getMyScore();
         myHits = source.getMyHits();
         opponentScore = source.getOpponentScore();
         opponentHits = source.getOpponentHits();
      } else {
         me = source.getOpponent().flippedCopy();
         opponent = source.getMe().flippedCopy();
         ball = source.getBall().flippedCopy();
         myScore = source.getOpponentScore();
         myHits = source.getOpponentHits();
         opponentScore = source.getMyScore();
         opponentHits = source.getMyHits();
      }
   }

   @Override
   public PhysicsObjectInterface getMe() { return me; }
   @Override
   public int getMyScore() { return myScore; }
   @Override
   public int getMyHits() { return myHits; }

   @Override
   public PhysicsObjectInterface getOpponent() { return opponent; }
   @Override
   public int getOpponentScore() { return opponentScore; }
   @Override
   public int getOpponentHits() { return opponentHits; }

   @Override
   public PhysicsObjectInterface getBall() { return ball; }
}
