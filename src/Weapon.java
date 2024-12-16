public class Weapon {
    String name;
    int damage;
    int value;
    int durability;
    public Weapon(String name,int value, int damage, int durability) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
        this.value = value;
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }
    public void decreaseDurability() {
        durability -= 1;
    }

    public int getValue() {
        return value;
    }
    public String getName() {
        return name;
    }
}
