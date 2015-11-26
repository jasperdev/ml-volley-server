public interface GameFactory {
   public UI createUI();
   public PlayerInputProvider createLeftPlayer();
   public PlayerInputProvider createRightPlayer();
}
