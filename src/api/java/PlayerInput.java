public final class PlayerInput {
    public final static PlayerInput NEUTRAL = new PlayerInput(false, false, false);
    public final boolean left, right, up;

    private PlayerInput(boolean left, boolean right, boolean up) {
        this.left = left;
        this.right = right;
        this.up = up;
    }

    public PlayerInput withLeft(boolean left) {
        return new PlayerInput(left, this.right, this.up);
    }

    public PlayerInput withUp(boolean up) {
        return new PlayerInput(this.left, this.right, up);
    }

    public PlayerInput withRight(boolean right) {
        return new PlayerInput(this.left, right, this.up);
    }
}
