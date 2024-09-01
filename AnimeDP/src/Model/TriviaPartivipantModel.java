
package Model;

public class TriviaPartivipantModel {
    private int ParticipationID;
    private double Score;
    private int TriviaContestID;

    public TriviaPartivipantModel(int ParticipationID, double Score, int TriviaContestID) {
        this.ParticipationID = ParticipationID;
        this.Score = Score;
        this.TriviaContestID = TriviaContestID;
    }

    public int getParticipationID() {
        return ParticipationID;
    }

    public void setParticipationID(int ParticipationID) {
        this.ParticipationID = ParticipationID;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    public int getTriviaContestID() {
        return TriviaContestID;
    }

    public void setTriviaContestID(int TriviaContestID) {
        this.TriviaContestID = TriviaContestID;
    }
    
}

