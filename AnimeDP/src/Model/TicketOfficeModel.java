/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class TicketOfficeModel {
    private int EventID;
    private String Location;
    private String ContacNumber;
    private int StaffInChargeID;

    public TicketOfficeModel(int EventID, String Location, String ContacNumber, int StaffInChargeID) {
        this.EventID = EventID;
        this.Location = Location;
        this.ContacNumber = ContacNumber;
        this.StaffInChargeID = StaffInChargeID;
    }

    public int getEventID() {
        return EventID;
    }

    public void setEventID(int EventID) {
        this.EventID = EventID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getContacNumber() {
        return ContacNumber;
    }

    public void setContacNumber(String ContacNumber) {
        this.ContacNumber = ContacNumber;
    }

    public int getStaffInChargeID() {
        return StaffInChargeID;
    }

    public void setStaffInChargeID(int StaffInChargeID) {
        this.StaffInChargeID = StaffInChargeID;
    }

    
}
