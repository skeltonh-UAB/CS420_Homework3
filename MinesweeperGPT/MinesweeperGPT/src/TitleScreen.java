import java.util.Scanner;

public class TitleScreen {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Choose a difficulty level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        // Instantiate appropriate factory based on difficulty
        GameFactory factory;
        switch (choice) {
            case 1:
                factory =  new EasyMode();
                break;
            case 2:
                factory = new NormalMode();
                break;
            case 3:
                factory = new HardMode();
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }

        // Create Minesweeper game using factory
        Minesweeper game = factory.createGame();

        // Start the game
        game.startGame();
    }
}
