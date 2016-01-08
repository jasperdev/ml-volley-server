package server;
import java.util.concurrent.TimeUnit;

import ui.UI;

public class FastGameLoop {

    public GameState state;
    public int iterations = 0;

    public FastGameLoop(GameState state_) {
        state = state_;
    }

    public void run() {
        while (!state.isFinished()) {
            state.step();
            iterations++;
        }
    }
}
