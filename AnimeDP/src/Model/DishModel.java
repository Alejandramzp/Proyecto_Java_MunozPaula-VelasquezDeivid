
package Model;


public class DishModel {
    private int dishID;
    private String description;
    private String type; // 'Entrada', 'Bebida', 'Plato Fuerte', 'Aperitivo'
    private int preparationTimeMinutes;

    public DishModel(int dishID, String description, String type, int preparationTimeMinutes) {
        this.dishID = dishID;
        this.description = description;
        this.type = type;
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public int getDishID() { return dishID; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public int getPreparationTimeMinutes() { return preparationTimeMinutes; }

    public void setDishID(int dishID) { this.dishID = dishID; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setPreparationTimeMinutes(int preparationTimeMinutes) { this.preparationTimeMinutes = preparationTimeMinutes; }
       
}

