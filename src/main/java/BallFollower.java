public class BallFollower implements PlayerInputProvider {
   public PlayerInputImpl in = new PlayerInputImpl();
   public GameState game;
   public SideImpl side;

   public BallFollower(GameState game_, SideImpl side_) {
      game = game_;
      side = side_;
   }

   public PlayerInputImpl getInput() {
      PhysicsCircle ball = game.match.ball.pCircle, me = side.getPlayer(game).pCircle;
      long idealLocation = me.posX - side.translateX(me.radius/2);
      in.left = ball.posX < idealLocation;
      in.right = ball.posX > idealLocation;
      in.up = ball.posY > me.posY * 3;
      return in;
   }
}
