package stock;

import java.util.ArrayList;


public class ItemList {

    private static ArrayList<Electronic> electronics = new ArrayList<Electronic>();
    private static ArrayList<Furniture> furnitures = new ArrayList<Furniture>();

    // load sample data
    static {
        Furniture chair1 = new Furniture(10, "teacher chair 1", "description 1", "wood");
        Furniture table1 = new Furniture(25, "teacher table 2", "description 2", "wood");
        Furniture chair2 = new Furniture(12, "teacher chair 3", "description 3", "wood");
        Furniture table2 = new Furniture(22, "teacher table 4", "description 4", "wood");
        Furniture chair3 = new Furniture(11, "teacher chair 5", "description 5", "wood");
        Furniture table3 = new Furniture(24, "teacher table 5", "description 6", "wood");
        Furniture chair4 = new Furniture(19, "teacher chair 7", "description 7", "wood");
        Furniture table4 = new Furniture(26, "teacher table 8", "description 7", "wood");
        furnitures.add(chair1);
        furnitures.add(table1);
        furnitures.add(chair2);
        furnitures.add(table2);
        furnitures.add(chair3);
        furnitures.add(table3);
        furnitures.add(chair4);
        furnitures.add(table4);

        Electronic fan1 = new Electronic(50, "table fan", "fan description", "AC");
        Electronic tv1 = new Electronic(5, "smart tv", "tv description", "AC");
        Electronic fan2 = new Electronic(50, "table fan", "fan description", "AC");
        Electronic tv2 = new Electronic(5, "smart tv", "tv description", "AC");
        Electronic fan3 = new Electronic(50, "table fan", "fan description", "AC");
        Electronic tv3 = new Electronic(5, "smart tv", "tv description", "AC");
        electronics.add(fan1);
        electronics.add(tv1);
        electronics.add(fan2);
        electronics.add(tv2);
        electronics.add(fan3);
        electronics.add(tv3);
    }

    public static ArrayList<Electronic> getElectronics() {
        return electronics;
    }

    public static void addElectronics(Electronic newElectronics) {
        electronics.add(newElectronics);
    }

    public static void removeElectronicByIndex(int index) {
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

    // formate data for the stock table
    public static javax.swing.table.DefaultTableModel getFurnitureDataForTable() {

        
        String[][] data = new String[ItemList.getFurnitures().size()][4];
        
        for (int i = 0; i < ItemList.getFurnitures().size(); i++) {
            for (int j = 0; j < 4; j++) {
                if(j==0){
                    data[i][j] = ItemList.getFurnitures().get(i).getName();
                }
                if(j==1){
                    data[i][j] = ItemList.getFurnitures().get(i).getDescription();
                }
                if(j==2){
                    String strCount = Integer.toString(ItemList.getFurnitures().get(i).getCount());
                    data[i][j] = strCount;
                }
                if(j==3){
                    data[i][j] = ItemList.getFurnitures().get(i).getMaterial();
                }
            }}

        return new javax.swing.table.DefaultTableModel(
                data,
                new String[] {
                        "Name", "Description", "Quantity", "Material"
                });
    }

    public static javax.swing.table.DefaultTableModel getElectronicDataForTable() {
        String[][] data = new String[ItemList.getElectronics().size()][4];
        
        for (int i = 0; i < ItemList.getElectronics().size(); i++) {
            for (int j = 0; j < 4; j++) {
                if(j==0){
                    data[i][j] = ItemList.getElectronics().get(i).getName();
                }
                if(j==1){
                    data[i][j] = ItemList.getElectronics().get(i).getDescription();
                }
                if(j==2){
                    String strCount = Integer.toString(ItemList.getElectronics().get(i).getCount());
                    data[i][j] = strCount;
                }
                if(j==3){
                    data[i][j] = ItemList.getElectronics().get(i).getPowerType();
                }
            }}

        return new javax.swing.table.DefaultTableModel(
                data,
                new String[] {
                        "Name", "Description", "Quantity", "Power Type"
                });
    }
}
