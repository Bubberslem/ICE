public class Weapon {
    String name;
    int damage;
    int durability;
    public Weapon(String name, int damage, int durability) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
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
}
