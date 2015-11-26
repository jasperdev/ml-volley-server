package impl;

import agent.Side;

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

    public static SideImpl fromApiSide(Side side) {
        switch (side) {
            case LEFT:
                return LEFT;
            case RIGHT:
                return RIGHT;
            default:
                throw new AssertionError();
        }
    }

    public long translateX(long x) {
        return scale * x;
    }

    public abstract PlayerStateImpl getPlayer(GameState game);
}
