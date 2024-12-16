abstract class NPC {
    private String name;
    private boolean isFriendly;
    public NPC(String name, boolean isFriendly) {
        this.name = name;
        this.isFriendly = isFriendly;

    }
    public String getName() {
        return name;
    }
    public boolean isFriendly() {
        return isFriendly;
    }
}
