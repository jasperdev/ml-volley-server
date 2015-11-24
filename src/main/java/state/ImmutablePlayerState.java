package state;

import agent.PlayerState;
import agent.Side;
import impl.PhysicsCircle;
import impl.PlayerStateImpl;

public class ImmutablePlayerState implements PlayerState {

    private final long posX;
    private final long posY;
    private final long velX;
    private final long velY;
    private final Side side;

    public ImmutablePlayerState(long posX, long posY, long velX, long velY, Side side) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.side = side;
    }

    public static ImmutablePlayerState from(PlayerStateImpl player, Side side) {
        PhysicsCircle playerPos = player.pCircle;
        return new ImmutablePlayerState(playerPos.posX, playerPos.posY, playerPos.velX, playerPos.velY, side);
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
    public Side side() {
        return side;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImmutablePlayerState{");
        sb.append("pos=").append(posX).append(',').append(posY);
        sb.append(", vel=").append(velX).append(',').append(velY);
        sb.append(", side=").append(side);
        sb.append('}');
        return sb.toString();
    }
}
