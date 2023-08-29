package au.edu.usc;

import java.util.ArrayList;

public class Commands {
    private final ArrayList<InventoryItems> inventory; // Use ArrayList as inventory
    private double totalProfit = 0; // Track total profit

    public Commands(Inventory inventory) {
        this.inventory = inventory.getItems();
    }

    public void executeCommand(String[] tokens) {
        String command = tokens[0];
        switch (command) {
            case "ADD":
                ADD(tokens);
                break;
            case "SELL":
                SELL(tokens);
                break;
            case "RETURN":
                RETURN(tokens);
                break;
            case "DONATE":
                DONATE(tokens);
                break;
            case "WRITEOFF":
                WRITEOFF(tokens);
                break;
            case "CHECK":
                CHECK();
                break;
            case "PROFIT":
                profit();
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    private void ADD(String[] tokens) {
        String itemName = tokens[1];
        int quantity = Integer.parseInt(tokens[2]);
        double price = Double.parseDouble(tokens[3]);
        InventoryItems newItem = new InventoryItems(itemName, quantity, price);
        inventory.add(newItem);
    }

    private void SELL(String[] tokens) {
        String itemName = tokens[1];
        int quantity = Integer.parseInt(tokens[2]);
        double price = Double.parseDouble(tokens[3]);
        int soldQuantity = 0;


        for (InventoryItems item : inventory) {
            if (item.getItemName().equals(itemName) && item.getQuantity() > 0) {
                int sellableQuantity = Math.min(quantity - soldQuantity, item.getQuantity());
                if (quantity > sellableQuantity) {
                    System.out.println("ERROR: Sale quantity exceeds stock");
                    totalProfit = Double.NaN; // Set totalProfit NaN
                    return; // Exit the method
                }

                double soldProfit = sellableQuantity * (price - item.getPrice());
                totalProfit += soldProfit; // Update total profit

                item.setQuantity(item.getQuantity() - sellableQuantity); // Adjust the quantity of the item

                break;
            }
        }
    }

    private void WRITEOFF(String[] tokens) {
        String itemName = tokens[1];
        int quantity = Integer.parseInt(tokens[2]);

        for (InventoryItems item : inventory) {
            if (item.getItemName().equals(itemName) && item.getQuantity() > 0) {
                double writtenOffLoss = quantity * item.getPurchasePrice(); // Calculate loss

                if (quantity > item.getQuantity()) {
                    System.out.println("ERROR: Throwing out more stock than in inventory ");
                    totalProfit = Double.NaN; // Set totalProfit NaN
                    return; // Exit the method
                }

                totalProfit -= writtenOffLoss; // Deduct loss from profit
                // Adjust the quantity of the item
                item.setQuantity(item.getQuantity() - quantity);



                break;

            }
        }

        }

    private void DONATE(String[] tokens) {
        String itemName = tokens[1];
        int quantity = Integer.parseInt(tokens[2]);

        for (InventoryItems item : inventory) {
            if (item.getItemName().equals(itemName) && item.getQuantity() > 0) {
                if (quantity > item.getQuantity()){
                    System.out.println("ERROR: Donating out more stock than in inventory ");
                    totalProfit = Double.NaN; // Set totalProfit to NaN to ensure the error is seen
                    return; // Exit the method
                }
                // Donations don't affect profit/loss
                item.setQuantity(item.getQuantity() - quantity);


                break;
            }
        }
    }

    private void RETURN(String[] tokens) {
        String itemName = tokens[1];
        int quantity = Integer.parseInt(tokens[2]);
        double price = Double.parseDouble(tokens[3]);

        int returnedQuantity = 0; // Track the total quantity of items returned

        for (InventoryItems item : inventory) {
            if (item.getItemName().equals(itemName)) {
                int returnableQuantity = Math.min(quantity - returnedQuantity, item.getQuantity());
                double refundValue = returnableQuantity * (price - item.getPrice());
                totalProfit -= refundValue; // Deduct the profit for the returned items
                if (returnedQuantity >= quantity) {
                    break; // Exit the loop when the requested quantity is returned
                }
            }
        }
    }

    private void CHECK() {
        for (InventoryItems item : inventory) {
            if (item.getQuantity() > 0){
                System.out.println(item.getItemName() + ": " + item.getQuantity()); // prints all items quantities in inventory
            }
        }
    }

    private void profit() {
        // Track total loss
        double totalLoss = 0;
        // Track total cancellation for returned items
        double totalCancellation = 0;
        double totalProfitLoss = totalProfit - totalLoss - totalCancellation;
        String formattedTotalProfitLoss = String.format("%.2f", totalProfitLoss); // Format with 2 decimal places
        System.out.println("Profit/Loss: $" + formattedTotalProfitLoss);
    }
} // end of class
