public enum Side {
   LEFT(-1) {
      public PlayerState getPlayer(GameState game) {
         return game.match.lPlayer;
      }
   },
   RIGHT(1) {
      public PlayerState getPlayer(GameState game) {
         return game.match.rPlayer;
      }
   };

   private int scale;

   Side(int scale) {
      this.scale = scale;
   }

   public long translateX(long x) {
      return scale * x;
   }

   public abstract PlayerState getPlayer(GameState game);
}
