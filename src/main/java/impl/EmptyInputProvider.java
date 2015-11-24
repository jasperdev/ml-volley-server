package impl;

public class EmptyInputProvider implements PlayerInputProvider {
   PlayerInputImpl in = new PlayerInputImpl();
   int count = 0;
   @Override
   public PlayerInputImpl getInput() {
      count++;
      in.up ^= count % 100 == 20;
      //in.up ^= Math.random() > 0.9;
      //in.left ^= Math.random() > 0.9;
      //in.right ^= Math.random() > 0.9;
      //System.err.println(in.up+" "+in.left+" "+in.right);
      return in;
   }
}
