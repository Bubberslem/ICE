import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Welcome to Madseroth! ===");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Exit Game");
            System.out.println("Please choose an option adventurer!: ");

            String Choice = scanner.nextLine();


            switch (Choice) {
                case "1":
                    System.out.println("Starting New Game...");
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
}
