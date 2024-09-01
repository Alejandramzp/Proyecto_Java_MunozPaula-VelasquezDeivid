
package Model;


public class RestaurantMenuModel {
    private int businessID;
    private int dishID;
    private int availableQuantity;

    public RestaurantMenuModel(int businessID, int dishID, int availableQuantity) {
        this.businessID = businessID;
        this.dishID = dishID;
        this.availableQuantity = availableQuantity;
    }

    public int getBusinessID() { return businessID; }
    public int getDishID() { return dishID; }
    public int getAvailableQuantity() { return availableQuantity; }

    public void setBusinessID(int businessID) { this.businessID = businessID; }
    public void setDishID(int dishID) { this.dishID = dishID;}
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
       
}

