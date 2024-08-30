
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

