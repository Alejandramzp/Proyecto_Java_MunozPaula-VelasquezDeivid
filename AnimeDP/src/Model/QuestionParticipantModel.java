/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class QuestionParticipantModel {
    private int QuestionID;
    private int ParticipationID;

    public QuestionParticipantModel(int QuestionID, int ParticipationID) {
        this.QuestionID = QuestionID;
        this.ParticipationID = ParticipationID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public int getParticipationID() {
        return ParticipationID;
    }

    public void setParticipationID(int ParticipationID) {
        this.ParticipationID = ParticipationID;
    }
    
}
