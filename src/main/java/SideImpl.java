public enum SideImpl {
   LEFT(-1) {
      public PlayerStateImpl getPlayer(GameState game) {
         return game.match.lPlayer;
      }
   },
   RIGHT(1) {
      public PlayerStateImpl getPlayer(GameState game) {
         return game.match.rPlayer;
      }
   };

   private int scale;

   SideImpl(int scale) {
      this.scale = scale;
   }

   public long translateX(long x) {
      return scale * x;
   }

   public abstract PlayerStateImpl getPlayer(GameState game);
}
