
package Controller;

import Dao.TicketOfficeDao;
import Model.TicketOfficeModel;

public class TicketOfficeController {
    
    private TicketOfficeDao ticketOfficeDao;
    
    public TicketOfficeController() {
        ticketOfficeDao = new TicketOfficeDao();
    }
    
    public boolean addTicketOffice(int eventID, String location, String contactNumber, int staffnChargeID) {
        // Validación de datos antes de crear el objeto TicketOfficeModel
        if (location == null || location.isEmpty()) {
            System.out.println("Error: La ubicación no puede estar vacía.");
            return false;
        }
        
        if (contactNumber == null || contactNumber.isEmpty()) {
            System.out.println("Error: El número de contacto no puede estar vacío.");
            return false;
        }
        
        // Validación del formato del número de contacto (Ejemplo: validación simple)
        if (!contactNumber.matches("\\d{10}")) {
            System.out.println("Error: El número de contacto debe tener 10 dígitos.");
            return false;
        }
        
        // Creación del objeto TicketOfficeModel
        TicketOfficeModel office = new TicketOfficeModel(eventID, location, contactNumber, staffnChargeID);
        
        // Llamar al DAO para agregar la taquilla
        boolean result = ticketOfficeDao.addTicketOffice(office);
        
        return result;
    }
    
    // Otros métodos de control, por ejemplo:
    
    public boolean isEventIDValid(int eventID) {
        return ticketOfficeDao.isEventIDExists(eventID);
    }
    
    public boolean isStaffIDValid(int staffID) {
        return ticketOfficeDao.isStaffnChargeIDExists(staffID);
    }
    
}

