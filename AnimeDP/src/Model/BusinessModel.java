
package Model;


public class BusinessModel {
    
    private int businessID;
    private String name;
    private String type;
    private int inChargeID;

    public BusinessModel(int businessID, String name, String type, int inChargeID) {
        this.businessID = businessID;
        this.name = name;
        this.type = type;
        this.inChargeID = inChargeID;
    }

    // Getters y Setters
    public int getBusinessID() { return businessID; }
    public void setBusinessID(int businessID) { this.businessID = businessID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getInChargeID() { return inChargeID; }
    public void setInChargeID(int inChargeID) { this.inChargeID = inChargeID; }
}
