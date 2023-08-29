package au.edu.usc;

import java.util.ArrayList;


public class Inventory {
    private final ArrayList<InventoryItems> items; // Use an ArrayList to store items

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public ArrayList<InventoryItems> getItems() {
        return items;
    }

} // end of class

