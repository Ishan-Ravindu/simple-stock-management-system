package stock;

import java.util.ArrayList;


public class ItemList {
    private ArrayList<Electronic> electronics = new ArrayList<Electronic>();
    private ArrayList<Furniture> furnitures= new ArrayList<Furniture>();

    public ArrayList<Electronic> getElectronics() {
        return electronics;
    }

    public void addElectronics(Electronic electronics) {
        this.electronics.add(electronics);
    }

    public void removeElectronicByIndex(int index) {
        this.electronics.remove(index);
    }
    
    public ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }

    public void addFurnitures(Furniture furnitures) {
        this.furnitures.add(furnitures);
    }
    public void removeFurnitureByIndex(int index) {
        this.furnitures.remove(index);
    }

    
}
