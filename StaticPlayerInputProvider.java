public class StaticPlayerInputProvider implements PlayerInputProvider {
   public PlayerInput in = new PlayerInput();
   public PlayerInput getInput() { return in; }
}
