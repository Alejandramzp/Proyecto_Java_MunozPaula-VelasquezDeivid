
package Model;

public class EventModel {
    private int id;
    private String name;
    private String country;
    private String city;
    private String address;
    private int maxPersonCapacity;
    private int maxStoreCapacity;
    private int maxRestaurantCapacity;
    private String date;
    private String time;
    private String organizer;
    private String ageRating;
    private String status;
    
    public EventModel() {
        
    }

    public EventModel(int id, String name, String country, String city, String address,
                      int maxPersonCapacity, int maxStoreCapacity, int maxRestaurantCapacity,
                      String date, String time, String organizer, String ageRating) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.maxPersonCapacity = maxPersonCapacity;
        this.maxStoreCapacity = maxStoreCapacity;
        this.maxRestaurantCapacity = maxRestaurantCapacity;
        this.date = date;
        this.time = time;
        this.organizer = organizer;
        this.ageRating = ageRating;
        this.status = "Pendiente"; // Valor por defecto
    }


    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getMaxPersonCapacity() { return maxPersonCapacity; }
    public void setMaxPersonCapacity(int maxPersonCapacity) { this.maxPersonCapacity = maxPersonCapacity; }
    public int getMaxStoreCapacity() { return maxStoreCapacity; }
    public void setMaxStoreCapacity(int maxStoreCapacity) { this.maxStoreCapacity = maxStoreCapacity; }
    public int getMaxRestaurantCapacity() { return maxRestaurantCapacity; }
    public void setMaxRestaurantCapacity(int maxRestaurantCapacity) { this.maxRestaurantCapacity = maxRestaurantCapacity; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }
    public String getAgeRating() { return ageRating; }
    public void setAgeRating(String ageRating) { this.ageRating = ageRating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
