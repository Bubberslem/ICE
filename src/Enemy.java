public class Enemy extends NPC{
    private int damage;
    private int health;
    public Enemy(String name, boolean isFriendly, int damage, int health) {
        super(name, false);
        this.damage = damage;
        this.health = health;
    }
   public int getDamage() {
        return damage;
    }
    public void attack(Player player){
        System.out.println(getName()+"attacks "+player.getName()+" for " + damage + " damage!");
        player.takeDamage(damage);
    }
    public void takeDamage(int damage){
        health -= damage;
    }
}
