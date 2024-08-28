/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import Model.ActivityModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao {
    public boolean addActivity(ActivityModel activity) {
        String sql = "INSERT INTO Activity(Name, Type, CategoryID, NumberOfParticipants, EventID, StartTime, StaffID) VALUES(?, ?, ?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, activity.getName());
            stmt.setString(2, activity.getType());
            stmt.setInt(3, activity.getCategoryID());
            stmt.setInt(4, activity.getNumberOfParticipants());
            stmt.setInt(5, activity.getEventID());
            stmt.setString(6, activity.getStartTime());
            stmt.setInt(7, activity.getStaffID());

            int rowInserted = stmt.executeUpdate();
            return rowInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar actividad: " + e.getMessage());
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

    public List<ActivityModel> getAllActivities() {
        List<ActivityModel> activities = new ArrayList<>();
        String sql = "SELECT * FROM Activity";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ActivityModel activity = new ActivityModel(
                        rs.getInt("ActivityID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getInt("CategoryID"),
                        rs.getInt("NumberOfParticipants"),
                        rs.getInt("EventID"),
                        rs.getString("StartTime"),
                        rs.getInt("StaffID")
                );
                activities.add(activity);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener actividades: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return activities;
    }
}

