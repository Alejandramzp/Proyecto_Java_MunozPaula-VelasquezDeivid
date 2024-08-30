
package Dao;

import Connection.Conexion;
import Model.TicketOfficeModel;

import java.sql.*;

public class TicketOfficeDao {

    public boolean isEventIDExists(int eventID) {
        String sql = "SELECT COUNT(*) FROM Event WHERE EventID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, eventID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el EventID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean isStaffnChargeIDExists(int StaffID) {
        String sql = "SELECT COUNT(*) FROM Staff WHERE StaffID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, StaffID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el StaffID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    // Verificar si ya existe una taquilla para un evento específico
    public boolean isTicketOfficeExistsForEvent(int eventID) {
        String sql = "SELECT COUNT(*) FROM TicketOffice WHERE EventID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, eventID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si ya existe una taquilla para el EventID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean addTicketOffice(TicketOfficeModel office) {
        
        if (!isEventIDExists(office.getEventID())) {
            System.out.println("Error: El EventID proporcionado no existe.");
            return false;
        }

        // Validar si el StaffID proporcionado existe
        if (!isStaffnChargeIDExists(office.getStaffInChargeID())) {
            System.out.println("Error: El StaffID proporcionado no existe.");
            return false;
        }

        // Validar si ya existe una taquilla para el EventID
        if (isTicketOfficeExistsForEvent(office.getEventID())) {
            System.out.println("Error: Ya existe una taquilla para este EventID.");
            return false;
        }

        // Insertar nueva taquilla en la base de datos
        String sql = "INSERT INTO TicketOffice(EventID, Location, ContactNumber, StaffInChargeID) VALUES(?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, office.getEventID());
            stmt.setString(2, office.getLocation());
            stmt.setString(3, office.getContacNumber());
            stmt.setInt(4, office.getStaffInChargeID());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar la taquilla: " + e.getMessage());
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


