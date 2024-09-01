
package Model;

public class StoreInventory {
    private int inventoryID;
    private int businessID;
    private String productName;
    private String description;
    private String manufacturer;
    private String type;
    private int availableQuantity;
    private double individualPrice;

    public StoreInventory(int inventoryID, int businessID, String productName, String description, 
                          String manufacturer, String type, int availableQuantity, double individualPrice) {
        this.inventoryID = inventoryID;
        this.businessID = businessID;
        this.productName = productName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.type = type;
        this.availableQuantity = availableQuantity;
        this.individualPrice = individualPrice;
    }

    // Getters y Setters
    public int getInventoryID() { return inventoryID; }
    public int getBusinessID() { return businessID; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public String getManufacturer() { return manufacturer; }
    public String getType() { return type; }
    public int getAvailableQuantity() { return availableQuantity; }
    public double getIndividualPrice() { return individualPrice; }

    public void setInventoryID(int inventoryID) { this.inventoryID = inventoryID; }
    public void setBusinessID(int businessID) { this.businessID = businessID; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setDescription(String description) { this.description = description; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setType(String type) { this.type = type; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    public void setIndividualPrice(double individualPrice) { this.individualPrice = individualPrice; }
}
