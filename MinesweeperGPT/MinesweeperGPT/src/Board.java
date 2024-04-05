import java.util.Random;

public class Board {
    private Cell[][] cells;
    private int width, height;
    private int numberOfMines;

    public Board(int width, int height, int numberOfMines) {
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        cells = new Cell[height][width];
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numberOfMines) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (!cells[y][x].isMine()) {
                cells[y][x].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!cells[i][j].isMine()) {
                    int mines = countAdjacentMines(i, j);
                    cells[i][j].setAdjacentMines(mines);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < height && j >= 0 && j < width && cells[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean revealCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height || cells[y][x].isRevealed() || cells[y][x].isFlagged()) {
            return false;
        }

        cells[y][x].setRevealed(true);

        if (cells[y][x].isMine()) {
            return true; // Hit a mine
        }

        if (cells[y][x].getAdjacentMines() == 0) {
            // Recursively reveal adjacent cells if no adjacent mines
            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + 1; j++) {
                    if (i >= 0 && i < height && j >= 0 && j < width && (i != y || j != x)) {
                        revealCell(j, i);
                    }
                }
            }
        }
        return false;
    }

    public void flagCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height || cells[y][x].isRevealed()) {
            return;
        }
        cells[y][x].setFlagged(!cells[y][x].isFlagged());
    }

    public boolean checkWin() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!cells[i][j].isMine() && !cells[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isRevealed()) {
                    if (cells[i][j].isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(cells[i][j].getAdjacentMines() + " ");
                    }
                } else if (cells[i][j].isFlagged()) {
                    System.out.print("F ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    // Add methods to reveal cells and check for game over conditions
}