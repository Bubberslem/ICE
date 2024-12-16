import java.util.Scanner;

public class Path {

TextUI ui = new TextUI();
private Player player;

    public Path(Player player) {
        this.player = player;
    }

    public void startPath(String saveSlot) {
        displayWelcomeMessage();

        System.out.println("=== Your Adventure Begins! ===");
        System.out.println("You are in a destroyed city filled with rubble, fire and the smell of concrete. A billboard lies before you, written on it states Welcome to City 17. three paths lie ahead: ");
        System.out.println("1. Take the path through the abandoned parking lot (Strange sounds are heard from within).");
        System.out.println("2. Follow along the destroyed roads of the city (The roads are very open a visible from a far).");
        System.out.println("3. Walk towards the Combine base (It looks destroyed).");
        String choice = ui.promptText("Choose a path: ");

        hanndleChoice(choice, saveSlot);

    }
    private void displayWelcomeMessage() {
        System.out.println("Welcome to City 17");
        System.out.println("You're a rebellious fighter known throughout planet earth, however humanity failed and you lost the war for the world!");
        System.out.println("Your name is Tess Freeman, known for you wisdom and athletic abilities. You're equipped with an standard glock 18 and a crowbar");
        System.out.println("Follow your heart and save the world from the evil combine slavers.");
        System.out.println();
    }

    private void hanndleChoice(String choice, String saveSlot) {
        switch (choice) {
            case "1":
                System.out.println("You head towards the abandoned parking lot. As you enter shadowy figures dance around the pillars of the concrete castle. Before you lies a corpse of a security guard with loot at his disposal. ");
                triggerEvent("Parking lot", saveSlot);
                break;
            case "2":
                System.out.println("You follow along the destroyed roads. The fog gets thicker and thicker around you as you venture further into the ruined city. A combine helicopter shines a ligth on you from above!");
                triggerEvent("Road", saveSlot);
                break;
                case "3":
                    System.out.println("The combine base is mostly destroyed with solderis lying lifeless at every turn. Most of their electronic equipment is completely destroyed... except for a small SMG with a detachable grenade launcher");
                    triggerEvent("Combine Base", saveSlot);
                    break;
            default:
                System.out.println("Invalid choice! Try again.");
                startPath(saveSlot);
        }
    }
    private void encounterSecurityGuard() {
        System.out.println("The security guard has the following loot:");
        System.out.println("1. A tactical flashlight (Good for dark areas or exposing shadowy beings...).");
        System.out.println("2. A medkit (restores a moderate amount of health upon use).");
        System.out.println("3. A baton (Well suited for close quarter combat).");

        String lootChoice = ui.promptText("Choose an item to take: ");
        handleLootChoice(lootChoice);
    }

    private void handleLootChoice(String lootChoice) {
        switch (lootChoice) {
            case "1":
                System.out.println("You take the tactical flashlight. It might help you in dark places.");
                player.addToInventory(new Loot("Tactical Flashlight", 20, "Utility"));
                break;
            case "2":
                System.out.println("you take the medkit. It might save your life someday.");
                player.addToInventory(new Loot("Medkit", 50,"Healing"));
                break;
            case "3":
                System.out.println("You take the Baton. Useful for close quarter combat");
                Weapon Baton = new Weapon("Baton", 100, 15, 10);
                player.addWeapon(Baton);
                player.equipWeapon(Baton);
                break;
            default:
                System.out.println("Invalid choice! Try again.");
                String newLootChoice = ui.promptText("Choose an item to take: ");
                handleLootChoice(newLootChoice);
        }
    }

    private void triggerEvent(String location, String saveSlot) {
        System.out.println("An event occurs in the " + location + "!");
        // Placeholder for event logic
        System.out.println("Your progress is saved to " + saveSlot + ".");
        System.out.println("Returning to the main menu...\n");
    }

}
