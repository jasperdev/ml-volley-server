public class DefaultGameFactory {
   SwingUI ui;
   PlayerInputProvider lInput, rInput;
   public DefaultGameFactory(GameProperties gameProps) {
      ui = new SwingUI(gameProps);
      //lInput = new BallFollower(gameProps.playerRadius/2);
      lInput = new KeyboardInputProvider(ui, 'a', 'd', 'w');
      rInput = new BallFollower(gameProps.playerRadius/2);
      //rInput = new KeyboardInputProvider(ui, 'j', 'l', 'i');
   }

   public UI createUI() {
      return ui;
   }

   public PlayerInputProvider createLeftPlayer() {
      return lInput;
   }

   public PlayerInputProvider createRightPlayer() {
      return rInput;
   }
}
