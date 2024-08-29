/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventAccountingDao {

    // Obtener el aforo máximo de un evento
    public int getMaxPersonCapacity(int eventID) {
        String sql = "SELECT MaxPersonCapacity FROM Event WHERE EventID = ?";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, eventID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaxPersonCapacity");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el aforo máximo del evento: " + e.getMessage());
        }
        return 0;
    }
    
    // Verificar si una categoría existe en la base de datos
    public boolean categoryExists(int categoryID) {
        String sql = "SELECT COUNT(*) AS Total FROM Category WHERE CategoryID = ?";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total") > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia de la categoría: " + e.getMessage());
        }
        return false;
    }

    // Obtener el monto total recaudado por categoría
    public double getTotalAmountByCategory(int categoryID) {
        String sql = "SELECT SUM(TotalAmount) AS Total FROM EventAccounting WHERE ActivityID IN (SELECT ActivityID FROM Activity WHERE CategoryID = ?)";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Total");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el monto total por categoría: " + e.getMessage());
        }
        return 0.0;
    }

    // Obtener el total de boletos vendidos
    public int getTotalTicketsSold() {
        String sql = "SELECT SUM(TicketsSold) AS TotalTickets FROM EventAccounting";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalTickets");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el total de boletos vendidos: " + e.getMessage());
        }
        return 0;
    }

    // Obtener boletos disponibles para un evento específico
    public int getAvailableTickets(int eventID, int maxCapacity) {
        String sql = "SELECT COUNT(*) AS TicketsSold FROM Ticket WHERE TicketOfficeID IN (SELECT TicketOfficeID FROM TicketOffice WHERE EventID = ?)";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, eventID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int ticketsSold = rs.getInt("TicketsSold");
                return maxCapacity - ticketsSold;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener boletos disponibles: " + e.getMessage());
        }
        return 0;
    }

    // Obtener el número de participantes por actividad
    public int getParticipantsByActivity(int activityID) {
        String sql = "SELECT COUNT(*) AS Participants FROM ActivityParticipation WHERE ActivityID = ?";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, activityID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Participants");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener participantes por actividad: " + e.getMessage());
        }
        return 0;
    }
}


