
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
