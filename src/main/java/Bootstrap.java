
import server.GameLoop;
import server.GameProperties;
import server.GameState;
import server.PhysicsProperties;
import ui.SwingUI;
import agent.BallFollower;
import agent.KeyboardInputProvider;
import agent.PlayerInputProvider;

public class Bootstrap {
   public static void main(String[] args) {
      GameProperties gameProps = new GameProperties();
      PhysicsProperties physProps = new PhysicsProperties();

      //StaticPlayerInputProvider lInput = new StaticPlayerInputProvider();
      SwingUI ui = new SwingUI(gameProps);
      //PlayerInputProvider lInput = new BallFollower(gameProps.playerRadius/2);
      PlayerInputProvider lInput = new KeyboardInputProvider(ui, 'a', 'd', 'w');
      PlayerInputProvider rInput = new BallFollower(gameProps.playerRadius/2);
      //PlayerInputProvider rInput = new KeyboardInputProvider(ui, 'j', 'l', 'i');

      GameState game = new GameState(gameProps, physProps, lInput, rInput);

      new GameLoop(60, ui, game).run();
   }
}
