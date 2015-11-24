package state;

import agent.BallState;
import impl.PhysicsCircle;

public class ImmutableBallState implements BallState {

    private final long posX;
    private final long posY;
    private final long velX;
    private final long velY;

    public ImmutableBallState(long posX, long posY, long velX, long velY) {

        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
    }

    public static BallState from(impl.BallStateImpl ball) {
        PhysicsCircle ballPos = ball.pCircle;
        return new ImmutableBallState(ballPos.posX, ballPos.posY, ballPos.velX, ballPos.velY);
    }

    @Override
    public long posX() {
        return posX;
    }

    @Override
    public long posY() {
        return posY;
    }

    @Override
    public long velX() {
        return velX;
    }

    @Override
    public long velY() {
        return velY;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImmutableBallState{");
        sb.append("pos=").append(posX).append(',').append(posY);
        sb.append(", vel=").append(velX).append(',').append(velY);
        sb.append('}');
        return sb.toString();
    }
}
