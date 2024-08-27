
package Dao;

import Model.PropsModel;
import Connection.Conexion;

import java.sql.*;

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
    
    
}
