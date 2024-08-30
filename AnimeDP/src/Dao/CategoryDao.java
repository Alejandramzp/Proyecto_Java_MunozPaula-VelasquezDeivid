
package Dao;

import Model.CategoryModel;
import Connection.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private Connection connection;

    public CategoryDao() {
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }

    // Insertar una nueva categoría
    public boolean addCategory(CategoryModel category) {
        String sql = "INSERT INTO Category (CategoryName, Age, Gender) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getAge());
            statement.setString(3, category.getGender());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar la categoría: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una categoría
    public boolean deleteCategory(int categoryID) {
        String sql = "DELETE FROM Category WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar la categoría: " + e.getMessage());
            return false;
        }
    }

    // Obtener una categoría por ID
    public CategoryModel getCategoryById(int categoryID) {
        String sql = "SELECT * FROM Category WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new CategoryModel(
                        resultSet.getInt("CategoryID"),
                        resultSet.getString("CategoryName"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Gender")
                    );
                }
            }   
        } catch (SQLException e) {
            System.out.println("Error al obtener la categoría: " + e.getMessage());
        }
        return null;
    }

    // Listar todas las categorías
    public List<CategoryModel> listCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                categories.add(new CategoryModel(
                    resultSet.getInt("CategoryID"),
                    resultSet.getString("CategoryName"),
                    resultSet.getInt("Age"),
                    resultSet.getString("Gender")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las categorías: " + e.getMessage());
        }
        return categories;
    }
}

