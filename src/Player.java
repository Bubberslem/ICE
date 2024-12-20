import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int gold;
    private int health;
    private List<Weapon> weapons;
    private List<Armor> armors;
    private List<Loot> inventory;
    private List<Loot> items;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    public Player(String name, int gold, int health) {
        this.name = name;
        this.gold = gold;
        this.health = health;
        this.weapons = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.items = new ArrayList<>();
    }


    public void loadPlayerData(String filePath) {
        List<String> data = FileIO.readPlayerData(filePath);

        if (!data.isEmpty()) {
            String[] parts = data.get(0).split(","); // Assume first line contains player data
            this.name = parts[0].trim();
            this.gold = Integer.parseInt(parts[1].trim());
            this.health = Integer.parseInt(parts[2].trim());
        }
    }

    public void loadInventoryFromFile(String filePath) {
        List<String> data = FileIO.readPlayerData(filePath);

        for (String line : data) {
            String[] parts = line.split(","); // Split the line into fields
            String type = parts[0].trim();
            String name = parts[1].trim();
            int value = Integer.parseInt(parts[2].trim());
            int stat1 = Integer.parseInt(parts[3].trim());
            int stat2 = Integer.parseInt(parts[4].trim());

            if (type.equalsIgnoreCase("Weapon")) {
                weapons.add(new Weapon(name, value, stat1, stat2));
            } else if (type.equalsIgnoreCase("Armor")) {
                armors.add(new Armor(name, value, stat1, stat2));
            }
        }
    }
    public void displayInventory() {
        System.out.println("=== Inventory ===");

        displayCategory("Weapons", weapons);
        displayCategory("Armors", armors);
        displayCategory("Items", items);

        if (weapons.isEmpty() && armors.isEmpty() && items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        }
    }

    private <T> void displayCategory(String title, List<T> items) {
        if (!items.isEmpty()) {
            System.out.println("\n=== " + title + " ===");
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    public void equipWeapon(Weapon weapon) {
        if (weapons.contains(weapon)) {
            this.equippedWeapon = weapon;
            System.out.println(weapon.getName() + " is now equipped.");
        } else {
            System.out.println("You don't have this weapon in your inventory.");
        }
    }

    public void equipArmor(Armor armor) {
        if (armors.contains(armor)) {
            this.equippedArmor = armor;
            System.out.println(armor.getName() + " is now equipped.");
        } else {
            System.out.println("You don't have this armor in your inventory.");
        }
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getName() {
        return name;
    }
    public int getGold() {
        return gold;
    }
    public int getHealth() {
        return health;
    }


    //Player action methods
    public void decreaseHealth(int amount) {
        health -= amount;
    }
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; // Prevent negative health
        }
        System.out.println(name + " took " + damage + " damage! Health now: " + health);
    }
    public void increaseHealth(int amount) {
        health += amount;
    }

    public void addToInventory(Loot loot){
        inventory.add(loot);
    }
    public void addItem(Loot loot){
        items.add(loot);
    }
    public void attackEnemy(Enemy enemy) {
        if (equippedWeapon != null) {
            System.out.println(getName() + " attacks " + enemy.getName() + " with " + equippedWeapon.getName());
            enemy.takeDamage(equippedWeapon.getDamage());
        } else {
            System.out.println(getName() + " attacks " + enemy.getName() + " with their fists!");
            enemy.takeDamage(5); // Basic attack damage
        }
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Armor> getArmors() {
        return armors;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addArmor(Armor armor) {
        armors.add(armor);
    }
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0; // Health can't be negative
        } else if (health > 100) { // Assuming 100 is the maximum health
            this.health = 100;
        } else {
            this.health = health;
        }
        System.out.println("Health is now: " + this.health);
    }


    public void savePlayerData(Player player, String filePath) {
        List<String> playerData = new ArrayList<>();
        playerData.add(player.getName() + "," + player.getGold() + "," + player.getHealth());
        FileIO.saveData(playerData, filePath, "name,gold,health");
    }

    public void saveInventoryToFile(String filePath) {
        List<String> inventoryData = new ArrayList<>();

        // Add each item’s name or a unique identifier to the list
        for (Loot loot : inventory) {
            inventoryData.add(loot.getName());
        }

        for (Weapon weapon : weapons) {
            inventoryData.add(weapon.getName());
        }

        // Save the list of items to the inventory file
        FileIO.saveData(inventoryData, filePath, "itemName");
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", gold=" + gold +
                ", health=" + health +
                '}';
    }


}