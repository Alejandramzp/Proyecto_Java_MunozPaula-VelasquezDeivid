
package Dao;

import Connection.Conexion;
import Model.StoreInventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StoreInventoryDao {

    public boolean addProductToInventory(StoreInventory product) {
        String sqlCheckBusinessType = "SELECT Type FROM Business WHERE BusinessID = ?";
        String sqlInsertProduct = "INSERT INTO StoreInventory (BusinessID, ProductName, Description, Manufacturer, Type, AvailableQuantity, IndividualPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        
        try (Connection conn = conexion.establecerConexion();
             PreparedStatement stmtCheck = conn.prepareStatement(sqlCheckBusinessType)) {

            // Verificar que el comercio exista y sea de tipo "Tienda"
            stmtCheck.setInt(1, product.getBusinessID());
            try (ResultSet rs = stmtCheck.executeQuery()) {
                if (rs.next()) {
                    if (!rs.getString("Type").equalsIgnoreCase("Tienda")) {
                        System.out.println("El comercio no es de tipo Tienda.");
                        return false;
                    }
                } else {
                    System.out.println("El ID del comercio no existe.");
                    return false;
                }
            }

            // Insertar producto en el inventario
            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsertProduct)) {
                stmtInsert.setInt(1, product.getBusinessID());
                stmtInsert.setString(2, product.getProductName());
                stmtInsert.setString(3, product.getDescription());
                stmtInsert.setString(4, product.getManufacturer());
                stmtInsert.setString(5, product.getType());
                stmtInsert.setInt(6, product.getAvailableQuantity());
                stmtInsert.setDouble(7, product.getIndividualPrice());
                stmtInsert.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInventoryAfterSale(int inventoryID, int quantitySold) {
        String sqlCheckInventoryExists = "SELECT AvailableQuantity FROM StoreInventory WHERE InventoryID = ?";
        String sqlUpdateInventory = "UPDATE StoreInventory SET AvailableQuantity = AvailableQuantity - ? WHERE InventoryID = ?";
        Conexion conexion = new Conexion();

        try (Connection conn = conexion.establecerConexion();
             PreparedStatement stmtCheck = conn.prepareStatement(sqlCheckInventoryExists)) {

            // Verificar si el InventoryID existe y obtener la cantidad disponible
            stmtCheck.setInt(1, inventoryID);
            try (ResultSet rs = stmtCheck.executeQuery()) {
                if (rs.next()) {
                    int availableQuantity = rs.getInt("AvailableQuantity");

                    // Verificar si hay suficiente cantidad disponible
                    if (availableQuantity < quantitySold) {
                        System.out.println("No hay suficiente inventario disponible para la cantidad solicitada.");
                        return false;
                    }
                } else {
                    System.out.println("No hay un inventario con ese ID disponible.");
                    return false;
                }
            }

            // Actualizar el inventario si el ID existe y hay suficiente cantidad disponible
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateInventory)) {
                stmtUpdate.setInt(1, quantitySold);
                stmtUpdate.setInt(2, inventoryID);
                stmtUpdate.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<StoreInventory> getProductsByBusinessID(int businessID) {
        List<StoreInventory> products = new ArrayList<>();
        String sqlCheckBusiness = "SELECT Type FROM Business WHERE BusinessID = ?";
        String sqlGetProducts = "SELECT * FROM StoreInventory WHERE BusinessID = ?";
        Conexion conexion = new Conexion();

        try (Connection conn = conexion.establecerConexion() ;
             PreparedStatement stmtCheck = conn.prepareStatement(sqlCheckBusiness)) {

            // Verificar que el comercio exista y sea de tipo "Tienda"
            stmtCheck.setInt(1, businessID);
            try (ResultSet rsCheck = stmtCheck.executeQuery()) {
                if (rsCheck.next()) {
                    if (!rsCheck.getString("Type").equalsIgnoreCase("Tienda")) {
                        System.out.println("El comercio no es de tipo Tienda.");
                        return products;
                    }
                } else {
                    System.out.println("El ID del comercio no existe.");
                    return products;
                }
            }

            // Obtener productos del inventario
            try (PreparedStatement stmt = conn.prepareStatement(sqlGetProducts)) {
                stmt.setInt(1, businessID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        StoreInventory product = new StoreInventory(
                            rs.getInt("InventoryID"),
                            rs.getInt("BusinessID"),
                            rs.getString("ProductName"),
                            rs.getString("Description"),
                            rs.getString("Manufacturer"),
                            rs.getString("Type"),
                            rs.getInt("AvailableQuantity"),
                            rs.getDouble("IndividualPrice")
                        );
                        products.add(product);
                    }
                }
            }

            // Mensaje si no hay inventario disponible
            if (products.isEmpty()) {
                System.out.println("No hay inventario disponible para este comercio.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}

