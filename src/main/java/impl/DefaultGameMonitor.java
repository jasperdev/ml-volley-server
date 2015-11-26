package impl;

import agent.GameSnapshot;
import agent.InputProvider;
import control.GameMonitor;

public class DefaultGameMonitor implements GameMonitor {

    private static final int POINTS_TO_WIN = 21;

    private final GameProperties props;

    public DefaultGameMonitor(GameProperties props) {
        this.props = props;
    }

    @Override
    public InputProvider createLeftPlayer() {
        return KeyboardInputProvider.PLAYER_1;
    }

    @Override
    public InputProvider createRightPlayer() {
        return new BallFollower(props.playerRadius / 2);
    }

    @Override
    public void onMatchStart(GameSnapshot state) {
        // NOOP
    }

    @Override
    public void onMatchEnd(GameSnapshot state) {
        // NOOP
    }

    @Override
    public Progress onMatchStep(GameSnapshot previousState, GameSnapshot nextState) {
        return Progress.CONTINUE;
    }

    @Override
    public boolean isFinished(GameSnapshot snapshot) {
        return snapshot.myScore() >= POINTS_TO_WIN || snapshot.opponentScore() >= POINTS_TO_WIN;
    }
}
