import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private TextUI ui;
    private String name;
    private List<String> gameSlots;
    private List<Weapon> globalWeapons;
    private List<Armor> globalArmors;

    public Game(String name) {
        this.name = name;
        this.ui = new TextUI();
        this.gameSlots = FileIO.readPlayerData("data/gameSlots.csv"); // Load game slots on startup
        this.globalWeapons = FileIO.loadWeapons("data/weapons.csv");
        this.globalArmors = FileIO.loadArmor("data/armors.csv");
    }

    public void menu() {
        boolean running = true;

        while (running) {
            ui.displayMsg("=== Welcome to " + name + "! ===");
            ui.displayMsg("1. New Game");
            ui.displayMsg("2. Load Game");
            ui.displayMsg("3. Exit Game");
            String choice = ui.promptText("Please choose an option, adventurer!: ");

            switch (choice) {
                case "1":
                    handleNewGame();
                    break;
                case "2":
                    handleLoadGame();
                    break;
                case "3":
                    System.out.println("Exiting Game...\nThanks for playing " + name + "!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Choice. Select an available option");
            }
        }
    }

    public void handleNewGame() {
        while (true) {
            ui.displayMsg("=== Select a Save Slot ===");
            ui.displayMsg("1. Save Slot 1");
            ui.displayMsg("2. Save Slot 2");
            ui.displayMsg("3. Save Slot 3");
            ui.displayMsg("4. Back to Main Menu");
            String slotChoice = ui.promptText("Choose a Save Slot to continue!");

            switch (slotChoice) {
                case "1":
                case "2":
                case "3":
                    ui.displayMsg("Save Slot " + slotChoice + " selected... starting New Game");
                    createNewGameSlot(slotChoice);
                    return;
                case "4":
                    ui.displayMsg("Returning to Main Menu...");
                    return;
                default:
                    ui.displayMsg("Invalid Choice. Select an available option");
            }
        }
    }

    public void handleLoadGame() {

        if (gameSlots.isEmpty()) {
            ui.displayMsg("No save slots available. Start a new game first.");
            return;
        }

        while (true) {
            ui.displayMsg("=== Load Game ===");
            for (int i = 0; i < gameSlots.size(); i++) {
                ui.displayMsg((i + 1) + ". Slot " + (i + 1) + ": " + gameSlots.get(i));
            }
            ui.displayMsg((gameSlots.size() + 1) + ". Back to Main Menu");

            String slotChoice = ui.promptText("Choose a Slot to continue!");
            int slotIndex;

            try {
                slotIndex = Integer.parseInt(slotChoice) - 1;

                if (slotIndex >= 0 && slotIndex < gameSlots.size()) {
                    String filePath = gameSlots.get(slotIndex);
                    Player player = loadPlayerFromFile(filePath);
                    ui.displayMsg("Loaded player: " + player);
                    return;
                } else if (slotIndex == gameSlots.size()) {
                    ui.displayMsg("Returning to Main Menu...");
                    return;
                } else {
                    ui.displayMsg("Invalid Choice. Select an available option");
                }
            } catch (NumberFormatException e) {
                ui.displayMsg("Invalid input. Please enter a number.");
            }
        }
    }

    private void createNewGameSlot(String slotChoice) {
        // Create a new save slot file and add it to the list
        String newSlotPath = "data/slot" + slotChoice + ".csv";
        FileIO.saveData(new ArrayList<>(), newSlotPath, "type,name,value,stat1,stat2"); // Initialize empty save file
        gameSlots.add(newSlotPath);

        // Create a corresponding inventory file for the new slot
        String newInventoryPath = "data/inventory" + slotChoice + ".csv";
        FileIO.saveData(new ArrayList<>(), newInventoryPath, "type,name,value,stat1,stat2"); // Initialize empty inventory file

        ui.displayMsg("New game slot created: " + newSlotPath);
    }

    private Player loadPlayerFromFile(String filePath) {
        Player player = new Player("Adventurer", 100, 100); // Default stats
        player.loadPlayerData(filePath); // Load player data (e.g., name, stats)

        // Load the player's inventory from the corresponding inventory file
        String inventoryFilePath = filePath.replace("slot", "inventory");
        player.loadInventoryFromFile(inventoryFilePath); // Load inventory data

        return player;
    }
}
