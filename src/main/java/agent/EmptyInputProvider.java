package agent;
import server.GameStateInterface;
import server.Side;

public class EmptyInputProvider implements PlayerInputProvider {
   PlayerInput in = new PlayerInput();
   int count = 0;

   @Override
   public PlayerInput getInput(GameStateInterface state, Side side) {
      count++;
      in.up ^= Math.random() > 0.9;
      in.left ^= Math.random() > 0.9;
      in.right ^= Math.random() > 0.9;
      return in;
   }
}
