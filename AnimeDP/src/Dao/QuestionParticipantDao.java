/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.QuestionParticipantModel;
import Connection.Conexion;
import java.sql.*;

public class QuestionParticipantDao {
    
    private Connection connection;
    
    public QuestionParticipantDao(){
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }
    
    public boolean isQuestionExisist(int QuestionID){
        String sql = "SELECT QuestionID FROM QuestionParticipant WHERE QuestionID = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, QuestionID);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }catch(SQLException e){
            System.out.println("Error al verificar la exisitencia de la pregunta " + e.getMessage());
            return false;
        }
    }
    
    public boolean isParticipantExisist(int ParticipationID){
        String sql = "SELECT ParticipationID FROM QuestionParticipant WHERE ParticipationID = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ParticipationID);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }catch(SQLException e){
            System.out.println("Error al verificar la exisitencia del participante " + e.getMessage());
            return false;
        }
    }
    
    public boolean addQuestionParticipant(QuestionParticipantModel participant){
        if(!isQuestionExisist(participant.getQuestionID())){
            System.out.println("Error: El ID de la preguntas no existe.");
            return false;
        }
        
        if(!isParticipantExisist (participant.getParticipationID())){
            System.out.println("Error: El ID del participante no existe");
            return false;
        }
        
        String sql = "INSERT INTO QuestionParticipant(QuestionID, ParticipationID) VALUES(?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, participant.getQuestionID());
            statement.setInt(2, participant.getParticipationID());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
