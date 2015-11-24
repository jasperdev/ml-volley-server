package state;

import agent.BallState;
import agent.GameSnapshot;
import agent.PlayerState;
import agent.Side;
import impl.MatchState;

public class ImmutableSnapshot implements GameSnapshot {

    private final BallState ball;
    private final ImmutablePlayerState self;
    private final ImmutablePlayerState other;
    private final int myScore;
    private final int opponentScore;

    private ImmutableSnapshot(BallState ballState, ImmutablePlayerState self, ImmutablePlayerState other,
            int myScore, int opponentScore) {
        this.ball = ballState;
        this.self = self;
        this.other = other;
        this.myScore = myScore;
        this.opponentScore = opponentScore;
    }

    public static ImmutableSnapshot from(MatchState match, int leftScore, int rightScore) {
        return new ImmutableSnapshot(
                ImmutableBallState.from(match.ball),
                ImmutablePlayerState.from(match.lPlayer, Side.LEFT),
                ImmutablePlayerState.from(match.rPlayer, Side.RIGHT),
                leftScore,
                rightScore
        );
    }

    @Override
    public PlayerState self() {
        return self;
    }

    @Override
    public PlayerState opponent() {
        return other;
    }

    @Override
    public BallState ball() {
        return ball;
    }

    @Override
    public long myScore() {
        return myScore;
    }

    @Override
    public long opponentScore() {
        return opponentScore;
    }

    public GameSnapshot mirrorPlayers() {
        return new ImmutableSnapshot(ball, other, self, opponentScore, myScore);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Snapshot{");
        sb.append("ball=").append(ball);
        sb.append(", self=").append(self);
        sb.append(", other=").append(other);
        sb.append(", myScore=").append(myScore);
        sb.append(", opponentScore=").append(opponentScore);
        sb.append('}');
        return sb.toString();
    }
}
