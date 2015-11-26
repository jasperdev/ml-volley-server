public class EmptyInputProvider implements PlayerInputProvider {
   PlayerInput in = new PlayerInput();
   int count = 0;

   @Override
   public PlayerInput getInput(GameState state, Side side) {
      count++;
      in.up ^= Math.random() > 0.9;
      in.left ^= Math.random() > 0.9;
      in.right ^= Math.random() > 0.9;
      return in;
   }
}
