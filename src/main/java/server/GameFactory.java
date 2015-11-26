package server;
import ui.UI;
import agent.PlayerInputProvider;

public interface GameFactory {
   public UI createUI();
   public PlayerInputProvider createLeftPlayer();
   public PlayerInputProvider createRightPlayer();
}
