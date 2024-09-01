
package Dao;

import Connection.Conexion;
import Model.DiscountPromotionModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountPromotionDao {

    public boolean addDiscountPromotion(DiscountPromotionModel discountPromotion) {
        String sql = "INSERT INTO DiscountPromotion (InventoryID, Description, Type, DiscountValue) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, discountPromotion.getInventoryID());
            stmt.setString(2, discountPromotion.getDescription());
            stmt.setString(3, discountPromotion.getType());
            stmt.setDouble(4, discountPromotion.getDiscountValue());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir el descuento: " + e.getMessage());
            return false;
        }
    }

    public List<DiscountPromotionModel> getAllDiscountPromotions() {
        List<DiscountPromotionModel> discountPromotions = new ArrayList<>();
        String sql = "SELECT * FROM DiscountPromotion";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DiscountPromotionModel discountPromotion = new DiscountPromotionModel(
                        rs.getInt("DiscountID"),
                        rs.getInt("InventoryID"),
                        rs.getString("Description"),
                        rs.getString("Type"),
                        rs.getDouble("DiscountValue")
                );
                discountPromotions.add(discountPromotion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los descuentos: " + e.getMessage());
        }
        return discountPromotions;
    }

    public DiscountPromotionModel getDiscountPromotionById(int discountID) {
        String sql = "SELECT * FROM DiscountPromotion WHERE DiscountID = ?";
        Conexion conexion = new Conexion();
        
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, discountID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DiscountPromotionModel(
                            rs.getInt("DiscountID"),
                            rs.getInt("InventoryID"),
                            rs.getString("Description"),
                            rs.getString("Type"),
                            rs.getDouble("DiscountValue")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el descuento por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean updateDiscountPromotion(DiscountPromotionModel discountPromotion) {
        String sql = "UPDATE DiscountPromotion SET InventoryID = ?, Description = ?, Type = ?, DiscountValue = ? WHERE DiscountID = ?";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, discountPromotion.getInventoryID());
            stmt.setString(2, discountPromotion.getDescription());
            stmt.setString(3, discountPromotion.getType());
            stmt.setDouble(4, discountPromotion.getDiscountValue());
            stmt.setInt(5, discountPromotion.getDiscountID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el descuento: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteDiscountPromotion(int discountID) {
        String sql = "DELETE FROM DiscountPromotion WHERE DiscountID = ?";
        Conexion conexion = new Conexion();
        try (Connection connection = conexion.establecerConexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, discountID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el descuento: " + e.getMessage());
            return false;
        }
    }
}
