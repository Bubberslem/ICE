import java.util.Scanner;



public class Game {
    private TextUI ui;
    private String name;
    public Game(String name) {
        this.name = name;
        this.ui = new TextUI();

    }
        public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            ui.displayMsg("=== Welcome to Madseroth! ===");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Exit Game");
            System.out.println("Please choose an option adventurer!: ");

            String Choice = scanner.nextLine();


            switch (Choice) {
                case "1":
                    handleNewGame(scanner);
                    break;
                case "2":
                    System.out.println("Loading Game...");
                    break;
                    case "3":
                        System.out.println("Exit Game...");
                        break;
                        default:
                            System.out.println("Invalid Choice. Select an available option");
            }
            System.out.println();
        }
        scanner.close();
    }
    private static void handleNewGame(Scanner scanner) {
        while (true) {
            System.out.println("=== Select a Save Slot===");
            System.out.println("1. Save Slot 1");
            System.out.println("2. Save Slot 2");
            System.out.println("3. Save Slot 3");
            System.out.println("4. Back to Main Menu");
            System.out.println("Choose a Save Slot to continue!");

            String slotChoice = scanner.nextLine();
            switch (slotChoice) {
                case "1":
                    System.out.println("Save Slot 1 selected... starting New Game");
                    return;
                    case "2":
                        System.out.println("Save Slot 2 selected... starting New Game");
                        return;
                        case "3":
                            System.out.println("Save Slot 3 selected... starting New Game");
                            return;
                            case "4":
                                System.out.println("Returning to Main Menu...");
                                return;
                                default:
                                    System.out.println("Invalid Choice. Select an available option");
            }
            System.out.println();
        }
    }
}
