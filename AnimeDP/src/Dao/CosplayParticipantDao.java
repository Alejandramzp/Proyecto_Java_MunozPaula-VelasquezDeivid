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
        // Consulta SQL para verificar la existencia de la actividad
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
        // Consulta SQL para verificar la existencia de la actividad
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

    // Método para agregar un nuevo participante
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
        String sql = "SELECT * FROM CosplayParticipant WHERE CosplayContestID = ? ORDER BY Score DESC LIMIT 3";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cosplayContestID);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

