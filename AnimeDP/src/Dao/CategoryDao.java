/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import Model.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public boolean addCategory(CategoryModel category){
        String sql = "INSERT INTO Category(CategoryName, Age, Gender) VALUES(?, ?, ?)";
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try{
            connection = conexion.establecerConexion();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getAge());
            stmt.setString(3, category.getGender());
            
            int rowInserted = stmt.executeUpdate();
            return rowInserted > 0;
        } catch (SQLException e){
            System.out.println("Error al insertar categoria: " + e.getMessage());
            return false;
        }finally{
            try{
                if(stmt != null) stmt.close();
                if(connection != null) connection.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    public List<CategoryModel> getAllCategory(){
        List<CategoryModel> categorys = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        Conexion conexion = new Conexion();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            connection = conexion.establecerConexion();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CategoryModel category = new CategoryModel(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getInt("Age"),
                        rs.getString("Gender")
                );
                categorys.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener Categorias: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return categorys;
    }
}
