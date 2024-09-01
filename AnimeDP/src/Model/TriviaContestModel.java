
package Model;

public class TriviaContestModel {
    private int TriviaContestID;
    private String Name;
    private int CategoryID;

    public TriviaContestModel(int TriviaContestID, String Name, int CategoryID) {
        this.TriviaContestID = TriviaContestID;
        this.Name = Name;
        this.CategoryID = CategoryID;
    }

    public int getTriviaContestID() {
        return TriviaContestID;
    }

    public void setTriviaContestID(int TriviaContestID) {
        this.TriviaContestID = TriviaContestID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }
    
}

