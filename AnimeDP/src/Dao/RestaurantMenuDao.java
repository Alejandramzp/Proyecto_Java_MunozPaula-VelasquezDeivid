
package Dao;

import Model.RestaurantMenuModel;
import Connection.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuDao {
    
    public boolean addRestaurantMenu(RestaurantMenuModel menu) {
        String sql = "INSERT INTO RestaurantMenu (BusinessID, DishID, AvailableQuantity) VALUES (?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, menu.getBusinessID());
            stmt.setInt(2, menu.getDishID());
            stmt.setInt(3, menu.getAvailableQuantity());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir el menú del restaurante: " + e.getMessage());
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

    public boolean updateRestaurantMenu(RestaurantMenuModel menu) {
        String sql = "UPDATE RestaurantMenu SET AvailableQuantity = ? WHERE BusinessID = ? AND DishID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, menu.getAvailableQuantity());
            stmt.setInt(2, menu.getBusinessID());
            stmt.setInt(3, menu.getDishID());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el menú del restaurante: " + e.getMessage());
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
    
    public boolean isStore(int businessID) {
        String sql = "SELECT Type FROM Business WHERE BusinessID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, businessID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("Type");
                    return "Tienda".equals(type); // Verifica si el tipo es 'Tienda'
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el tipo de comercio: " + e.getMessage());
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false; // Si no se encuentra el comercio o el tipo no es 'Tienda'
    }

    public List<RestaurantMenuModel> getMenuByBusinessId(int businessId) {
        String sql = "SELECT * FROM RestaurantMenu WHERE BusinessID = ?";
        List<RestaurantMenuModel> menus = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, businessId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RestaurantMenuModel menu = new RestaurantMenuModel(
                    rs.getInt("BusinessID"),
                    rs.getInt("DishID"),
                    rs.getInt("AvailableQuantity")
                );
                menus.add(menu);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el menú por ID de negocio: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return menus;
    }
}
