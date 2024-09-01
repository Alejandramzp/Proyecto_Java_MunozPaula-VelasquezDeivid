
package Dao;

import Connection.Conexion;
import Model.TriviaPartivipantModel;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class TriviaParticipantDao {
    private Connection connection;
    
    public TriviaParticipantDao(){
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }
    
    public boolean isParticipantExisist(int ParticipationID){
        String sql = "SELECT ParticipationID FROM ActivityParticipation Where ParticipationID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, ParticipationID);
            try(ResultSet resulSet = statement.executeQuery()) {
                return resulSet.next();
            }
        }catch(SQLException e) {
            System.out.println("Error al verificar la existencia del participante: " + e.getMessage());
            return false;
        }
    }
    
    public boolean isTriviaContestExisist(int TriviaContestID){
        String sql = "SELECT TriviaContestID FROM TriviaContest Where TriviaContestID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, TriviaContestID);
            try(ResultSet resulSet = statement.executeQuery()) {
                return resulSet.next();
            }
        }catch(SQLException e) {
            System.out.println("Error al verificar la existencia de la actividad: " + e.getMessage());
            return false;
        }
    }
    
    public boolean addTriviaParticipant(TriviaPartivipantModel participant){
        
        if(!isParticipantExisist(participant.getParticipationID())){
            System.out.println("Error: El ID de participante no existe.");
            return false;
        }
        
        if(!isTriviaContestExisist(participant.getTriviaContestID())){
            System.out.println("Error: El ID del concurso trivia no existe.");
            return false;
        }
        
        String sql = "INSERT INTO TriviaParticipant (ParticipationID, Score, TriviaContestID) VALUES(?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, participant.getParticipationID());
            statement.setDouble(2, participant.getScore());
            statement.setInt(3, participant.getTriviaContestID());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void actualizarPuntaje(int participationID, double nuevoPuntaje) throws SQLException {
        String sql = "UPDATE TriviaParticipant SET Score = ? WHERE ParticipationID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, nuevoPuntaje);
            statement.setInt(2, participationID);
            statement.executeUpdate();
        }
    }

    public List<TriviaPartivipantModel> obtenerParticipantesPorConcurso(int triviaContestID) throws SQLException {
        List<TriviaPartivipantModel> participantes = new ArrayList<>();
        String sql = "SELECT * FROM TriviaParticipant WHERE TriviaContestID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, triviaContestID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int participationID = resultSet.getInt("ParticipationID");
                double score = resultSet.getDouble("Score");
                participantes.add(new TriviaPartivipantModel(participationID, score, triviaContestID));
            }
        }
        return participantes;
    }
    
    public ResultSet getTopParticipant(int TriviaContestID){
        String getTopParticipantSQL = "SELECT tp.ParticipationID, ap.VisitorID, v.Name, tp.Score " +
                                        "FROM TriviaParticipant tp " +
                                        "JOIN ActivityParticipation ap ON tp.ParticipationID = ap.ParticipationID" +
                                        "JOIN Visitor v ON ap.VisitorID = v.VisitorID " +
                                        "WHERE tp.TriviaContestID = ? " +
                                        "ORDER BY tp.Score DESC LIMIT 1";
        
        String updateVisitorStatusSQL = "UPDATE Visitor SET Status = 'Ganador' WHERE VisitorID = ?";
        
        try (PreparedStatement getTopParticipantStmt = connection.prepareStatement(getTopParticipantSQL);
                PreparedStatement updateVisitorStatusStmt = connection.prepareStatement(updateVisitorStatusSQL)){
            
            getTopParticipantStmt.setInt(1, TriviaContestID);
            ResultSet resultSet = getTopParticipantStmt.executeQuery();
            
            if(resultSet.next()){
                int topVisitorID = resultSet.getInt("VisitorID");
                
                updateVisitorStatusStmt.setInt(1, topVisitorID);
                int rowsUpdated = updateVisitorStatusStmt.executeUpdate();
                
                if(rowsUpdated > 0){
                    System.out.println("El estado del visitante ha sido actualizado a 'Ganador'.");
                } else {
                    System.out.println("No se pudo actualizar el estado del visitante.");
                }
                
                return resultSet;
            } else {
                System.out.println("No se encontró ningún participante en el concurso de trivia.");
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
