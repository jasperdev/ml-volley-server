package agent;
public class PlayerInput {
   public boolean left, right, up;

   public PlayerInput() { this(false, false, false); }

   public PlayerInput(boolean left_, boolean right_, boolean up_) {
      left = left_;
      right = right_;
      up = up_;
   }

   public PlayerInput flippedCopy() { return new PlayerInput(right, left, up); }
}
