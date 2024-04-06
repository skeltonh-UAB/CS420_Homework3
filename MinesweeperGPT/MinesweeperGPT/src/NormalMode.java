public class NormalMode implements GameFactory {
    @Override
    public Minesweeper createGame() {
        return new Minesweeper(12, 12, 20);
    }
}
