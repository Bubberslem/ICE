import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int gold;
    private int health;
    private List<Weapon> weapons;
    private List<Armor> armors;
    private List<Loot> inventory;

    public Player(String name, int gold, int health) {
        this.name = name;
        this.gold = gold;
        this.health = health;
        this.weapons = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.inventory = new ArrayList<>();
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
    public void increaseHealth(int amount) {
        health += amount;
    }

    public void addToInventory(Loot loot){
        inventory.add(loot);
    }

    public void equipWeapon(){

    }

    public void equipArmor(){

    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addArmor(Armor armor) {
        armors.add(armor);
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