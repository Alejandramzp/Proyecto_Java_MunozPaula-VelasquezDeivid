
package Model;


public class IngredientInventoryModel {
    private int ingredientInventoryID;
    private int businessID;
    private int ingredientID;
    private double availableQuantity;

    public IngredientInventoryModel(int ingredientInventoryID, int businessID, int ingredientID, double availableQuantity) {
        this.ingredientInventoryID = ingredientInventoryID;
        this.businessID = businessID;
        this.ingredientID = ingredientID;
        this.availableQuantity = availableQuantity;
    }

    public int getIngredientInventoryID() { return ingredientInventoryID; }
    public int getBusinessID() { return businessID; }
    public int getIngredientID() { return ingredientID; }
    public double getAvailableQuantity() { return availableQuantity; }

    public void setIngredientInventoryID(int ingredientInventoryID) { this.ingredientInventoryID = ingredientInventoryID; }
    public void setBusinessID(int businessID) { this.businessID = businessID; }
    public void setIngredientID(int ingredientID) { this.ingredientID = ingredientID; }
    public void setAvailableQuantity(double availableQuantity) { this.availableQuantity = availableQuantity; }
       
}

