public class TitleScreen {
    public static void main(String[] args) {

        // Choose difficulty level based on user input or configuration
        String difficulty = "easy";  // Example: read from command line args or config

        // Instantiate appropriate factory based on difficulty
        GameFactory factory;
        switch (difficulty) {
            case "easy":
                factory =  new EasyMode();
                break;
            case "medium":
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
