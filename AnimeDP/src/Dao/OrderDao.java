
package Dao;

import Connection.Conexion;
import Model.OrderModel;
import java.sql.*;


public class OrderDao {

    public boolean addOrder(OrderModel order) {
        String sql = "INSERT INTO Orderr (VisitorID, BusinessID, CashRegisterID, TotalValue, Status) VALUES (?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, order.getVisitorID());
            stmt.setInt(2, order.getBusinessID());
            stmt.setInt(3, order.getCashRegisterID());
            stmt.setDouble(4, order.getTotalValue());
            stmt.setString(5, order.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir el pedido: " + e.getMessage());
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

    public boolean updateOrderStatus(int orderID, String newStatus) {
        String sql = "UPDATE Orderr SET Status = ? WHERE OrderID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, newStatus);
            stmt.setInt(2, orderID);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del pedido: " + e.getMessage());
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

    public OrderModel getOrderById(int orderID) {
        String sql = "SELECT * FROM Orderr WHERE OrderID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, orderID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                OrderModel order = new OrderModel(
                    rs.getInt("VisitorID"),
                    rs.getInt("BusinessID"),
                    rs.getInt("CashRegisterID"),
                    rs.getDouble("TotalValue")
                );
                order.setOrderID(rs.getInt("OrderID"));
                order.setStatus(rs.getString("Status"));
                return order;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el pedido por ID: " + e.getMessage());
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
}
