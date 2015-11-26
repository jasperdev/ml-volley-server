package impl;

import control.GameMonitor;
import state.ImmutableSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

public class MatchState {

    public final int maxHits;
    public PlayerStateImpl lPlayer, rPlayer;
    public BallStateImpl ball;
    public int hitCount;
    public long collisionVelocity;
    public boolean matchFinished;

    public EnumMap<GameEvent, Collection<GameObserver>> observerMap = new EnumMap<GameEvent, Collection<GameObserver>>(GameEvent.class);
    public Collection<GameObserver> globalObservers = new ArrayList<GameObserver>();


    public MatchState(GameProperties gameProps, PhysicsProperties physProps, GameMonitor monitor) {
        lPlayer = new PlayerStateImpl(SideImpl.LEFT, monitor.createLeftPlayer(), gameProps, physProps);
        rPlayer = new PlayerStateImpl(SideImpl.RIGHT, monitor.createRightPlayer(), gameProps, physProps);
        ball = new BallStateImpl(gameProps, physProps);

        maxHits = gameProps.maxHits;
        collisionVelocity = physProps.playerCollisionVelocity;
    }

    public void reset() {
        lPlayer.reset();
        rPlayer.reset();
        ball.reset();

        hitCount = 0;
        matchFinished = false;
    }

    public void registerLeftHit() {
        ball.firstHit = false;
        notifyObservers(GameEvent.BALL_HIT_L);
        hitCount = Math.min(hitCount, 0) - 1;
    }

    public void registerRightHit() {
        ball.firstHit = false;
        notifyObservers(GameEvent.BALL_HIT_R);
        hitCount = Math.max(hitCount, 0) + 1;
    }

    public void step(ImmutableSnapshot impl) {
        ball.step();
        lPlayer.step(impl);
        rPlayer.step(impl.mirrorPlayers());
        if (ball.hitGround) {
            matchFinished = true;
            notifyObservers(ball.pCircle.posX > 0 ? GameEvent.SCORE_L : GameEvent.SCORE_R);
            return;
        }

        if (ball.pCircle.collideCircle(lPlayer.pCircle, collisionVelocity)) {
            registerLeftHit();
        }
        if (ball.pCircle.collideCircle(rPlayer.pCircle, collisionVelocity)) {
            registerRightHit();
        }
        if (Math.abs(hitCount) > maxHits) {
            matchFinished = true;
            notifyObservers(hitCount > 0 ? GameEvent.SCORE_L : GameEvent.SCORE_R);
            return;
        }
    }

    public void addObserver(GameEvent e, GameObserver o) {
        if (!observerMap.containsKey(e)) {
            observerMap.put(e, new ArrayList<GameObserver>());
        }
        observerMap.get(e).add(o);
    }

    public void addObserver(GameObserver o) {
        globalObservers.add(o);
    }

    private void notifyObservers(GameEvent e) {
        if (observerMap.containsKey(e)) {
            for (GameObserver o : observerMap.get(e)) {
                o.observe(e);
            }
        }


        for (GameObserver o : globalObservers) {
            o.observe(e);
        }
    }
}
