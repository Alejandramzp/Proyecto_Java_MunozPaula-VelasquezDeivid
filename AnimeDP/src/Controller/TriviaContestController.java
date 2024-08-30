/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.TriviaContestDao;
import Model.TriviaContestModel;

public class TriviaContestController {
    private TriviaContestDao triviaContestDao;
    
    public TriviaContestController(){
        this.triviaContestDao = new TriviaContestDao();
    }
    
    public boolean  addTriviaContest(TriviaContestModel contest){
        return triviaContestDao.addTriviaContest(contest);
    }
    
    public void listAllTriviaContests(){
        triviaContestDao.listAllTriviaContest();
    }
}
