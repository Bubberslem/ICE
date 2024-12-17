import java.util.Scanner;

public class Path {

    TextUI ui = new TextUI();
    private Player player;
    private Shopkeeper shopkeeper;

    public Path(Player player) {
        this.player = player;
        this.shopkeeper = new Shopkeeper();
    }

    public void startPath(Player player, String saveSlot) {

        ui.displayMsg("=== Your Adventure Begins! ===");
        ui.displayMsg("You are in a destroyed city filled with rubble, fire, and the smell of concrete. A billboard lies before you, stating: 'Welcome to City 17.'");
        ui.displayMsg("Three paths lie ahead: ");
        ui.displayMsg("1. Take the path through the abandoned parking lot (Strange sounds are heard from within).");
        ui.displayMsg("2. Follow along the destroyed roads of the city (The roads are very open and visible from afar).");
        ui.displayMsg("3. Walk towards the Combine base (It looks destroyed).");
        ui.displayMsg("4. Check your inventory");
        String choice = ui.promptText("Choose a path: ");
        handleChoice(choice, saveSlot);
    }

    public void displayWelcomeMessage(Player player) {
        ui.displayMsg("Welcome to City 17");
        ui.displayMsg("You're a rebellious fighter known throughout planet Earth. However, humanity failed, and you lost the war for the world!");
        ui.displayMsg("Your name is " + player.getName() + " Freeman, known for your wisdom and athletic abilities. You're equipped with a standard Glock 18 and a crowbar.");
        ui.displayMsg("Follow your heart and save the world from the evil Combine slavers.");
        System.out.println();
    }

    public void handleChoice(String choice, String saveSlot) {
        switch (choice) {
            case "1":
                System.out.println("You head towards the abandoned parking lot. Shadowy figures dance around the pillars of the concrete castle. Before you lies the corpse of a security guard with loot at his disposal.");
                encounterSecurityGuard();
                triggerEvent("Parking lot", saveSlot);
                break;
            case "2":
                System.out.println("You follow along the destroyed roads. The fog grows thicker as you venture further into the ruined city. A Combine helicopter shines a light on you from above!");
                triggerEvent("Road", saveSlot);
                break;
            case "3":
                System.out.println("The Combine base is mostly destroyed, with soldiers lying lifeless at every turn. Most of their electronic equipment is completely destroyed... except for a small SMG with a detachable grenade launcher.");
                triggerEvent("Combine Base", saveSlot);
                break;
            case "4":
                player.displayInventory();
                startPath(player, saveSlot);
                break;
            default:
                System.out.println("Invalid choice! Try again.");
                startPath(player, saveSlot);
        }
    }

    private void shopkeeperEncounter() {
        ui.displayMsg("You arrive at the Combine base, which is mostly destroyed. Among the wreckage, you spot a shopkeeper standing near a stall.");
        ui.displayMsg("The shopkeeper offers a variety of items for sale, including weapons, armor, and healing supplies.");

        // Display the shop inventory
        shopkeeper.displayShopInventory();

        // Let the player choose to buy an item or leave
        boolean shopOpen = true;
        while (shopOpen) {
            ui.displayMsg("\nWhat would you like to do?");
            ui.displayMsg("1. Buy an item");
            ui.displayMsg("2. Leave the shop");
            String choice = ui.promptText("Enter your choice: ");

            if (choice.equals("1")) {
                // Ask the player for the category of item they want to browse
                ui.displayMsg("Which category would you like to browse?");
                ui.displayMsg("1. Weapons");
                ui.displayMsg("2. Armor");
                ui.displayMsg("3. Items");
                String categoryChoice = ui.promptText("Enter category (1, 2, or 3): ");

                // Ask for the item to buy
                ui.displayMsg("Enter the number of the item you wish to buy: ");
                int itemChoice = Integer.parseInt(ui.promptText("Item number: "));

                // Buy the item
                shopkeeper.buyItem(player, Integer.parseInt(categoryChoice), itemChoice);
            } else if (choice.equals("2")) {
                // Exit the shop
                ui.displayMsg("You leave the shop and continue your journey.");
                shopOpen = false;
                startPath(player, "");  // Return to the main path
            } else {
                ui.displayMsg("Invalid choice! Please try again.");
            }
        }
    }






