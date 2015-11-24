public class GameState implements GameObserver {
   public static final int POINTS_TO_WIN = 21;

   public MatchState match;

   public int lScore = 0, rScore = 0;
   public int lHits = 0, rHits = 0;

   public GameState(GameProperties gameProps, PhysicsProperties physProps, PlayerInputProvider lInput, PlayerInputProvider rInput) {
      match = new MatchState(gameProps, physProps, lInput, rInput);
      match.addObserver(this);

      reset();
   }

   public void reset() {
      lScore = rScore = lHits = rHits = 0;
      match.reset();
   }

   public void step() {
      match.step();
      if (match.matchFinished) {
         match.ball.side = (match.ball.side == Side.LEFT) ? Side.RIGHT : Side.LEFT;
         match.reset();
      }
   }

   public boolean isFinished() {
      return lScore == POINTS_TO_WIN || rScore == POINTS_TO_WIN;
   }

   public void observe(GameEvent e) {
      switch (e) {
      case SCORE_L:
         lScore++; break;
      case SCORE_R:
         rScore++; break;
      case BALL_HIT_L:
         lHits++; break;
      case BALL_HIT_R:
         rHits++; break;
      default:
         System.err.println("Unexpected Game Event: "+e);
      }
   }
}
