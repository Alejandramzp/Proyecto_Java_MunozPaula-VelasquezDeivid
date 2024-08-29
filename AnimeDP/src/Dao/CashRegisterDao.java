
package Dao;


import Connection.Conexion;
import Model.CashRegisterModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashRegisterDao {
    
    public boolean addCashRegister(CashRegisterModel CashRegister){
        String sql = "INSERT INTO CashRegister (Status, OpeningAmount, ClosingAmount,BusinessStaffID) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
    
        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, CashRegister.getStatus());
            stmt.setDouble(2, CashRegister.getOpeningAmount());
            stmt.setDouble(3, CashRegister.getClosingAmount());
            stmt.setInt(4, CashRegister.getBusinessStaffID());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir la caja: " + e.getMessage());
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
    
    public boolean isStaffExists(int id) {
        String sql = "SELECT COUNT(*) FROM BusinessStaff WHERE StaffID  = ?";
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
            System.out.println("Error al verificar si el ID del personal existe: " + e.getMessage());
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
    
    public List<CashRegisterModel> getAllCashRegister(){
        List<CashRegisterModel> cashRegister = new ArrayList<>();
        
        String sql = "SELECT * FROM CashRegister";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CashRegisterModel register = new CashRegisterModel(
                        rs.getInt("CashRegisterID"),
                        rs.getDouble("OpeningAmount"),
                        rs.getInt("BusinessStaffID")
                        
                );
                register.setClosingAmount(rs.getDouble("ClosingAmount"));
                register.setStatus(rs.getString("Status"));
                cashRegister.add(register);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cajas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return cashRegister;   
    }
    
    public CashRegisterModel getCashRegisterById(int cashRegisterID) {
    String sql = "SELECT * FROM CashRegister WHERE CashRegisterID = ?";
    Conexion conexion = new Conexion();
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        connection = conexion.establecerConexion();
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, cashRegisterID);
        rs = stmt.executeQuery();

        if (rs.next()) {
            CashRegisterModel cashRegister = new CashRegisterModel();
            
            cashRegister.setCashRegisterID(rs.getInt("CashRegisterID"));
            cashRegister.setStatus(rs.getString("Status"));
            cashRegister.setOpeningAmount(rs.getDouble("OpeningAmount"));
            cashRegister.setClosingAmount(rs.getDouble("ClosingAmount"));
            cashRegister.setBusinessStaffID(rs.getInt("BusinessStaffID"));
            return cashRegister;
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener la caja por ID: " + e.getMessage());
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
    
    public boolean updateCashRegister(CashRegisterModel cashRegister) {
    String sql = "UPDATE CashRegister SET Status = ?, OpeningAmount = ?, ClosingAmount = ?, BusinessStaffID = ? WHERE CashRegisterID = ?";
    Conexion conexion = new Conexion();
    Connection connection = null;
    PreparedStatement stmt = null;

    try {
        connection = conexion.establecerConexion();
        stmt = connection.prepareStatement(sql);

        stmt.setString(1, cashRegister.getStatus());
        stmt.setDouble(2, cashRegister.getOpeningAmount());
        stmt.setDouble(3, cashRegister.getClosingAmount());
        stmt.setInt(4, cashRegister.getBusinessStaffID());
        stmt.setInt(5, cashRegister.getCashRegisterID());

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        System.out.println("Error al actualizar la caja: " + e.getMessage());
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

}
