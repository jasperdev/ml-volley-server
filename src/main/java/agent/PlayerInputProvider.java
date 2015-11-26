package agent;
import server.GameStateInterface;
import server.Side;

public interface PlayerInputProvider {
   public PlayerInput getInput(GameStateInterface state, Side side);
}
