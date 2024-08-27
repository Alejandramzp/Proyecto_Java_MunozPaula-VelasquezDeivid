/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    // Constructor
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
        this.status = "Pendiente"; // Estado inicial siempre "Pendiente"
    }

    public EventModel(String name, String country, String city, String address, int maxPersonCapacity, int maxStoreCapacity, int maxRestaurantCapacity, String date, String time, String organizer, String ageRating, String status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters y Setters

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public int getMaxPersonCapacity() { return maxPersonCapacity; }
    public int getMaxStoreCapacity() { return maxStoreCapacity; }
    public int getMaxRestaurantCapacity() { return maxRestaurantCapacity; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getOrganizer() { return organizer; }
    public String getAgeRating() { return ageRating; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", maxPersonCapacity=" + maxPersonCapacity +
                ", maxStoreCapacity=" + maxStoreCapacity +
                ", maxRestaurantCapacity=" + maxRestaurantCapacity +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", organizer='" + organizer + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
