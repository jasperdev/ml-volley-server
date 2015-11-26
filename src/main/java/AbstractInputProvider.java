public abstract class AbstractInputProvider implements PlayerInputProvider {
   @Override
   public PlayerInput getInput(GameState state, Side side) {
      if (side == Side.LEFT) {
         return getInput(new FacadeGameState(state, false));
      } else {
         return getInput(new FacadeGameState(state, true)).flipX();
      }
   }

   public abstract PlayerInput getInput(GameStateInterface state);
}
