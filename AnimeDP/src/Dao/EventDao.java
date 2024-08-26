/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// folder import
import Model.EventModel;
import Connection.Conexion;


public class EventDao {
    private Conexion conexion = new Conexion();

    public void InsertEvent(EventModel event){
        String sql = "INSERT INTO Event(Name, Country, City, Address, MaxPersonCapacity, MaxStoreCapacity, MaxRestaurantCapacity, Date, Time, Organizer, AgeRating, Status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.establecerConexion();
             PreparedStatement stms = conn.prepareStatement(sql)) {

            stms.setString(1, event.getName());
            stms.setString(2, event.getCountry());
            stms.setString(3, event.getCity());
            stms.setString(4, event.getAddress());
            stms.setInt(5, event.getMaxPersonCapacity());
            stms.setInt(6, event.getMaxStoreCapacity());
            stms.setInt(7, event.getMaxRestaurantCapacity());
            stms.setString(8, event.getDate());
            stms.setString(9, event.getTime());
            stms.setString(10, event.getOrganizer());
            stms.setString(11, event.getAgeRating());
            stms.setString(12, event.getStatus());

            stms.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public EventModel ViewEventID(int id){
        String sql = "SELECT * FROM Event WHERE EventID = ?";
        try(Connection conn = new Conexion().establecerConexion();
                PreparedStatement stms = conn.prepareStatement(sql)){
            
            stms.setInt(1, id);
            
            ResultSet rs = stms.executeQuery();
            
            if (rs.next()){
                return new EventModel(rs.getInt("EventID"), rs.getString("Name"), rs.getString("Country"), rs.getString("City"), rs.getString("Address"),
                rs.getInt("MaxPersonCapacity"), rs.getInt("MaxStoreCapacity"), rs.getInt("MaxRestaurantCapacity"), rs.getString("Date"),
                rs.getString("Time"), rs.getString("Organizer"), rs.getString("AgeRating"), rs.getString("Status"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<EventModel> ViewEvent(){
        List<EventModel> event = new ArrayList<>();
        try(Connection conn = new Conexion().establecerConexion()){
            String sql = "SELECT * FROM Event";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int EventID = rs.getInt("EventID");
                String Name = rs.getString("Name"); 
                String Country = rs.getString("Country"); 
                String City = rs.getString("City");
                String Address = rs.getString("Address");
                int MaxPersonCapacity = rs.getInt("MaxPersonCapacity");
                int MaxStoreCapacity = rs.getInt("MaxStoreCapacity");
                int MaxRestaurantCapacity = rs.getInt("MaxRestaurantCapacity");
                String Date = rs.getString("Date");
                String Time = rs.getString("Time"); 
                String Organizer = rs.getString("Organizer");
                String AgeRating = rs.getString("AgeRating"); 
                String Status = rs.getString("Status");
                
                EventModel events = new EventModel(EventID, Name, Country, City, Address, 
                        MaxPersonCapacity, MaxStoreCapacity, MaxRestaurantCapacity, Date,
                        Time, Organizer, AgeRating, Status);
                event.add(events);
            }
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return event;
        
    }
}
