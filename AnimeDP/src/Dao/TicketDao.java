/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import Model.TicketModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    public boolean addTicket(TicketModel ticket) {
        String sql = "INSERT INTO Ticket (TicketName, Price, AgeRating, AdditionalCost, Status, VisitorID, TicketOfficeID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, ticket.getTicketName());
            stmt.setDouble(2, ticket.getPrice());
            stmt.setString(3, ticket.getAgeRating());
            stmt.setDouble(4, ticket.getAdditionalCost());
            stmt.setString(5, ticket.getStatus());
            stmt.setInt(6, ticket.getVisitorID());
            stmt.setInt(7, ticket.getTicketOfficeID());

            // Verificar existencia de VisitorID
            if (!isVisitorExists(ticket.getVisitorID(), connection)) {
                System.out.println("Error: El VisitorID no existe.");
                return false;
            }

            // Verificar existencia de TicketOfficeID
            if (!isTicketOfficeExists(ticket.getTicketOfficeID(), connection)) {
                System.out.println("Error: El TicketOfficeID no existe.");
                return false;
            }

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar ticket: " + e.getMessage());
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

    public List<TicketModel> getAllTickets() {
        List<TicketModel> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Ticket";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TicketModel ticket = new TicketModel(
                        rs.getInt("TicketID"),
                        rs.getString("TicketName"),
                        rs.getDouble("Price"),
                        rs.getString("AgeRating"),
                        rs.getDouble("AdditionalCost"),
                        rs.getString("Status"),
                        rs.getInt("VisitorID"),
                        rs.getInt("TicketOfficeID")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tickets: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return tickets;
    }

    private boolean isVisitorExists(int visitorID, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Visitor WHERE VisitorID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, visitorID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }

    private boolean isTicketOfficeExists(int ticketOfficeID, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TicketOffice WHERE TicketOfficeID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticketOfficeID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }
}

