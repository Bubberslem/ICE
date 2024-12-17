public class Armor {
    String name;
    int defense;
    int durability;
    int value;
    public Armor(String name, int value, int defense, int durability) {
        this.name = name;
        this.defense = defense;
        this.durability = durability;
        this.value = value;
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

    public int getValue() {
        return value;
    }
}
