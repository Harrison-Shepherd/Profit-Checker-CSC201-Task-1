package au.edu.usc;

public class InventoryItems {
    private final String itemName;
    private int quantity;
    public double price;
    private final double purchasePrice; // Add this field

    public InventoryItems(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.purchasePrice = price; // Set purchase price initially to the same as the price
    }

    // Implement getters and setters
    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }



    public double getPurchasePrice() {
        return purchasePrice;
    }


} // end of class
