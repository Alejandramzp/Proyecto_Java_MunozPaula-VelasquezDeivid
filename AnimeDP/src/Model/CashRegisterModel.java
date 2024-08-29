
package Model;


public class CashRegisterModel {
    private int cashRegisterID;
    private String status;
    private double openingAmount;
    private double closingAmount;
    private int businessStaffID;

    public CashRegisterModel() {
    }

    public CashRegisterModel(int cashRegisterID, double openingAmount, int businessStaffID) {
        this.cashRegisterID = cashRegisterID;
        this.status = "Inactivo";
        this.openingAmount = openingAmount;
        this.closingAmount = 0;
        this.businessStaffID = businessStaffID;
    }
    
    // Getters y Setters

    public int getCashRegisterID() { return cashRegisterID; }
    public void setCashRegisterID(int cashRegisterID) { this.cashRegisterID = cashRegisterID; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getOpeningAmount() { return openingAmount; }
    public void setOpeningAmount(double openingAmount) {this.openingAmount = openingAmount; }

    public double getClosingAmount() { return closingAmount; }
    public void setClosingAmount(double closingAmount) { this.closingAmount = closingAmount; }

    public int getBusinessStaffID() { return businessStaffID; }
    public void setBusinessStaffID(int businessStaffID) { this.businessStaffID = businessStaffID; }

}
