/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrizeDao {

    private Connection connection;

    // Constructor que inicializa la conexión usando la clase Conexion
    public PrizeDao() {
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }

    // Método para agregar un premio
    public boolean addPrize(String type, String description, double value, int activityID, int businessID) {
        // Verificar que el ID de la actividad exista
        if (!isActivityExist(activityID)) {
            System.out.println("Error: El ID de actividad no existe.");
            return false;
        }

        // Verificar que el ID del negocio exista
        if (!isBusinessExist(businessID)) {
            System.out.println("Error: El ID de negocio no existe.");
            return false;
        }

        // Consulta SQL para insertar un nuevo premio con estado predeterminado como "Disponible"
        String sql = "INSERT INTO Prize (Type, Description, Value, Status, ActivityID, BusinessID) VALUES (?, ?, ?, 'Disponible', ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);
            statement.setString(2, description);
            statement.setDouble(3, value);
            statement.setInt(4, activityID);
            statement.setInt(5, businessID);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar el premio: " + e.getMessage());
            return false;
        }
    }

    // Método para entregar un premio
    public boolean deliverPrize(int prizeID, int visitorID) {
        // Verificar que el ID del visitante exista
        if (!isVisitorExist(visitorID)) {
            System.out.println("Error: El ID de visitante no existe.");
            return false;
        }

        // Consulta SQL para actualizar el estado del premio a 'Entregado'
        String sql = "UPDATE Prize SET Status = 'Entregado', VisitorID = ? WHERE PrizeID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, visitorID);
            statement.setInt(2, prizeID);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error al marcar el premio como entregado: " + e.getMessage());
            return false;
        }
    }

    // Verificar existencia de actividad
    private boolean isActivityExist(int activityID) {
        // Consulta SQL para verificar la existencia de la actividad
        String sql = "SELECT ActivityID FROM Activity WHERE ActivityID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, activityID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Devuelve true si encuentra un resultado
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia de la actividad: " + e.getMessage());
            return false;
        }
    }

    // Verificar existencia de negocio
    private boolean isBusinessExist(int businessID) {
        // Consulta SQL para verificar la existencia del negocio
        String sql = "SELECT BusinessID FROM Business WHERE BusinessID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, businessID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Devuelve true si encuentra un resultado
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia del negocio: " + e.getMessage());
            return false;
        }
    }

    // Verificar existencia de visitante
    private boolean isVisitorExist(int visitorID) {
        // Consulta SQL para verificar la existencia del visitante
        String sql = "SELECT VisitorID FROM Visitor WHERE VisitorID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, visitorID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Devuelve true si encuentra un resultado
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia del visitante: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un premio
    public boolean removePrize(int prizeID) {
        // Consulta SQL para eliminar un premio
        String sql = "DELETE FROM Prize WHERE PrizeID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prizeID);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el premio: " + e.getMessage());
            return false;
        }
    }

    // Método para listar premios por estado
    public void listPrizesByStatus(String status) {
        // Consulta SQL para listar premios por estado
        String sql = "SELECT * FROM Prize WHERE Status = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int prizeID = resultSet.getInt("PrizeID");
                    String type = resultSet.getString("Type");
                    String description = resultSet.getString("Description");
                    double value = resultSet.getDouble("Value");
                    System.out.println("Premio ID: " + prizeID + ", Tipo: " + type + ", Descripción: " + description + ", Valor: " + value + ", Estado: " + status);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar premios por estado: " + e.getMessage());
        }
    }
}



