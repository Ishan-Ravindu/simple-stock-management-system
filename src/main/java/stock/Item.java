package stock;

public class Item extends Quantity {
    private String name;
    private String description;

    public Item(int count, String name, String description) {
        super(count);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
