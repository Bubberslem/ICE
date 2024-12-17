import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int gold;
    private int health;
    private List<Weapon> weapons;
    private List<Armor> armors;
    private List<Loot> inventory;
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
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty");
        } else {
            System.out.println("=== Weapons ===");
            for (Weapon weapon : weapons) {
                System.out.println(weapon);
            }
            System.out.println("\n=== Armor ===");
            for (Armor armor : armors) {
                System.out.println(armor);
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

    public void saveInventory(Player player, String inventoryFilePath) {
        List<String> inventoryData = new ArrayList<>();
        for (Weapon weapon : player.getWeapons()) {
            inventoryData.add("Weapon," + weapon.getName() + "," + weapon.getValue() + "," + weapon.getDamage() + "," + weapon.getDurability());
        }
        for (Armor armor : player.getArmors()) {
            inventoryData.add("Armor," + armor.getName() + "," + armor.getValue() + "," + armor.getDefense() + "," + armor.getDurability());
        }
        FileIO.saveData(inventoryData, inventoryFilePath, "type,name,value,stat1,stat2");
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