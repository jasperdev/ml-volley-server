package server;
import physics.PhysicsObjectInterface;

public interface GameStateInterface {
   public PhysicsObjectInterface getMe();
   public int getMyScore();
   public int getMyHits();

   public PhysicsObjectInterface getOpponent();
   public int getOpponentScore();
   public int getOpponentHits();

   public PhysicsObjectInterface getBall();
}
