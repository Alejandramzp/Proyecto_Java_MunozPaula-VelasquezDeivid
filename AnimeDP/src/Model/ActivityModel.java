/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ActivityModel {
    private int activityID;
    private String name;
    private String type;
    private int categoryID;
    private int numberOfParticipants;
    private int eventID;
    private String startTime;
    private int staffID;

    public ActivityModel(int activityID, String name, String type, int categoryID, int numberOfParticipants, int eventID, String startTime, int staffID) {
        this.activityID = activityID;
        this.name = name;
        this.type = type;
        this.categoryID = categoryID;
        this.numberOfParticipants = numberOfParticipants;
        this.eventID = eventID;
        this.startTime = startTime;
        this.staffID = staffID;
    }

    // Getters and Setters
    public int getActivityID() { return activityID; }
    public void setActivityID(int activityID) { this.activityID = activityID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

    public int getNumberOfParticipants() { return numberOfParticipants; }
    public void setNumberOfParticipants(int numberOfParticipants) { this.numberOfParticipants = numberOfParticipants; }

    public int getEventID() { return eventID; }
    public void setEventID(int eventID) { this.eventID = eventID; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
}

