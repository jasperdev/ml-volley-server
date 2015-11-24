package control;

import agent.GameSnapshot;
import agent.InputProvider;

public interface GameMonitor {

    InputProvider createLeftPlayer();

    InputProvider createRightPlayer();

    void onMatchStart(GameSnapshot state);

    void onMatchEnd(GameSnapshot state);

    Progress onMatchStep(GameSnapshot previousState, GameSnapshot nextState);

    enum Progress {
        STOP,
        CONTINUE,
    }
}
