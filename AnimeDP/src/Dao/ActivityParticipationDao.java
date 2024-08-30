
package Dao;

import Connection.Conexion;
import Model.ActivityParticipationModel;

import java.sql.*;

public class ActivityParticipationDao {

    public boolean addActivityParticipation(ActivityParticipationModel participation) {
        String checkVisitorSql = "SELECT Status FROM Visitor WHERE VisitorID = ?";
        String checkActivitySql = "SELECT NumberOfParticipants FROM Activity WHERE ActivityID = ?";
        String countParticipantsSql = "SELECT COUNT(*) FROM ActivityParticipation WHERE ActivityID = ?";
        String insertParticipationSql = "INSERT INTO ActivityParticipation (VisitorID, ActivityID) VALUES (?, ?)";
        
        Conexion conexion = new Conexion();
        Connection connection = null;
        PreparedStatement checkVisitorStmt = null;
        PreparedStatement checkActivityStmt = null;
        PreparedStatement countParticipantsStmt = null;
        PreparedStatement insertStmt = null;

        try {
            connection = conexion.establecerConexion();
            
            // Verificar el estado del visitante
            checkVisitorStmt = connection.prepareStatement(checkVisitorSql);
            checkVisitorStmt.setInt(1, participation.getVisitorID());
            ResultSet visitorResult = checkVisitorStmt.executeQuery();

            if (!visitorResult.next()) {
                System.out.println("Error: El VisitorID no existe.");
                return false;
            }

            String visitorStatus = visitorResult.getString("Status");
            if (!visitorStatus.equals("Participa") && !visitorStatus.equals("Ganador")) {
                System.out.println("Error: El visitante no está en estado 'Participa' o 'Ganador'.");
                return false;
            }

            // Verificar que la actividad exista y obtener el número máximo de participantes
            checkActivityStmt = connection.prepareStatement(checkActivitySql);
            checkActivityStmt.setInt(1, participation.getActivityID());
            ResultSet activityResult = checkActivityStmt.executeQuery();

            if (!activityResult.next()) {
                System.out.println("Error: El ActivityID no existe.");
                return false;
            }

            int maxParticipants = activityResult.getInt("NumberOfParticipants");

            // Contar el número actual de participantes en la actividad
            countParticipantsStmt = connection.prepareStatement(countParticipantsSql);
            countParticipantsStmt.setInt(1, participation.getActivityID());
            ResultSet countResult = countParticipantsStmt.executeQuery();

            if (countResult.next() && countResult.getInt(1) >= maxParticipants) {
                System.out.println("Error: La actividad ya tiene el número máximo de participantes.");
                return false;
            }

            // Insertar el registro de participación
            insertStmt = connection.prepareStatement(insertParticipationSql);
            insertStmt.setInt(1, participation.getVisitorID());
            insertStmt.setInt(2, participation.getActivityID());
            int rowsInserted = insertStmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar participación en actividad: " + e.getMessage());
            return false;
        } finally {
            try {
                if (checkVisitorStmt != null) checkVisitorStmt.close();
                if (checkActivityStmt != null) checkActivityStmt.close();
                if (countParticipantsStmt != null) countParticipantsStmt.close();
                if (insertStmt != null) insertStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

