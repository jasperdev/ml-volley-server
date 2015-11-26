package agent;

import java.util.Objects;

public abstract class AlwaysLeftInputProvider implements InputProvider {

    @Override
    public final PlayerInput provide(GameSnapshot snapshot) {
        if (Objects.equals(Side.LEFT, snapshot.self().side())) {
            // Already left
            return provideForLeft(snapshot);
        } else {
            // Reflect and swap the returned input
            return swapInput(provideForLeft(ReflectingSnapshot.ensureSelfSide(Side.LEFT, snapshot)));
        }
    }

    private PlayerInput swapInput(PlayerInput input) {
        Objects.requireNonNull(input, "Please don't return null");

        return PlayerInput.NEUTRAL
                .withUp(input.up)
                .withLeft(input.right)
                .withRight(input.left);
    }

    public abstract PlayerInput provideForLeft(GameSnapshot snapshot);
}
