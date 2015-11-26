package impl;

import agent.GameSnapshot;
import agent.InputProvider;
import agent.PlayerInput;

public class KeyboardInputProvider implements InputProvider {

    public final static KeyboardInputProvider PLAYER_1 = new KeyboardInputProvider();
    public final static KeyboardInputProvider PLAYER_2 = new KeyboardInputProvider();

    public PlayerInput nextInput = PlayerInput.NEUTRAL;

    private KeyboardInputProvider() {
    }

    @Override
    public PlayerInput provide(GameSnapshot snapshot) {
        return this.nextInput;
    }

    @Override
    public void resetMatch() {
        this.nextInput = PlayerInput.NEUTRAL;
    }
}
