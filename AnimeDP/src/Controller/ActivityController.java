/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ActivityDao;
import Model.ActivityModel;

import java.util.List;

public class ActivityController {
    private ActivityDao activityDao;

    public ActivityController() {
        activityDao = new ActivityDao();
    }

    public boolean addActivity(ActivityModel activity) {
        return activityDao.addActivity(activity);
    }

    public List<ActivityModel> getAllActivities() {
        return activityDao.getAllActivities();
    }
}

