package stock;

public class StockTest {

    public static void main(String[] args) {
        ItemList itemList = new ItemList();
        // load sample data
        Furniture chair = new Furniture(10, "teacher chair", "description", "wood");
        Furniture table = new Furniture(20, "teacher table", "description", "wood");
        itemList.addFurnitures(chair);
        itemList.addFurnitures(table);
        Electronic fan = new Electronic(50, "table fan", "fan description", "AC");
        Electronic tv = new Electronic(5, "smart tv", "tv description", "AC");
        itemList.addElectronics(fan);
        itemList.addElectronics(tv);

        System.out.println(itemList.getElectronics().get(1).getCount()); 
        itemList.getElectronics().get(1).setCount(10);
        System.out.println(itemList.getElectronics().get(1).getCount()); 
    }

}
