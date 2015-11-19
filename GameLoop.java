public class GameLoop {
   private final static int NS_PER_SEC = 1000000000;
   private final static long MS_PER_SEC = 1000;
   
   public static void run(int maxFps, UI ui, GameState state) {
      final long framePeriod = NS_PER_SEC / maxFps;
      
      ui.init(state);
      
      while (!state.isFinished()) {
         long beginTime = System.nanoTime();
         
         state.step();
         ui.display(state);
         
         long timeDiff = System.nanoTime() - beginTime;
         long sleepTime = framePeriod - timeDiff;
         if (sleepTime > 0) {
            try {
               long tmp = sleepTime * MS_PER_SEC;
               long sleepTimeMs = tmp / NS_PER_SEC;
               int sleepTimeNs = (int) (sleepTime % (NS_PER_SEC/MS_PER_SEC));
               //System.err.printf("Sleeping for %dms + %dns\n",sleepTimeMs,sleepTimeNs);
               Thread.sleep(sleepTimeMs, sleepTimeNs);
            } catch (InterruptedException e) {
               System.err.println("Sleep interrupted? "+e);
            }
         }
         
         for ( ; sleepTime < 0; sleepTime += framePeriod) {
            System.err.println("Uh oh... catching up.");
            state.step();
         }
      }
      
      ui.finish(state);
   }

   public static void main(String[] args) {
      GameProperties gameProps = new GameProperties();
      PhysicsProperties physProps = new PhysicsProperties();

      StaticPlayerInputProvider lInput = new StaticPlayerInputProvider();
      BallFollower ai = new BallFollower(null, Side.RIGHT);
      UI ui = new CompositeUI(new EmptyUI(), new SwingUI(gameProps, lInput, null));

      GameState game = new GameState(gameProps, physProps, lInput, ai);
      ai.game = game;

      run(60, ui, game);
   }
}
