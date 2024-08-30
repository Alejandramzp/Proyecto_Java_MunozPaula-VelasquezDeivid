
package Dao;

import Connection.Conexion;
import Model.VisitorModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorDao {

    public boolean addVisitor(VisitorModel visitor) {
        String sql = "INSERT INTO Visitor (Name, IdentificationDocument, Gender, DateOfBirth, Email, PhoneNumber, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, visitor.getName());
            stmt.setString(2, visitor.getIdentificationDocument());
            stmt.setString(3, visitor.getGender());
            stmt.setDate(4, new java.sql.Date(visitor.getDateOfBirth().getTime()));
            stmt.setString(5, visitor.getEmail());
            stmt.setString(6, visitor.getPhoneNumber());
            stmt.setString(7, visitor.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar visitante: " + e.getMessage());
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

    public List<VisitorModel> getAllVisitors() {
        List<VisitorModel> visitors = new ArrayList<>();
        String sql = "SELECT * FROM Visitor";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                VisitorModel visitor = new VisitorModel(
                        rs.getInt("VisitorID"),
                        rs.getString("Name"),
                        rs.getString("IdentificationDocument"),
                        rs.getString("Gender"),
                        rs.getDate("DateOfBirth"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status")
                );
                visitors.add(visitor);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener visitantes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return visitors;
    }
}

