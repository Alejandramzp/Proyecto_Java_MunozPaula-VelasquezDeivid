/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import Model.TriviaContestModel;

import java.sql.*;

public class TriviaContestDao {
    
    private Connection connection;
    
    public TriviaContestDao(){
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }
    
    public boolean isCategoryExist(int categoryID){
        String sql = "SELECT CategoryID FROM Category WHERE CategoryID = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryID);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }catch (SQLException e) {
            System.out.println("Error al verificar la existencia de la categoría: " + e.getMessage());
            return false;
        }
    }
    
    public boolean addTriviaContest(TriviaContestModel contest){
        if(!isCategoryExist(contest.getCategoryID())){
            System.out.println("Error: El ID de la categoría no existe.");
            return false;
        }
        
        String sql = "INSERT INTO TriviaContest (Name, CategoryID) VALUE (?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contest.getName());
            statement.setInt(2, contest.getCategoryID());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            System.out.println("Error al agregar el concurso de Trivia: " + e.getMessage());
            return false;
        }
    }
    
    public void listAllTriviaContest() {
        String sql = "SELECT * FROM TriviaContest";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int contestID = resultSet.getInt("TriviaContestID");
                String name = resultSet.getString("Name");
                int categoryID = resultSet.getInt("CategoryID");

                System.out.println("TriviaContestID: " + contestID + ", Name: " + name + ", CategoryID: " + categoryID);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los concursos de trivia: " + e.getMessage());
        }
    }
}
