public class EmptyInputProvider implements PlayerInputProvider {
   PlayerInput in = new PlayerInput();
   int count = 0;
   @Override
   public PlayerInput getInput() {
      count++;
      in.up ^= count % 100 == 20;
      //in.up ^= Math.random() > 0.9;
      //in.left ^= Math.random() > 0.9;
      //in.right ^= Math.random() > 0.9;
      //System.err.println(in.up+" "+in.left+" "+in.right);
      return in;
   }
}
