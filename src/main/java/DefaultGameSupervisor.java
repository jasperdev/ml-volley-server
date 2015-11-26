public class DefaultGameSupervisor implements GameSupervisor {
   public void onFrameStart(GameState state) { }
   public boolean onFrameEnd(GameState state) { return true; }
   public void onMatchStart(GameState state) { }
   public boolean onMatchEnd(GameState state) { return true; }
   public void onGameStart(GameState state) { }
   public boolean onGameEnd(GameState state) { return false; }
}

