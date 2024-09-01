
package Model;


public class IngredientModel {
    private int ingredientID;
    private String name;
    private String description;
    private String supplier;

    public IngredientModel(int ingredientID, String name, String description, String supplier) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.description = description;
        this.supplier = supplier;
    }

    public int getIngredientID() { return ingredientID; }
    public String getName() { return name;}
    public String getDescription() { return description; }
    public String getSupplier() { return supplier; }

    public void setIngredientID(int ingredientID) { this.ingredientID = ingredientID; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
 
}

