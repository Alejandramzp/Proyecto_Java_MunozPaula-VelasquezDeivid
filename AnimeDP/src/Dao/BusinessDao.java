
package Dao;

import Connection.Conexion;
import Model.BusinessModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessDao {

    public boolean addBusiness(BusinessModel business) {
        String sql = "INSERT INTO Business (Name, Type, InChargeID) VALUES (?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, business.getName());
            stmt.setString(2, business.getType());
            stmt.setInt(3, business.getInChargeID());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al añadir el comercio: " + e.getMessage());
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

    public List<BusinessModel> getAllBusinesses() {
        List<BusinessModel> businesses = new ArrayList<>();

        String sql = "SELECT * FROM Business";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                BusinessModel business = new BusinessModel(
                        rs.getInt("BusinessID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getInt("InChargeID")
                );
                businesses.add(business);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los comercios: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return businesses;
    }
    
    public BusinessModel getBusinessById(int businessID) {
        String sql = "SELECT * FROM Business WHERE BusinessID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, businessID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new BusinessModel(
                    rs.getInt("BusinessID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getInt("InChargeID")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el comercio por ID: " + e.getMessage());
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

    public boolean updateBusiness(BusinessModel business) {
        String sql = "UPDATE Business SET Name = ?, Type = ?, InChargeID = ? WHERE BusinessID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, business.getName());
            stmt.setString(2, business.getType());
            stmt.setInt(3, business.getInChargeID());
            stmt.setInt(4, business.getBusinessID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el comercio: " + e.getMessage());
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

    public boolean addStaffToBusiness(int staffID, int businessID) {
        String sql = "INSERT INTO BusinessStaff (StaffID, BusinessID) VALUES (?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, staffID);
            stmt.setInt(2, businessID);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al asignar personal al comercio: " + e.getMessage());
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

    public List<Integer> getStaffByBusiness(int businessID) {
        List<Integer> staffList = new ArrayList<>();
        String sql = "SELECT StaffID FROM BusinessStaff WHERE BusinessID = ?";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, businessID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                staffList.add(rs.getInt("StaffID"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el personal del comercio: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return staffList;
    }
}

