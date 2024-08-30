
package Model;

public class CosplayParticipantModel {
    private int participationID;
    private String name;
    private double score; // Puntuación total calculada
    private int cosplayContestID;

    public CosplayParticipantModel(int participationID, String name, double score, int cosplayContestID) {
        this.participationID = participationID;
        this.name = name;
        this.score = score;
        this.cosplayContestID = cosplayContestID;
    }

    // Getters y Setters
    public int getParticipationID() {
        return participationID;
    }

    public void setParticipationID(int participationID) {
        this.participationID = participationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCosplayContestID() {
        return cosplayContestID;
    }

    public void setCosplayContestID(int cosplayContestID) {
        this.cosplayContestID = cosplayContestID;
    }
}

