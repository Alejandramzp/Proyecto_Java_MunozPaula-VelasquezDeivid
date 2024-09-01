
package Dao;

import Connection.Conexion;
import Model.TriviaQuestioModel;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TriviaQuestionDao {
    
    private Connection connection;

    public TriviaQuestionDao() {
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }
    
    public boolean addTriviaQuestion(TriviaQuestioModel question){
        String sql = "INSERT INTO TriviaQuestion (Question, CorrectAnswer, Category, Difficulty) VALUES(?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getCorrectAnswer());
            statement.setString(3, question.getCatagory());
            statement.setString(4, question.getDifficulty());
            
            int rowInserted = statement.executeUpdate();
            return rowInserted > 0;
        }catch(SQLException e){
            System.out.println("Error al agregar la pregunta trivia: " + e.getMessage());
            return false;
        }
    }
    
    public void listAllTriviaQuestion(){
        String sql = "SELECT * FROM TriviaQuestion";
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            
            while(resultSet.next()){
                int QuestionID = resultSet.getInt("QuestionID");
                String Question = resultSet.getString("Question");
                String CorrectAnswer = resultSet.getString("CorrectAnswer");
                String Category = resultSet.getString("Category");
                String Difficulty = resultSet.getString("Difficulty");
                
                System.out.println("QuestionID: " + QuestionID + ", Question: " + Question + ", CorrectAnswer: " + CorrectAnswer + ", Category: " + Category + ", Difficulty: " + Difficulty);
            }
            
        }catch (SQLException e){
            System.out.println("Error al listar las preguntas de trivia: " + e.getMessage());
        }
    }
    
    public List<TriviaQuestioModel> getQuestionsByDifficulty(String difficulty) throws SQLException{
        List<TriviaQuestioModel> question = new ArrayList<>();
        String sql = "SELECT * FROM TriviaQuestion WHERE Difficulty = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, difficulty);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int id = resultSet.getInt("QuestionID");
                String questionText = resultSet.getString("Question");
                String correctAnswer = resultSet.getString("CorrectAnswer");
                String category = resultSet.getString("Category");
                question.add(new TriviaQuestioModel(id, questionText, correctAnswer, category, difficulty));
            }
        }
        return question;
    } 
}
