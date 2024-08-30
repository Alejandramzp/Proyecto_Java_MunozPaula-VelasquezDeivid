/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.Conexion;
import Model.CosplayParticipantModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CosplayParticipantDao {
    private Connection connection;

    public CosplayParticipantDao() {
        Conexion conexion = new Conexion();
        this.connection = conexion.establecerConexion();
    }
    
    private boolean isParticipantExist(int ParticipationID) {

        String sql = "SELECT ParticipationID FROM CosplayParticipant WHERE ParticipationID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ParticipationID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia de la actividad: " + e.getMessage());
            return false;
        }
    }
    
    private boolean isCosplayContestExist(int CosplayContestID) {

        String sql = "SELECT CosplayContestID FROM CosplayContest WHERE CosplayContestID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, CosplayContestID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia de la actividad: " + e.getMessage());
            return false;
        }
    }

    public boolean addCosplayParticipant(CosplayParticipantModel participant) {
        
        if (!isParticipantExist(participant.getParticipationID())) {
            System.out.println("Error: El ID de Participante no existe.");
            return false;
        }
        
        if (!isCosplayContestExist(participant.getCosplayContestID())) {
            System.out.println("Error: El ID del Consurso no existe.");
            return false;
        }

        String sql = "INSERT INTO CosplayParticipant (ParticipationID, Name, Score, CosplayContestID) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, participant.getParticipationID());
            statement.setString(2, participant.getName());
            statement.setDouble(3, participant.getScore());
            statement.setInt(4, participant.getCosplayContestID());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getTopParticipants(int cosplayContestID) {
    String getTopParticipantSQL = "SELECT cp.ParticipationID, ap.VisitorID, cp.Name, cp.Score " +
                                  "FROM CosplayParticipant cp " +
                                  "JOIN ActivityParticipation ap ON cp.ParticipationID = ap.ParticipationID " +
                                  "WHERE cp.CosplayContestID = ? " +
                                  "ORDER BY cp.Score DESC LIMIT 1";

    String updateVisitorStatusSQL = "UPDATE Visitor SET Status = 'Ganador' WHERE VisitorID = ?";

    try (PreparedStatement getTopParticipantStmt = connection.prepareStatement(getTopParticipantSQL);
         PreparedStatement updateVisitorStatusStmt = connection.prepareStatement(updateVisitorStatusSQL)) {

        getTopParticipantStmt.setInt(1, cosplayContestID);
        ResultSet resultSet = getTopParticipantStmt.executeQuery();

        if (resultSet.next()) {
            int topVisitorID = resultSet.getInt("VisitorID");

            updateVisitorStatusStmt.setInt(1, topVisitorID);
            int rowsUpdated = updateVisitorStatusStmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("El estado del visitante ha sido actualizado a 'Ganador'.");
            } else {
                System.out.println("No se pudo actualizar el estado del visitante.");
            }

            return resultSet;
        } else {
            System.out.println("No se encontró ningún participante en el concurso de cosplay.");
            return null;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

}

