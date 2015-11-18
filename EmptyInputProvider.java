public class EmptyInputProvider implements PlayerInputProvider {
   PlayerInput in = new PlayerInput();
   @Override
   public PlayerInput getInput() {
      in.up ^= Math.random() > 0.9;
      in.left ^= Math.random() > 0.9;
      in.right ^= Math.random() > 0.9;
      //System.err.println(in.up+" "+in.left+" "+in.right);
      return in;
   }
}
