package stock;

import java.util.ArrayList;


public class ItemList {
    private static ArrayList<Electronic> electronics = new ArrayList<Electronic>();
    private static ArrayList<Furniture> furnitures= new ArrayList<Furniture>();

    //load sample data
    static{
        Furniture chair = new Furniture(10, "teacher chair", "description", "wood");
        Furniture table = new Furniture(20, "teacher table", "description", "wood");
        furnitures.add(chair);
        furnitures.add(table);

        Electronic fan = new Electronic(50, "table fan", "fan description", "AC");
        Electronic tv = new Electronic(5, "smart tv", "tv description", "AC");
        electronics.add(fan);
        electronics.add(tv);
    }
    
    public static ArrayList<Electronic> getElectronics() {
        return electronics;
    }

    public static void addElectronics(Electronic newElectronics) {
        electronics.add(newElectronics);
    }

    public  static void removeElectronicByIndex(int index) {
        electronics.remove(index);
    }
    
    public static ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }

    public static void addFurnitures(Furniture newFurnitures) {
        furnitures.add(newFurnitures);
    }
    public static void removeFurnitureByIndex(int index) {
        furnitures.remove(index);
    }

    
}
