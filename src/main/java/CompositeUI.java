public class CompositeUI implements UI {
   UI[] bases;
   public CompositeUI(UI... bases_) {
      bases = bases_;
   }

   @Override
   public void init(GameState state) {
      for (UI ui : bases) {
         ui.init(state);
      }
   }

   @Override
   public void display(GameState state) {
      for (UI ui : bases) {
         ui.display(state);
      }
   }

   @Override
   public void finish(GameState state) {
      for (UI ui : bases) {
         ui.finish(state);
      }
   }
}
