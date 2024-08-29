
package Model;

public class OrderModel {
    private int orderID;
    private int visitorID;
    private int businessID;
    private int cashRegisterID;
    private double totalValue;
    private String status; // Registrado, Pagado, Entregado

    public OrderModel(int visitorID, int businessID, int cashRegisterID, double totalValue) {
        this.visitorID = visitorID;
        this.businessID = businessID;
        this.cashRegisterID = cashRegisterID;
        this.totalValue = totalValue;
        this.status = "Registrado"; // Estado inicial
    }

    // Getters y Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public int getVisitorID() { return visitorID; }
    public void setVisitorID(int visitorID) { this.visitorID = visitorID; }

    public int getBusinessID() { return businessID; }
    public void setBusinessID(int businessID) { this.businessID = businessID; }

    public int getCashRegisterID() { return cashRegisterID; }
    public void setCashRegisterID(int cashRegisterID) { this.cashRegisterID = cashRegisterID; }

    public double getTotalValue() { return totalValue; }
    public void setTotalValue(double totalValue) { this.totalValue = totalValue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
