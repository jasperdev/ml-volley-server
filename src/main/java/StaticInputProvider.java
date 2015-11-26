public class StaticInputProvider implements PlayerInputProvider {
   public PlayerInput in = new PlayerInput();

   @Override
   public PlayerInput getInput(GameState state, Side side) { return in; }
}
