/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class ActivityRoleModel {
    private int ActivityRoleID;
    private String ActivityName;

    public ActivityRoleModel(int ActivityRoleID, String ActivityName) {
        this.ActivityRoleID = ActivityRoleID;
        this.ActivityName = ActivityName;
    }

    public int getActivityRoleID() {
        return ActivityRoleID;
    }

    public void setActivityRoleID(int ActivityRoleID) {
        this.ActivityRoleID = ActivityRoleID;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String ActivityName) {
        this.ActivityName = ActivityName;
    }

    @Override
    public String toString() {
        return "ActivityRoleModel{" + "ActivityRoleID=" + ActivityRoleID + ", ActivityName=" + ActivityName + '}';
    }
   
}
