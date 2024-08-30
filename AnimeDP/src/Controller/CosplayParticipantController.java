
package Controller;

import Dao.CosplayParticipantDao;
import Model.CosplayParticipantModel;
import java.sql.*;

public class CosplayParticipantController {
    private CosplayParticipantDao cosplayParticipantDao;

    public CosplayParticipantController(CosplayParticipantDao cosplayParticipantDao) {
        this.cosplayParticipantDao = cosplayParticipantDao;
    }

    // Método para agregar un participante de cosplay
    public boolean addCosplayParticipant(int participantID, String name, double[] scores, int cosplayContestID) {
        if (scores.length != 3) {
            throw new IllegalArgumentException("Se requieren exactamente 3 puntuaciones.");
        }

        // Calcular el promedio de los puntajes
        double averageScore = (scores[0] + scores[1] + scores[2]) / 3.0;

        // Crear el modelo de participante con el puntaje promedio
        CosplayParticipantModel participant = new CosplayParticipantModel(participantID, name, averageScore, cosplayContestID);
        return cosplayParticipantDao.addCosplayParticipant(participant);
    }

    // Método para obtener los 3 mejores participantes
    public ResultSet getTopParticipants(int cosplayContestID) {
        return cosplayParticipantDao.getTopParticipants(cosplayContestID);
    }
}

