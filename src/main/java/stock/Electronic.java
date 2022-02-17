package stock;

public class Electronic extends Item{
    private String powerType;



    public Electronic(int count, String name, String description, String powerType) {
        super(count, name, description);
        this.powerType = powerType;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }
    
}
