package impl;

public class StaticPlayerInputProvider implements PlayerInputProvider {
   public PlayerInputImpl in = new PlayerInputImpl();
   public PlayerInputImpl getInput() { return in; }
}
