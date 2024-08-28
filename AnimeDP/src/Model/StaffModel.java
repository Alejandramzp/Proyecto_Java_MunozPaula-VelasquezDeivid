/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

public class StaffModel {
    private int staffID;
    private int eventID;
    private String name;
    private String identification;
    private LocalDate dateOfBirth;
    private int roleID;
    private String status;

    public StaffModel(int eventID, String name, String identification, LocalDate dateOfBirth, int roleID, String status) {
        this.eventID = eventID;
        this.name = name;
        this.identification = identification;
        this.dateOfBirth = dateOfBirth;
        this.roleID = roleID;
        this.status = "No Trabajando";
    }

    public int getStaffID() { return staffID; }
    public void setStaffID(int staffID) { this.staffID = staffID; }
    public int getEventID() { return eventID; }
    public void setEventID(int eventID) { this.eventID = eventID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public int getRoleID() { return roleID; }
    public void setRoleID(int roleID) { this.roleID = roleID; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
