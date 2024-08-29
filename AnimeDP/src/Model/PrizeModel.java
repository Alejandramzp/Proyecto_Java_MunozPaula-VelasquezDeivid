/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class PrizeModel {
    private int PrizeID;
    private String Type;
    private String Description;
    private double Value;
    private String Status;
    private int ActivityID;
    private int VisitorID;
    private int BusinessID;

    public PrizeModel(int PrizeID, String Type, String Description, double Value, String Status, int ActivityID, int VisitorID, int BusinessID) {
        this.PrizeID = PrizeID;
        this.Type = Type;
        this.Description = Description;
        this.Value = Value;
        this.Status = Status;
        this.ActivityID = ActivityID;
        this.VisitorID = VisitorID;
        this.BusinessID = BusinessID;
    }

    public int getPrizeID() {
        return PrizeID;
    }

    public void setPrizeID(int PrizeID) {
        this.PrizeID = PrizeID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getActivityID() {
        return ActivityID;
    }

    public void setActivityID(int ActivityID) {
        this.ActivityID = ActivityID;
    }

    public int getVisitorID() {
        return VisitorID;
    }

    public void setVisitorID(int VisitorID) {
        this.VisitorID = VisitorID;
    }

    public int getBusinessID() {
        return BusinessID;
    }

    public void setBusinessID(int BusinessID) {
        this.BusinessID = BusinessID;
    }
    
}
