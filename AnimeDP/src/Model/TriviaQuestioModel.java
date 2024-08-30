/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class TriviaQuestioModel {
    private int QuestionID;
    private String Question;
    private String CorrectAnswer;
    private String Catagory;
    private String Difficulty;

    public TriviaQuestioModel(int QuestionID, String Question, String CorrectAnswer, String Catagory, String Difficulty) {
        this.QuestionID = QuestionID;
        this.Question = Question;
        this.CorrectAnswer = CorrectAnswer;
        this.Catagory = Catagory;
        this.Difficulty = Difficulty;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String Catagory) {
        this.Catagory = Catagory;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String Difficulty) {
        this.Difficulty = Difficulty;
    }
    
    
}
