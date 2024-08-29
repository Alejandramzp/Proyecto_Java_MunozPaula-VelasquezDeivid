/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ActivityParticipationModel {
    private int participationID;
    private int visitorID;
    private int activityID;

    public ActivityParticipationModel(int participationID, int visitorID, int activityID) {
        this.participationID = participationID;
        this.visitorID = visitorID;
        this.activityID = activityID;
    }

    public int getParticipationID() {
        return participationID;
    }

    public int getVisitorID() {
        return visitorID;
    }

    public int getActivityID() {
        return activityID;
    }
}

