import java.util.Scanner;

public class TitleScreen {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Choose a difficulty level:");
        System.out.println("1. easy");
        System.out.println("2. normal");
        System.out.println("3. hard");
        System.out.print("Enter your choice: ");

        String choice = scanner.next();

        // Instantiate appropriate factory based on difficulty
        GameFactory factory;
        switch (choice) {
            case "easy":
                factory =  new EasyMode();
                break;
            case "normal":
                factory = new NormalMode();
                break;
            case "hard":
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
