
package Dao;

import Connection.Conexion;
import Model.OrderItemModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    
    public boolean addOrderItem(OrderItemModel orderItem) {
        if (!orderExists(orderItem.getOrderID())) {
            System.out.println("Error: El pedido con ID " + orderItem.getOrderID() + " no existe.");
            return false; // No se puede agregar el ítem si el pedido no existe
        }

        String sql = "INSERT INTO OrderItem (OrderID, ItemName, Quantity, IndividualValue) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, orderItem.getOrderID());
            stmt.setString(2, orderItem.getItemName());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getIndividualValue());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                // Actualizar el total del pedido después de agregar el ítem
                updateOrderTotal(orderItem.getOrderID());
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error al añadir el ítem del pedido: " + e.getMessage());
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
    
    public boolean orderExists(int orderID) {
        String sql = "SELECT 1 FROM Orderr WHERE OrderID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, orderID);
            rs = stmt.executeQuery();

            return rs.next(); // Devuelve true si se encontró una fila
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia del pedido: " + e.getMessage());
            return false;
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
    
    private void updateOrderTotal(int orderID) {
        String sql = "UPDATE Orderr SET TotalValue = (SELECT SUM(Quantity * IndividualValue) FROM OrderItem WHERE OrderID = ?) WHERE OrderID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, orderID);
            stmt.setInt(2, orderID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el total del pedido: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    public List<OrderItemModel> getOrderItemsByOrderId(int orderID) {
        List<OrderItemModel> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM OrderItem WHERE OrderID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, orderID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItemModel item = new OrderItemModel(
                    rs.getInt("OrderID"),
                    rs.getString("ItemName"),
                    rs.getInt("Quantity"),
                    rs.getDouble("IndividualValue")
                );
                item.setOrderItemID(rs.getInt("OrderItemID"));
                orderItems.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ítems del pedido: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return orderItems;
    }
}
