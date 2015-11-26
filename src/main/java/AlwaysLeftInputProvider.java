public abstract class AlwaysLeftInputProvider implements PlayerInputProvider {
   @Override
   public final PlayerInput getInput(GameStateInterface state, Side side) {
      if (side == Side.LEFT) {
         return getInput(new GameStateSnapshot(state, false));
      } else {
         return getInput(new GameStateSnapshot(state, true)).flippedCopy();
      }
   }

   public abstract PlayerInput getInput(GameStateInterface state);
}
