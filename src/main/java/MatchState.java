import java.util.*;

public class MatchState {
   public PlayerState lPlayer, rPlayer;
   public BallState ball;

   public final int maxHits;
   public int hitCount;
   public long collisionVelocity;
   public boolean matchFinished;

   public EnumMap<GameEvent, Collection<GameObserver>> observerMap = new EnumMap<GameEvent, Collection<GameObserver>>(GameEvent.class);
   public Collection<GameObserver> globalObservers = new ArrayList<GameObserver>();

   public MatchState(GameProperties gameProps, PhysicsProperties physProps, PlayerInputProvider lInput, PlayerInputProvider rInput) {
      lPlayer = new PlayerState(Side.LEFT, lInput, gameProps, physProps);
      rPlayer = new PlayerState(Side.RIGHT, rInput, gameProps, physProps);
      ball = new BallState(gameProps, physProps);

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
      hitCount = Math.min(hitCount,0)-1;
   }
   public void registerRightHit() {
      ball.firstHit = false;
      notifyObservers(GameEvent.BALL_HIT_R);
      hitCount = Math.max(hitCount,0)+1;
   }

   public void step(GameState gstate) {
      ball.step();
      lPlayer.step(new FacadeGameState(gstate, false));
      rPlayer.step(new FacadeGameState(gstate, true));
      if (ball.hitGround) {
         matchFinished = true;
         notifyObservers(ball.pCircle.posX > 0 ? GameEvent.SCORE_L : GameEvent.SCORE_R);
         return;
      }

      if (ball.pCircle.collideCircle(lPlayer.pCircle,collisionVelocity)) {
         registerLeftHit();
      }
      if (ball.pCircle.collideCircle(rPlayer.pCircle,collisionVelocity)) {
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
