public class EasyMode implements GameFactory {
    @Override
    public Minesweeper createGame() {
        return new Minesweeper(8, 8, 10);
    }
}
