package impl;

public class CompositeUI implements UI {
   UI[] bases;
   public CompositeUI(UI... bases_) {
      bases = bases_;
   }
   public void init(GameState state) {
      for (UI ui : bases) {
         ui.init(state);
      }
   }
   public void display(GameState state) {
      for (UI ui : bases) {
         ui.display(state);
      }
   }
   public void finish(GameState state) {
      for (UI ui : bases) {
         ui.finish(state);
      }
   }
}
