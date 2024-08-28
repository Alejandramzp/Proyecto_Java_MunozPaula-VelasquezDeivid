
package Dao;

import Model.PropsModel;
import Connection.Conexion;
import Model.EventModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropsDao {
    
    public boolean addProps(PropsModel props){
        String sql = "INSERT INTO Props (Name, Quantity, Status, EventID) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
    
        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, props.getName());
            stmt.setInt(2, props.getQuantity());
            stmt.setString(3, props.getStatus());
            stmt.setInt(4, props.getEventId());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir la utilería: " + e.getMessage());
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    public boolean isEventExists(int id) {
        String sql = "SELECT COUNT(*) FROM Event WHERE EventID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si el ID del evento existe: " + e.getMessage());
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
    
    public List<PropsModel> getAllProps(){
        List<PropsModel> props = new ArrayList<>();
        
        String sql = "SELECT * FROM Props";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PropsModel prop = new PropsModel(
                        rs.getInt("PropID"),
                        rs.getString("Name"),
                        rs.getInt("Quantity"),
                        rs.getString("Status"),
                        rs.getInt("EventID")
                        
                );
                props.add(prop);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener utilerías: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return props;   
    }
    
    public PropsModel getPropsById(int propsId) {
    String sql = "SELECT * FROM Props WHERE PropID = ?";
    Conexion conexion = new Conexion();
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        connection = conexion.establecerConexion();
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, propsId);
        rs = stmt.executeQuery();

        if (rs.next()) {
            PropsModel props = new PropsModel();
            
            props.setId(rs.getInt("PropID"));
            props.setName(rs.getString("Name"));
            props.setQuantity(rs.getInt("Quantity"));
            props.setStatus(rs.getString("Status"));
            props.setEventId(rs.getInt("EventID"));
            return props;
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener la utilería por ID: " + e.getMessage());
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
    
    public boolean updatePropsStatus(PropsModel props){
        String sql = "UPDATE Props SET Status = ? WHERE PropID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, props.getStatus());
            stmt.setInt(2,props.getId());
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado de la utilería: " + e.getMessage());
            return false;
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
