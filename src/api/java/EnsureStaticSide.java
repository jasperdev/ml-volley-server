import java.util.Objects;

public class EnsureStaticSide {
    public static GameSnapshot ensureSelfSide(Side mySide, GameSnapshot snapshot) {
        if (Objects.equals(mySide, snapshot.self().side())) {
            return snapshot;
        } else {
            return new ReflectingSnapshot(snapshot);
        }
    }

    public static GameSnapshot ensureAlwaysLeft(GameSnapshot snapshot) {
        return ensureSelfSide(Side.LEFT, snapshot);
    }

    private static class ReflectingSnapshot implements GameSnapshot {
        private final GameSnapshot backend;

        public ReflectingSnapshot(GameSnapshot backend) {
            this.backend = backend;
        }

        @Override
        public PlayerState self() {
            return new ReflectedPlayer(backend.self());
        }

        @Override
        public PlayerState opponent() {
            return new ReflectedPlayer(backend.opponent());
        }

        @Override
        public BallState ball() {
            return new ReflectedBall(backend.ball());
        }

        @Override
        public long myScore() {
            return backend.myScore();
        }

        @Override
        public long opponentScore() {
            return backend.opponentScore();
        }
    }

    private static class ReflectedPlayer implements PlayerState {
        private final PlayerState playerState;

        public ReflectedPlayer(PlayerState playerState) {
            this.playerState = playerState;
        }

        @Override
        public long posX() {
            return -1 * playerState.posX();
        }

        @Override
        public long posY() {
            return playerState.posY();
        }

        @Override
        public long velX() {
            return -1 * playerState.velX();
        }

        @Override
        public long velY() {
            return playerState.velY();
        }

        @Override
        public Side side() {
            switch (playerState.side()) {
                case LEFT:
                    return Side.RIGHT;
                case RIGHT:
                    return Side.LEFT;
                default:
                    throw new AssertionError();
            }
        }
    }

    private static class ReflectedBall implements BallState {
        private final BallState ball;

        public ReflectedBall(BallState ball) {
            this.ball = ball;
        }

        @Override
        public long posX() {
            return -1 * ball.posX();
        }

        @Override
        public long posY() {
            return ball.posY();
        }

        @Override
        public long velX() {
            return -1 * ball.velX();
        }

        @Override
        public long velY() {
            return ball.velY();
        }
    }
}
