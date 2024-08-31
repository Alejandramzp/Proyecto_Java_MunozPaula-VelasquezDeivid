package Model;

public class OrderItemModel {
    private int orderItemID;
    private int orderID;
    private String itemName;
    private int quantity;
    private double individualValue;

    public OrderItemModel(int orderID, String itemName, int quantity, double individualValue) {
        this.orderID = orderID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.individualValue = individualValue;
    }

    // Getters y Setters
    public int getOrderItemID() { return orderItemID; }
    public void setOrderItemID(int orderItemID) { this.orderItemID = orderItemID; }

    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getIndividualValue() { return individualValue; }
    public void setIndividualValue(double individualValue) { this.individualValue = individualValue; }
}
