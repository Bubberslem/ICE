public class Enemy extends NPC{
    private int damage;
    private int health;
    public Enemy(String name, boolean isFriendly, int damage, int health) {
        super(name, false);
        this.damage = damage;
        this.health = health;
    }
    public void attack(Player player) {
        System.out.println(getName() + " attacks " + player.getName() + " for " + damage + " damage!");
        player.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(getName() + " has been defeated!");
            // Add loot or other rewards upon enemy defeat
        }
    }
    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
