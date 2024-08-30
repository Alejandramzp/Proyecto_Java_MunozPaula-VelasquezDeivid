
package Model;

public class CosplayContestModel {
    private int cosplayContestID;
    private String name;
    private int categoryID;

    // Constructor
    public CosplayContestModel(int cosplayContestID, String name, int categoryID) {
        this.cosplayContestID = cosplayContestID;
        this.name = name;
        this.categoryID = categoryID;
    }

    // Getters y setters
    public int getCosplayContestID() {
        return cosplayContestID;
    }

    public void setCosplayContestID(int cosplayContestID) {
        this.cosplayContestID = cosplayContestID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "CosplayContestID: " + cosplayContestID + ", Name: " + name + ", CategoryID: " + categoryID;
    }
}


