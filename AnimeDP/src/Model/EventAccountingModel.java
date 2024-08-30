
package Model;

public class EventAccountingModel {
    private int accountingID;
    private int eventID;
    private int activityID;
    private int ticketsSold;
    private int activityParticipation;
    private double totalAmount;

    // Constructor
    public EventAccountingModel(int accountingID, int eventID, int activityID, int ticketsSold, int activityParticipation, double totalAmount) {
        this.accountingID = accountingID;
        this.eventID = eventID;
        this.activityID = activityID;
        this.ticketsSold = ticketsSold;
        this.activityParticipation = activityParticipation;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public int getAccountingID() {
        return accountingID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getActivityID() {
        return activityID;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public int getActivityParticipation() {
        return activityParticipation;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

