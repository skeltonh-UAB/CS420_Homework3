public class HardMode implements GameFactory{
    @Override
    public Minesweeper createGame() {
        return new Minesweeper(16, 16, 30);
    }
}
