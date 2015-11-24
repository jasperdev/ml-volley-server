package agent;

public interface GameSnapshot {

    PlayerState self();

    PlayerState opponent();

    BallState ball();

    long myScore();

    long opponentScore();
}