    private void handleRoadChoice(String roadChoice) {
        switch (roadChoice) {
            case "1": // Hide under the pile of bodies and find armor
                ui.displayMsg("You dive under a pile of dead rebellion soldiers. Their flesh is cold and moist.");
                ui.displayMsg("As you press against the bodies, your hand touches something metallic. It's a piece of armor!");
                ui.displayMsg("The helicopter fires a stream of bullets, but the armor absorbs the damage, saving your life.");
                Armor bulletproofVest = new Armor("Bulletproof Vest", 300, 50, 10); // Defense: 50, Durability: 10
                player.addArmor(bulletproofVest);
                player.equipArmor(bulletproofVest);
                ui.displayMsg("You equip the Bulletproof Vest. It will absorb damage from bullets, but its durability will decrease over time.");
                ui.displayMsg("The helicopter loses interest and flies away. You survive this encounter.");
                break;
            case "2": // Sprint for cover and die
                ui.displayMsg("You give an incredible dash, sprinting as fast as you can.");
                ui.displayMsg("The helicopter focuses on you and unleashes a precise stream of bullets.");
                ui.displayMsg("You are hit multiple times and collapse to the ground. You have died.");
                player.setHealth(0); // Trigger death sequence
                break;
            case "3": // Lay flat and die
                ui.displayMsg("You jump to the ground, hoping that lying flat will save you.");
                ui.displayMsg("The helicopter hovers above you, its spotlight fixed. A hail of bullets rains down.");
                ui.displayMsg("You are riddled with bullets and die instantly.");
                player.setHealth(0); // Trigger death sequence
                break;
            default:
                ui.displayMsg("Invalid choice! Try again.");
                String newRoadChoice = ui.promptText("Choose an action: 1. Hide in the pile of bodies, 2. Sprint, 3. Lay flat");
                handleRoadChoice(newRoadChoice);
        }
    }


    private void encounterSecurityGuard() {
        System.out.println("The security guard has the following loot:");
        System.out.println("1. A tactical flashlight (Good for dark areas or exposing shadowy beings).");
        System.out.println("2. A medkit (Restores a moderate amount of health upon use).");
        System.out.println("3. A baton (Well suited for close-quarter combat).");

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
                System.out.println("You take the medkit. It might save your life someday.");
                player.addToInventory(new Loot("Medkit", 50, "Healing"));
                break;
            case "3":
                System.out.println("You take the baton. Useful for close-quarter combat.");
                Weapon baton = new Weapon("Baton", 100, 15, 10);
                player.addWeapon(baton);
                player.equipWeapon(baton);
                break;
            default:
                System.out.println("Invalid choice! Try again.");
                String newLootChoice = ui.promptText("Choose an item to take: ");
                handleLootChoice(newLootChoice);
        }
    }

    private void triggerEvent(String location, String saveSlot) {
        if ("Parking lot".equals(location)) {
            ui.displayMsg("An enemy appears in the parking lot!");
            Enemy enemy = new Enemy("Security Guard", false, 15, 50);
            while (enemy.isAlive() && player.getHealth() > 0) {
                enemy.attack(player);
                String action = ui.promptText("Choose your action: 1. Attack, 2. Use Item");
                if ("1".equals(action)) {
                    player.attackEnemy(enemy);
                    ui.displayMsg(enemy.getName() + "s health is now " + enemy.getHealth());
                } else if ("2".equals(action)) {
                    // Example item use (healing)
                    player.increaseHealth(20);
                    ui.displayMsg(player.getName() + " uses a medkit! Health: " + player.getHealth());
                }
            }
            if (player.getHealth() <= 0) {
                ui.displayMsg("You have been killed by " + enemy.getName());
            } else {
                ui.displayMsg("Event in " + location + " completed.");
            }

        } else if ("Road".equals(location)) {
        ui.displayMsg("You continue down the destroyed road. The fog grows denser, obscuring your surroundings.");
        ui.displayMsg("In the distance, you hear the sound of a Combine helicopter. Its spotlight sweeps the area, searching for movement.");

        String roadChoice = ui.promptText("The helicopter seems to have spotted you! What do you do?\n1. Hide behind debris\n2. Run towards a building for cover\n3. Stand your ground and prepare to fight");
        handleRoadChoice(roadChoice);
    }
}
    }

