package impl;

import agent.BallState;
import agent.GameSnapshot;
import agent.InputProvider;
import agent.PlayerInput;
import agent.PlayerState;

public class BallFollower implements InputProvider {

    private final long followingOffset;

    public BallFollower(long followingOffset) {
        this.followingOffset = followingOffset;
    }

    @Override
    public PlayerInput provide(GameSnapshot snapshot) {
        BallState ball = snapshot.ball();
        PlayerState me = snapshot.self();

        long idealLocation = me.posX() - SideImpl.fromApiSide(me.side()).translateX(followingOffset);

        return PlayerInput.NEUTRAL
                .withLeft(ball.posX() < idealLocation)
                .withRight(ball.posX() > idealLocation)
                .withUp(ball.posY() > me.posY() * 3);
    }

    @Override
    public void resetMatch() {

    }
}
