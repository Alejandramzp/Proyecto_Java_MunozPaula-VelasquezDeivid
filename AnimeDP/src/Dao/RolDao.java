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

public class RolDao {

    public boolean isActivityRoleIDExists(int activityRoleID) {
        String sql = "SELECT COUNT(*) FROM ActivityRole WHERE ActivityRoleID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, activityRoleID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el ID de la actividad: " + e.getMessage());
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

    public boolean addRole(String roleName, int activity1ID, int activity2ID) {
        if (!isActivityRoleIDExists(activity1ID) || !isActivityRoleIDExists(activity2ID)) {
            System.out.println("Uno o ambos IDs de actividad no existen.");
            return false;
        }

        String sql = "INSERT INTO Role (RoleName, activity1ID, activity2ID) VALUES (?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, roleName);
            stmt.setInt(2, activity1ID);
            stmt.setInt(3, activity2ID);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el rol: " + e.getMessage());
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

    // Método para ver todos los roles
    public void viewAllRoles() {
        String sql = "SELECT * FROM Role";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            System.out.println("\n--- Lista de Roles ---");
            while (rs.next()) {
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");
                int activity1ID = rs.getInt("activity1ID");
                int activity2ID = rs.getInt("activity2ID");

                System.out.println("ID del Rol: " + roleID);
                System.out.println("Nombre del Rol: " + roleName);
                System.out.println("ID de la Actividad 1: " + activity1ID);
                System.out.println("ID de la Actividad 2: " + activity2ID);
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los roles: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}


