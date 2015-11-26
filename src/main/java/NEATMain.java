import agent.PlayerInputProvider;
import server.GameProperties;
import server.GameState;
import server.PhysicsProperties;


// NOTE: This is a simple example for demonstration purposes.
// This WILL fail.

public class NEATMain {

   public static void run(GameState state) {
      int ticks = 0;
      while (true) {
         state.step();
         ticks++;
         if (ticks > 1000 && state.rHits == 0 && state.lHits == 0) {
            break;
         }
         if (state.rScore > 21 || state.lScore > 21) {
            break;
         }
      }
   }

   public static void main(String[] args) {
      GameProperties gameProps = new GameProperties();
      PhysicsProperties physProps = new PhysicsProperties();
      while (true) {
         PlayerInputProvider lInput = null;
         PlayerInputProvider rInput = null;
         GameState s = new GameState(gameProps, physProps, lInput, rInput);
         run(s);
      }
   }
}
