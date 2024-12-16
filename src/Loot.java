public class Loot {
    private String name;
    private int value;
    private String type;

    public Loot(String name, int value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;

    }
    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public String getType() {
        return type;
    }
}