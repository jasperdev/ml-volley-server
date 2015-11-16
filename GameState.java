import java.util.EnumMap;

public class GameState {
   public enum GameEvent {
      BALL_HIT_L,
      BALL_HIT_R,
      SCORE_L,
      SCORE_R;
   }

   public PlayerState lPlayer, rPlayer;
   public BallState ball;
   public PlayerInputProvider lInput, rInput;

   public final int maxHits;
   public int hitCount;

   public EnumMap observerMap = new EnumMap<GameState, Collection<GameObserver>>();
   public Collection<GameObserver> globalObservers = new ArrayList<GameObserver>();

   public GameState(GameProperties gameProps, PhysicsProperties physProps, PlayerInputProvider lInput_, PlayerInputProvider rInput_) {
      lPlayer = new PlayerState(PlayerState.Side.LEFT, gameProps, physProps);
      rPlayer = new PlayerState(PlayerState.Side.RIGHT, gameProps, physProps);
      ball = new BallState(gameProps, physProps);

      lInput = lInput_;
      rInput = rInput_;
      maxHits = gameProps.maxHits;
   }

   public void init() {
      lPlayer.init();
      rPlayer.init();
      ball.init();
      hitCount = 0;
   }

   public void registerLeftHit() {
      notifyObservers(BALL_HIT_L);
      hitCount = Math.min(hitCount,0)-1;
   }
   public void registerRightHit() {
      notifyObservers(BALL_HIT_R);
      hitCount = Math.max(hitCount,0)+1;
   }

   public void step() {
      ball.step();
      lPlayer.step(lInput.getInput());
      rPlayer.step(rInput.getInput());
      if (ball.hitGround) {
         notifyObservers(ball.pCircle.posX > 0 ? SCORE_L : SCORE_R);
         return;
      }

      if (ball.collideCircle(lPlayer)) {
         registerLeftHit();
      }
      if (ball.collideCircle(rPlayer)) {
         registerRightHit();
      }
      if (Math.abs(hitCount) > maxHits) {
         notifyObservers(hitCount > 0 ? SCORE_L : SCORE_R);
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
      if (!observerMap.containsKey(e)) {
         return;
      }

      for (GameObserver o : observerMap.get(e)) {
         o.observe(e);
      }

      for (GameObserver o : globalObservers) {
         o.observe(e);
      }
   }
}
