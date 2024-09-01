
package Controller;

import Dao.TriviaParticipantDao;
import Model.TriviaPartivipantModel;
import java.sql.*;

public class TriviaParticipantController {
    private TriviaParticipantDao triviaparticipantDao;
    
    public TriviaParticipantController(TriviaParticipantDao triviaparticipantDao){
        this.triviaparticipantDao = triviaparticipantDao;
    }
    
    public boolean addTriviaParticipant(int participantID, double score, int triviaContestID){
        TriviaPartivipantModel participant = new TriviaPartivipantModel(participantID, score, triviaContestID);
        return triviaparticipantDao.addTriviaParticipant(participant);
    }
    
    public ResultSet getTopParticipants(int triviaContestID){
        return triviaparticipantDao.getTopParticipant(triviaContestID);
    }
}
