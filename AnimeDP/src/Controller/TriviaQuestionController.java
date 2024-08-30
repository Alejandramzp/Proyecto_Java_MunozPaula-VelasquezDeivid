/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.TriviaQuestionDao;
import Model.TriviaQuestioModel;

public class TriviaQuestionController {
    private TriviaQuestionDao triviaQuestionDao;
    
    public TriviaQuestionController() {
        this.triviaQuestionDao = new TriviaQuestionDao();
    }
    
    public boolean addTriviaQuestion(TriviaQuestioModel question){
        return triviaQuestionDao.addTriviaQuestion(question);
    }
    
    public void listAllTriviaQuestion(){
        triviaQuestionDao.listAllTriviaQuestion();
    }
}
