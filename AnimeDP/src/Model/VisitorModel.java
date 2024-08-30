
package Model;

import java.util.Date;

public class VisitorModel {
    private int VisitorID;
    private String Name;
    private String IdentificationDocument;
    private String Gender;
    private Date DateOfBirth;
    private String Email;
    private String PhoneNumber;
    private String Status;

    // Constructor
    public VisitorModel(int VisitorID, String Name, String IdentificationDocument, String Gender, Date DateOfBirth, String Email, String PhoneNumber, String Status) {
        this.VisitorID = VisitorID;
        this.Name = Name;
        this.IdentificationDocument = IdentificationDocument;
        this.Gender = Gender;
        this.DateOfBirth = DateOfBirth;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Status = Status;
    }

    // Getters and Setters
    public int getVisitorID() { return VisitorID; }
    public void setVisitorID(int VisitorID) { this.VisitorID = VisitorID; }
    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }
    public String getIdentificationDocument() { return IdentificationDocument; }
    public void setIdentificationDocument(String IdentificationDocument) { this.IdentificationDocument = IdentificationDocument; }
    public String getGender() { return Gender; }
    public void setGender(String Gender) { this.Gender = Gender; }
    public Date getDateOfBirth() { return DateOfBirth; }
    public void setDateOfBirth(Date DateOfBirth) { this.DateOfBirth = DateOfBirth; }
    public String getEmail() { return Email; }
    public void setEmail(String Email) { this.Email = Email; }
    public String getPhoneNumber() { return PhoneNumber; }
    public void setPhoneNumber(String PhoneNumber) { this.PhoneNumber = PhoneNumber; }
    public String getStatus() { return Status; }
    public void setStatus(String Status) { this.Status = Status; }
}
