public class Armor {
    String name;
    int defense;
    int durability;
    public Armor(String name, int value, int defense, int durability) {
        this.name = name;
        this.defense = defense;
        this.durability = durability;
    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }
    public void decreaseDurability() {
        durability -= 1;
    }

    public String getName() {
        return name;
    }
}
