
package Dao;

import Connection.Conexion;
import Model.StaffModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {

    // Verifica si el roleID existe en la tabla Role
    private boolean isRoleIDExists(int roleID) {
        String sql = "SELECT COUNT(*) FROM Role WHERE RoleID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, roleID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Devuelve verdadero si existe
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el roleID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;  // Devuelve falso si no existe o si ocurre un error
    }

    // Método para añadir un nuevo miembro del personal a la base de datos
    public boolean addStaff(StaffModel staff) {
        if (!isRoleIDExists(staff.getRoleID())) {
            System.out.println("Error: El roleID proporcionado no existe.");
            return false;
        }

        String sql = "INSERT INTO Staff (EventID, Name, Identification, DateOfBirth, RoleID, Status) VALUES (?, ?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, staff.getEventID());
            stmt.setString(2, staff.getName());
            stmt.setString(3, staff.getIdentification());
            stmt.setDate(4, java.sql.Date.valueOf(staff.getDateOfBirth()));
            stmt.setInt(5, staff.getRoleID());
            stmt.setString(6, staff.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;  // Devuelve verdadero si la inserción fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al insertar el miembro del personal: " + e.getMessage());
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

    // Método para ver todos los registros del personal
    public List<StaffModel> viewAllStaff() {
        List<StaffModel> staffList = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int staffID = rs.getInt("StaffID");
                int eventID = rs.getInt("EventID");
                String name = rs.getString("Name");
                String identification = rs.getString("Identification");
                LocalDate dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
                int roleID = rs.getInt("RoleID");
                String status = rs.getString("Status");

                StaffModel staff = new StaffModel(eventID, name, identification, dateOfBirth, roleID, status);
                staff.setStaffID(staffID); // Setea el StaffID obtenido de la base de datos
                staffList.add(staff);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de personal: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return staffList;
    }
}

