import java.util.Scanner;



public class Game {
    private TextUI ui;
    private String name;
    public Game(String name) {
        this.name = name;
        this.ui = new TextUI();

    }
        public void menu() {
        boolean running = true;

        while (running) {
            ui.displayMsg("=== Welcome to "+ name+ "!"+" ===");
            ui.displayMsg("1. New Game");
            ui.displayMsg("2. Load Game");
            ui.displayMsg("3. Exit Game");
            String Choice = ui.promptText("Please choose an option adventurer!: ");

            switch (Choice) {
                case "1":
                    handleNewGame();
                    break;
                case "2":
                    System.out.println("Loading Game...");
                    break;
                    case "3":
                        System.out.println("Exiting Game...\nThanks for playing "+ name+"!");
                        running = false;
                        break;
                        default:
                            System.out.println("Invalid Choice. Select an available option");
            }
        }
    }
    private void handleNewGame() {
        while (true) {
            ui.displayMsg("=== Select a Save Slot===");
            ui.displayMsg("1. Save Slot 1");
            ui.displayMsg("2. Save Slot 2");
            ui.displayMsg("3. Save Slot 3");
            ui.displayMsg("4. Back to Main Menu");
            String slotChoice = ui.promptText("Choose a Save Slot to continue!");

            switch (slotChoice) {
                case "1":
                    ui.displayMsg("Save Slot 1 selected... starting New Game");
                    return;
                    case "2":
                        ui.displayMsg("Save Slot 2 selected... starting New Game");
                        return;
                        case "3":
                            ui.displayMsg("Save Slot 3 selected... starting New Game");
                            return;
                            case "4":
                                ui.displayMsg("Returning to Main Menu...");
                                return;
                                default:
                                    ui.displayMsg("Invalid Choice. Select an available option");
            }
        }
    }
}
