
package Dao;

import Connection.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.CosplayContestModel;

public class CosplayContestDao {

    public boolean isCategoryExist(int categoryID) {
        String sql = "SELECT CategoryID FROM Category WHERE CategoryID = ?";
        try (Connection connection = new Conexion().establecerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia de la categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean addCosplayContest(CosplayContestModel contest) {
        if (!isCategoryExist(contest.getCategoryID())) {
            System.out.println("Error: El ID de la categoría no existe.");
            return false;
        }

        String sql = "INSERT INTO CosplayContest (Name, CategoryID) VALUES (?, ?)";
        try (Connection connection = new Conexion().establecerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contest.getName());
            statement.setInt(2, contest.getCategoryID());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar el concurso de cosplay: " + e.getMessage());
            return false;
        }
    }

    public void listAllCosplayContests() {
        String sql = "SELECT * FROM CosplayContest";
        try (Connection connection = new Conexion().establecerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int contestID = resultSet.getInt("CosplayContestID");
                String name = resultSet.getString("Name");
                int categoryID = resultSet.getInt("CategoryID");

                System.out.println("CosplayContestID: " + contestID + ", Name: " + name + ", CategoryID: " + categoryID);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los concursos de cosplay: " + e.getMessage());
        }
    }
}



