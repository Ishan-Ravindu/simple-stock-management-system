package stock;

public class Furniture extends Item {
    private String material;

    public Furniture(int count, String name, String description, String material) {
        super(count, name, description);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

}
