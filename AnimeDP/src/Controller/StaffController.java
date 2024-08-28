/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.StaffDao;
import Model.StaffModel;

import java.time.LocalDate;
import java.util.List;

public class StaffController {
    private StaffDao staffDao;

    public StaffController() {
        this.staffDao = new StaffDao();
    }

    // Método para convertir un número a un estado de staff
    private String getStatusFromCode(int code) {
        switch (code) {
            case 1:
                
            case 2:
                return "Despedido";
            case 3:
                return "Incapacitado";
            default:
                return null;
        }
    }

    
    public boolean addStaff(int eventID, String name, String identification, LocalDate dateOfBirth, int roleID, String statusCode) {
        String status = "No Trabajando";

        StaffModel newStaff = new StaffModel(eventID, name, identification, dateOfBirth, roleID, status);
        return staffDao.addStaff(newStaff);
    }

    // Método para visualizar todos los registros de personal
    public void viewAllStaff() {
        List<StaffModel> staffList = staffDao.viewAllStaff();
        if (staffList.isEmpty()) {
            System.out.println("No hay registros de personal para mostrar.");
        } else {
            for (StaffModel staff : staffList) {
                System.out.println("StaffID: " + staff.getStaffID());
                System.out.println("EventID: " + staff.getEventID());
                System.out.println("Nombre: " + staff.getName());
                System.out.println("Identificación: " + staff.getIdentification());
                System.out.println("Fecha de Nacimiento: " + staff.getDateOfBirth());
                System.out.println("RoleID: " + staff.getRoleID());
                System.out.println("Estado: " + staff.getStatus());
                System.out.println("-----------------------------");
            }
        }
    }
}

