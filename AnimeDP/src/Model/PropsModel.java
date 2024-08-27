
package Model;


public class PropsModel {
    private int id;
    private String name;
    private int quantity;
    private String status;
    private int eventId;

    public PropsModel() {
    }

    public PropsModel(int id, String name, int quantity, String status, int eventId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.eventId = eventId;
    }
    
    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    
}
