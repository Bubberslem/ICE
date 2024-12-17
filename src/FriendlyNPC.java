public class FriendlyNPC extends NPC {
    TextUI ui = new TextUI();
    public FriendlyNPC(String name) {
        super(name, true);
    }

    public void offerHelp(Player player) {
        ui.displayMsg(getName() + " offers to help you.");
        String choice = ui.promptText("Do you accept the help? (yes/no): ");
        if ("yes".equalsIgnoreCase(choice)) {
            player.increaseHealth(50);
            ui.displayMsg(getName() + " healed you. Health: " + player.getHealth());
        } else {
            ui.displayMsg(getName() + " walks away.");
        }
    }
}
