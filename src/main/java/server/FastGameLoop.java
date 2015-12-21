package server;
import java.util.concurrent.TimeUnit;

import ui.UI;

public class FastGameLoop {

    public GameState state;

    public FastGameLoop(GameState state_) {
        state = state_;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        while (!state.isFinished()) {
            state.step();
        }
        long endTime = System.currentTimeMillis();
    }
}
