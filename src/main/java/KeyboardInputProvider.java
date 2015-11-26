import java.awt.Component;
import java.awt.event.*;

public class KeyboardInputProvider implements PlayerInputProvider {
   public PlayerInput input = new PlayerInput();
   public char left, right, up;

   public KeyboardInputProvider(Component c, char left_, char right_, char up_) {
      left = left_;
      right = right_;
      up = up_;

      c.addKeyListener(new GameKeyListener());
   }

   @Override
   public PlayerInput getInput(GameStateInterface state, Side side) {
      return input;
   }

   public class GameKeyListener extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         char c = e.getKeyChar();
         if (c == left) {
            input.left = true;
         } else if (c == right) {
            input.right = true;
         } else if (c == up) {
            input.up = true;
         }
      }

      @Override
      public void keyReleased(KeyEvent e) {
         char c = e.getKeyChar();
         if (c == left) {
            input.left = false;
         } else if (c == right) {
            input.right = false;
         } else if (c == up) {
            input.up = false;
         }
      }
   }
}
