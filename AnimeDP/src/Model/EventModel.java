/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class EventModel {
    
    private int EventID;
    private String Name;
    private String Country;
    private String City;
    private String Address;
    private int MaxPersonCapacity;
    private int MaxStoreCapacity;
    private int MaxRestaurantCapacity;
    private String Date;
    private String Time;
    private String Organizer;
    private String AgeRating;
    private String Status;

    public EventModel(int EventID, String Name, String Country, String City, String Address, int MaxPersonCapacity, int MaxStoreCapacity, int MaxRestaurantCapacity, String Date, String Time, String Organizer, String AgeRating, String Status) {
        this.EventID = EventID;
        this.Name = Name;
        this.Country = Country;
        this.City = City;
        this.Address = Address;
        this.MaxPersonCapacity = MaxPersonCapacity;
        this.MaxStoreCapacity = MaxStoreCapacity;
        this.MaxRestaurantCapacity = MaxRestaurantCapacity;
        this.Date = Date;
        this.Time = Time;
        this.Organizer = Organizer;
        this.AgeRating = AgeRating;
        this.Status = Status;
    }

    public int getEventID() {
        return EventID;
    }

    public void setEventID(int EventID) {
        this.EventID = EventID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getMaxPersonCapacity() {
        return MaxPersonCapacity;
    }

    public void setMaxPersonCapacity(int MaxPersonCapacity) {
        this.MaxPersonCapacity = MaxPersonCapacity;
    }

    public int getMaxStoreCapacity() {
        return MaxStoreCapacity;
    }

    public void setMaxStoreCapacity(int MaxStoreCapacity) {
        this.MaxStoreCapacity = MaxStoreCapacity;
    }

    public int getMaxRestaurantCapacity() {
        return MaxRestaurantCapacity;
    }

    public void setMaxRestaurantCapacity(int MaxRestaurantCapacity) {
        this.MaxRestaurantCapacity = MaxRestaurantCapacity;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getOrganizer() {
        return Organizer;
    }

    public void setOrganizer(String Organizer) {
        this.Organizer = Organizer;
    }

    public String getAgeRating() {
        return AgeRating;
    }

    public void setAgeRating(String AgeRating) {
        this.AgeRating = AgeRating;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "EventModel{" + "EventID= " + EventID + ", Name= " + Name + ", Country= " + Country + ", City= " + City + ", Address= " + Address + ", MaxPersonCapacity= " + MaxPersonCapacity + ", MaxStoreCapacity= " + MaxStoreCapacity + ", MaxRestaurantCapacity= " + MaxRestaurantCapacity + ", Date= " + Date + ", Time= " + Time + ", Organizer= " + Organizer + ", AgeRating= " + AgeRating + ", Status= " + Status + '}';
    }
    
    
}
