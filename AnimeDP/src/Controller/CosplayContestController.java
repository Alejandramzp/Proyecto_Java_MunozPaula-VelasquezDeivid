/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.CosplayContestDao;
import Model.CosplayContestModel;

public class CosplayContestController {

    private CosplayContestDao cosplayContestDao;

    public CosplayContestController() {
        this.cosplayContestDao = new CosplayContestDao();
    }

    public boolean addCosplayContest(CosplayContestModel contest) {
        return cosplayContestDao.addCosplayContest(contest);
    }

    public void listAllCosplayContests() {
        cosplayContestDao.listAllCosplayContests();
    }
}


