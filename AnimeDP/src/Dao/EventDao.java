/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.EventModel;
import Connection.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public boolean addEvent(EventModel event) {
        String sql = "INSERT INTO Event (Name, Country, City, Address, MaxPersonCapacity, MaxStoreCapacity, MaxRestaurantCapacity, Date, Time, Organizer, AgeRating, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getCountry());
            stmt.setString(3, event.getCity());
            stmt.setString(4, event.getAddress());
            stmt.setInt(5, event.getMaxPersonCapacity());
            stmt.setInt(6, event.getMaxStoreCapacity());
            stmt.setInt(7, event.getMaxRestaurantCapacity());
            stmt.setString(8, event.getDate());
            stmt.setString(9, event.getTime());
            stmt.setString(10, event.getOrganizer());
            stmt.setString(11, event.getAgeRating());
            stmt.setString(12, event.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir evento: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public List<EventModel> getAllEvents() {
        List<EventModel> events = new ArrayList<>();
        String sql = "SELECT * FROM Event";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                EventModel event = new EventModel(
                        rs.getInt("EventID"),
                        rs.getString("Name"),
                        rs.getString("Country"),
                        rs.getString("City"),
                        rs.getString("Address"),
                        rs.getInt("MaxPersonCapacity"),
                        rs.getInt("MaxStoreCapacity"),
                        rs.getInt("MaxRestaurantCapacity"),
                        rs.getString("Date"),
                        rs.getString("Time"),
                        rs.getString("Organizer"),
                        rs.getString("AgeRating")
                );
                event.setStatus(rs.getString("Status"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener eventos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return events;
    }

    public EventModel getEventById(int eventId) {
    String sql = "SELECT * FROM Event WHERE EventID = ?";
    Conexion conexion = new Conexion();
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        connection = conexion.establecerConexion();
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, eventId);
        rs = stmt.executeQuery();

        if (rs.next()) {
            EventModel event = new EventModel();
            event.setId(rs.getInt("EventID"));
            event.setName(rs.getString("Name"));
            event.setDate(rs.getString("Date"));
            event.setStatus(rs.getString("Status"));
            return event;
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el evento por ID: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
        return null;
    }

    public boolean isEventNameExists(String name) {
        String sql = "SELECT COUNT(*) FROM Event WHERE Name = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si el nombre del evento existe: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return exists;
    }

    public boolean updateEventStatus(EventModel event) {
        String sql = "UPDATE Event SET Status = ? WHERE EventID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, event.getStatus());
            stmt.setInt(2, event.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del evento: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

