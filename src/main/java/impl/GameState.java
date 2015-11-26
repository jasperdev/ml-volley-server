package impl;

import agent.GameSnapshot;
import control.GameMonitor;
import state.ImmutableSnapshot;

public class GameState implements GameObserver {

    private final GameMonitor monitor;

    public MatchState match;

    public int lScore = 0, rScore = 0;
    public int lHits = 0, rHits = 0;

    private ImmutableSnapshot currentSnapshot = null;

    public GameState(GameProperties gameProps, PhysicsProperties physProps, GameMonitor monitor) {
        this.monitor = monitor;
        match = new MatchState(gameProps, physProps, monitor);
        match.addObserver(this);

        reset();
        monitor.onMatchStart(generateSnapshot()); //Only place to put this
    }

    public void reset() {
        lScore = rScore = lHits = rHits = 0;
        match.reset();
    }

    public void step() {
        ImmutableSnapshot before = this.currentSnapshot;
        match.step(before);
        GameSnapshot after = generateSnapshot();
        if (monitor.onMatchStep(before, after) == GameMonitor.Progress.STOP || match.matchFinished) {
            match.ball.side = (match.ball.side == SideImpl.LEFT) ? SideImpl.RIGHT : SideImpl.LEFT;
            monitor.onMatchEnd(after);
            match.reset();
            if (!isFinished()) { // Not finished, more games to play
                monitor.onMatchStart(generateSnapshot());
            }
        }
    }

    private ImmutableSnapshot generateSnapshot() {
        this.currentSnapshot = ImmutableSnapshot.from(match, lScore, rScore);
        return currentSnapshot;
    }

    public boolean isFinished() {
        return monitor.isFinished(currentSnapshot);
    }

    public void observe(GameEvent e) {
        switch (e) {
            case SCORE_L:
                lScore++;
                break;
            case SCORE_R:
                rScore++;
                break;
            case BALL_HIT_L:
                lHits++;
                break;
            case BALL_HIT_R:
                rHits++;
                break;
            default:
                System.err.println("Unexpected Game Event: " + e);
        }
    }
}
