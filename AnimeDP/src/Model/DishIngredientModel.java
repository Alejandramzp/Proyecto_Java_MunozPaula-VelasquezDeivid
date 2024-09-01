
package Model;


public class DishIngredientModel {
    private int dishID;
    private int ingredientID;
    private double requiredQuantity;

    public DishIngredientModel(int dishID, int ingredientID, double requiredQuantity) {
        this.dishID = dishID;
        this.ingredientID = ingredientID;
        this.requiredQuantity = requiredQuantity;
    }

    public int getDishID() { return dishID; }
    public int getIngredientID() { return ingredientID; }
    public double getRequiredQuantity() { return requiredQuantity; }

    public void setDishID(int dishID) { this.dishID = dishID; }
    public void setIngredientID(int ingredientID) { this.ingredientID = ingredientID; }
    public void setRequiredQuantity(double requiredQuantity) { this.requiredQuantity = requiredQuantity; }
      
}


