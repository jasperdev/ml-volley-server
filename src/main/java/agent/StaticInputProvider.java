package agent;
import server.GameStateInterface;
import server.Side;

public class StaticInputProvider implements PlayerInputProvider {
   public PlayerInput in = new PlayerInput();

   @Override
   public PlayerInput getInput(GameStateInterface state, Side side) { return in; }
}
