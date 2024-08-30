
package Model;

public class TicketModel {
    private int TicketID;
    private String TicketName;
    private double Price;
    private String AgeRating;
    private double AdditionalCost;
    private String Status;
    private int VisitorID;
    private int TicketOfficeID;

    public TicketModel(int TicketID, String TicketName, double Price, String AgeRating, double AdditionalCost, String Status, int VisitorID, int TicketOfficeID) {
        this.TicketID = TicketID;
        this.TicketName = TicketName;
        this.Price = Price;
        this.AgeRating = AgeRating;
        this.AdditionalCost = AdditionalCost;
        this.Status = Status;
        this.VisitorID = VisitorID;
        this.TicketOfficeID = TicketOfficeID;
    }

    public int getTicketID() {
        return TicketID;
    }

    public void setTicketID(int TicketID) {
        this.TicketID = TicketID;
    }

    public String getTicketName() {
        return TicketName;
    }

    public void setTicketName(String TicketName) {
        this.TicketName = TicketName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getAgeRating() {
        return AgeRating;
    }

    public void setAgeRating(String AgeRating) {
        this.AgeRating = AgeRating;
    }

    public double getAdditionalCost() {
        return AdditionalCost;
    }

    public void setAdditionalCost(double AdditionalCost) {
        this.AdditionalCost = AdditionalCost;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getVisitorID() {
        return VisitorID;
    }

    public void setVisitorID(int VisitorID) {
        this.VisitorID = VisitorID;
    }

    public int getTicketOfficeID() {
        return TicketOfficeID;
    }

    public void setTicketOfficeID(int TicketOfficeID) {
        this.TicketOfficeID = TicketOfficeID;
    }

}
