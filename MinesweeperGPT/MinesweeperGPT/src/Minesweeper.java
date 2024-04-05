import java.util.Scanner;

public class Minesweeper {
    private Board board;
    private boolean gameOver;

    public Minesweeper(int width, int height, int numberOfMines) {
        board = new Board(width, height, numberOfMines);
        gameOver = false;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            board.printBoard();
            System.out.println("Enter action (reveal/flag) and coordinates x y:");
            String action = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            switch (action) {
                case "reveal":
                    boolean hitMine = board.revealCell(x, y);
                    if (hitMine) {
                        gameOver = true;
                        System.out.println("Game Over! You hit a mine.");
                        board.printBoard();
                        return;
                    }
                    break;
                case "flag":
                    board.flagCell(x, y);
                    break;
                default:
                    System.out.println("Invalid action. Use 'reveal' or 'flag'.");
                    break;
            }

            if (board.checkWin()) {
                gameOver = true;
                System.out.println("Congratulations! You've cleared all mines.");
                board.printBoard();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(10, 10, 10);
        game.startGame();
    }
}