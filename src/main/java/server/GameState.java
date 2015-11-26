package server;
import agent.PlayerInput;
import agent.PlayerInputProvider;
import physics.PhysicsObjectInterface;

public class GameState implements GameObserver, GameStateInterface {
   public static final int POINTS_TO_WIN = 21;

   public MatchState match;

   public PlayerInputProvider lInputProvider, rInputProvider;
   public int lScore = 0, rScore = 0;
   public int lHits = 0, rHits = 0;

   public GameState(GameProperties gameProps, PhysicsProperties physProps,
         PlayerInputProvider lInputProvider_, PlayerInputProvider rInputProvider_) {
      match = new MatchState(gameProps, physProps);
      match.addObserver(this);

      lInputProvider = lInputProvider_;
      rInputProvider = rInputProvider_;
      reset();
   }

   public void reset() {
      lScore = rScore = lHits = rHits = 0;
      match.reset();
   }

   public void step() {
      PlayerInput lInput = lInputProvider.getInput(this, Side.LEFT);
      PlayerInput rInput = rInputProvider.getInput(this, Side.RIGHT);
      match.step(lInput, rInput);
      if (match.matchFinished) {
         match.ball.side = (match.ball.side == Side.LEFT) ? Side.RIGHT : Side.LEFT;
         match.reset();
      }
   }

   public boolean isFinished() {
      return lScore == POINTS_TO_WIN || rScore == POINTS_TO_WIN;
   }

   @Override
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

   @Override
   public PhysicsObjectInterface getMe() { return match.lPlayer.pCircle; }
   @Override
   public int getMyScore() { return lScore; }
   @Override
   public int getMyHits() { return lHits; }

   @Override
   public PhysicsObjectInterface getOpponent() { return match.rPlayer.pCircle; }
   @Override
   public int getOpponentScore() { return rScore; }
   @Override
   public int getOpponentHits() { return rHits; }

   @Override
   public PhysicsObjectInterface getBall() { return match.ball.pCircle; }
}
