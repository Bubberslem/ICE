import java.util.ArrayList;
import java.util.List;

public class Shopkeeper {
    private List<Weapon> weaponsForSale;
    private List<Armor> armorForSale;
    private List<Loot> itemsForSale;

    public Shopkeeper() {
        weaponsForSale = new ArrayList<>();
        armorForSale = new ArrayList<>();
        itemsForSale = new ArrayList<>();
        populateShopInventory();
    }
    private void populateShopInventory() {
       weaponsForSale = FileIO.loadWeapons("data/weapons.csv");
       armorForSale = FileIO.loadArmor("data/armors.csv");
       itemsForSale = FileIO.loadLoot("data/loot.csv");
    }
    public void displayShopInventory() {
        displayWeapons();
        displayArmors();
        displayItems();
    }

    public void displayWeapons() {
        System.out.println("=== Weapons for Sale ===");
        for (int i = 0; i < weaponsForSale.size(); i++) {
            Weapon weapon = weaponsForSale.get(i);
            System.out.println((i + 1) + ". " + weapon.getName() + " - "
                    + weapon.getValue() + " gold (Damage: "
                    + weapon.getDamage() + ", Durability: "
                    + weapon.getDurability() + ")");
        }
    }
    public void displayArmors() {
        System.out.println("\n=== Armors for Sale ===");
        for (int i = 0; i < armorForSale.size(); i++) {
            Armor armor = armorForSale.get(i);
            System.out.println((i + 1) + ". " + armor.getName() + " - "
                    + armor.getValue() + " gold (Defense: "
                    + armor.getDefense() + ", Durability: "
                    + armor.getDurability() + ")");
        }
    }

    public void displayItems() {
        System.out.println("\n=== Items for Sale ===");
        for (int i = 0; i < itemsForSale.size(); i++) {
            Loot item = itemsForSale.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - "
                    + item.getValue() + " gold (" + item.getDescription() + ")");
        }
    }

    public void buyItem(Player player, int category, int index) {
        switch (category) {
            case 1: // Weapons

                if (index > 0 && index <= weaponsForSale.size()) {
                    Weapon weapon = weaponsForSale.get(index - 1);
                    purchaseItem(player, weapon);
                    weaponsForSale.remove(index - 1); // Remove the item from shop after purchase
                } else {
                    System.out.println("Invalid selection!");
                }
                break;
            case 2: // Armors
                if (index > 0 && index <= armorForSale.size()) {
                    Armor armor = armorForSale.get(index - 1);
                    purchaseItem(player, armor);
                    armorForSale.remove(index - 1); // Remove the item from shop after purchase
                } else {
                    System.out.println("Invalid selection!");
                }
                break;
            case 3: // Items
                if (index > 0 && index <= itemsForSale.size()) {
                    Loot item = itemsForSale.get(index - 1);
                    purchaseItem(player, item);
                    itemsForSale.remove(index - 1); // Remove the item from shop after purchase
                } else {
                    System.out.println("Invalid selection!");
                }
                break;
            default:
                System.out.println("Invalid category!");
        }
    }

    private void purchaseItem(Player player, Loot item) {
        if (player.getGold() >= item.getValue()) {
            player.setGold(player.getGold() - item.getValue());
            player.addItem(item);
            System.out.println("You purchased: " + item.getName());
        } else {
            System.out.println("You don't have enough gold to buy " + item.getName());
        }
    }

    private void purchaseItem(Player player, Weapon weapon) {
        if (player.getGold() >= weapon.getValue()) {
            player.setGold(player.getGold() - weapon.getValue());
            player.addWeapon(weapon);
            System.out.println("You purchased: " + weapon.getName());
        } else {
            System.out.println("You don't have enough gold to buy " + weapon.getName());
        }
    }

    private void purchaseItem(Player player, Armor armor) {
        if (player.getGold() >= armor.getValue()) {
            player.setGold(player.getGold() - armor.getValue());
            player.addArmor(armor);
            System.out.println("You purchased: " + armor.getName());
        } else {
            System.out.println("You don't have enough gold to buy " + armor.getName());
        }
    }
}
