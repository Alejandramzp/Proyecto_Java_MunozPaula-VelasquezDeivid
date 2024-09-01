/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.QuestionParticipantDao;
import Model.QuestionParticipantModel;
import java.sql.*;

public class QuestionParticipantController {
    private QuestionParticipantDao questionPariticipantDao;
    
    public QuestionParticipantController(QuestionParticipantDao questionParticipantDao){
        this.questionPariticipantDao = questionPariticipantDao;
    }
    
    public boolean addQuestionParticipant(int QuestionID, int ParticipationID){
        QuestionParticipantModel participant = new QuestionParticipantModel(QuestionID, ParticipationID);
        return questionPariticipantDao.addQuestionParticipant(participant);
    }
}
