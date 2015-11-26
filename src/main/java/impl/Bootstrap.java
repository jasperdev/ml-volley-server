package impl;

import control.GameMonitor;

public class Bootstrap {

    public static void main(String[] args) {
        GameProperties gameProps = new GameProperties();
        PhysicsProperties physProps = new PhysicsProperties();

        GameMonitor monitor = new DefaultGameMonitor(gameProps);

        UI ui = new CompositeUI(new EmptyUI(), new SwingUI(gameProps));
        GameState game = new GameState(gameProps, physProps, monitor);

        new GameLoop(60, ui, game).run();
    }
}
