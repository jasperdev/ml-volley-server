package server;
public interface GameSupervisor {
   public void onFrameStart(GameState state);
   public boolean onFrameEnd(GameState state);
   public void onMatchStart(GameState state);
   public boolean onMatchEnd(GameState state);
   public void onGameStart(GameState state);
   public boolean onGameEnd(GameState state);
}
