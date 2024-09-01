
package Dao;

import Model.DishModel;
import Connection.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDao {
    
    public boolean addDish(DishModel dish) {
        String sql = "INSERT INTO Dish (Description, Type, PreparationTimeMinutes) VALUES (?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try{   
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);   
            
            stmt.setString(1, dish.getDescription());
            stmt.setString(2, dish.getType());
            stmt.setInt(3, dish.getPreparationTimeMinutes());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir el plato: " + e.getMessage());
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

    public boolean updateDish(DishModel dish) {
        String sql = "UPDATE Dish SET Description = ?, Type = ?, PreparationTimeMinutes = ? WHERE DishID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, dish.getDescription());
            stmt.setString(2, dish.getType());
            stmt.setInt(3, dish.getPreparationTimeMinutes());
            stmt.setInt(4, dish.getDishID());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el plato: " + e.getMessage());
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

    public List<DishModel> getDishesByBusinessId(int businessId) {
        String sql = "SELECT d.DishID, d.Description, d.Type, d.PreparationTimeMinutes, rm.AvailableQuantity " +
                     "FROM Dish d " +
                     "JOIN RestaurantMenu rm ON d.DishID = rm.DishID " +
                     "WHERE rm.BusinessID = ?";
        List<DishModel> dishes = new ArrayList<>();
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
                DishModel dish = new DishModel(
                    rs.getInt("DishID"),
                    rs.getString("Description"),
                    rs.getString("Type"),
                    rs.getInt("PreparationTimeMinutes")
                );
                dishes.add(dish);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los platos por ID de negocio: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return dishes;
    }
}

