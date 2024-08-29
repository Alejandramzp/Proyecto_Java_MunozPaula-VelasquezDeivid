
package Model;


public class BusinessStaffModel {
    private int staffID;
    private int businessID;

    public BusinessStaffModel(int staffID, int businessID) {
        this.staffID = staffID;
        this.businessID = businessID;
    }
    
    // Getters y Setters
    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }

    public int getBusinessID() { return businessID; }
    public void setBusinessID(int businessID) { this.businessID = businessID; }

}
