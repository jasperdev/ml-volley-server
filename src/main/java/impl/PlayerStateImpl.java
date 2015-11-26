package impl;

import agent.GameSnapshot;
import agent.InputProvider;
import agent.PlayerInput;

public class PlayerStateImpl {

    public PhysicsProperties physics;
    public GameProperties game;
    public SideImpl side;

    public InputProvider input;

    public long sideWidth, playerRadius;
    public boolean playerBounce;

    public PhysicsCircle pCircle;

    public PlayerStateImpl(SideImpl side_, InputProvider input_, GameProperties gameProps, PhysicsProperties physProps) {
        physics = physProps;
        game = gameProps;
        side = side_;

        input = input_;

        sideWidth = game.sideWidth;
        playerBounce = physics.playerBounce;

        pCircle = new PhysicsCircle(game.playerRadius);

        reset();
    }

    public void reset() {
        pCircle.posX = side.translateX(sideWidth / 2);
        pCircle.posY = pCircle.radius;
        pCircle.velX = pCircle.velY = 0;
        input.resetMatch();
    }

    public void step(GameSnapshot snapshot) {
        PlayerInput in = input.provide(snapshot);

        if (in.up) {
            if (pCircle.velY == 0) {
                pCircle.velY = physics.playerJumpVelocity;
            }
            pCircle.accY = -physics.playerReducedGravity;
        } else {
            pCircle.accY = -physics.playerGravity;
        }

        pCircle.velX = (in.right ? physics.playerHorizontalSpeed : 0) -
                (in.left ? physics.playerHorizontalSpeed : 0);

        pCircle.step();

        pCircle.collideFloor(0, playerBounce);
        pCircle.collideVertPlane(0, false);
        pCircle.collideVertPlane(-sideWidth, false);
        pCircle.collideVertPlane(sideWidth, false);
    }
}
