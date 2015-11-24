public interface InputProvider {

    PlayerInput provide(GameSnapshot snapshot);

    void resetMatch();

}
