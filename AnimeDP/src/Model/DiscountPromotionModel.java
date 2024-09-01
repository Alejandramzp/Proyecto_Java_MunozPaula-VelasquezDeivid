
package Model;

public class DiscountPromotionModel {
    private int discountID;
    private int inventoryID;
    private String description;
    private String type; // combo, 2x1, mejor precio por cantidad, etc.
    private double discountValue;

    // Constructor
    public DiscountPromotionModel(int discountID, int inventoryID, String description, String type, double discountValue) {
        this.discountID = discountID;
        this.inventoryID = inventoryID;
        this.description = description;
        this.type = type;
        this.discountValue = discountValue;
    }

    // Getters and Setters
    public int getDiscountID() { return discountID; }
    public void setDiscountID(int discountID) { this.discountID = discountID; }

    public int getInventoryID() { return inventoryID; }
    public void setInventoryID(int inventoryID) { this.inventoryID = inventoryID; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
}
