
package View;

import Controller.CashRegisterController;
import Model.CashRegisterModel;
import java.util.Scanner;


public class CashRegisterView {
    
    private CashRegisterController cashRegisterController;
    private Scanner scanner;

    public CashRegisterView() {
        this.cashRegisterController = new CashRegisterController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'           Gestión de Caja:            '");
            System.out.println("'                                       '");
            System.out.println("'    1. Añadir Caja                     '");
            System.out.println("'    2. Mostrar todas las Cajas         '");
            System.out.println("'    3. Mostrar Caja por ID             '");
            System.out.println("'    4. Actualizar Caja por ID          '");
            System.out.println("'    5. Activar Caja                    '");
            System.out.println("'    6. Desactivar Caja                 '");
            System.out.println("'    7. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addCashRegister();
                case 2 -> showAllCashRegister();
                case 3 -> showCashRegisterById();
                case 4 -> updateCashRegister();
                case 5 -> activateCashRegister();
                case 6 -> desactivateCashRegister();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 7);  
    }
    
    private void addCashRegister() {
        System.out.println("\n-----------------------------------------");
        System.out.println("           Añadir Nueva Caja          ");
        System.out.println("-------------------------------------------");
        
        int businessStaffID;
        
        // Validar el ID del evento
        while (true) {
            try {
                System.out.println("Id del personal de comercio que trabajará en la caja a crear: ");
                businessStaffID = scanner.nextInt();
                scanner.nextLine();
                
                if(cashRegisterController.isStaffExists(businessStaffID)){
                    break; // Si no hay excepción, salir del bucle
                }else{
                    System.out.println("El personal de comercio no se encuentra registrado");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un ID de personal de comercio diferente.");
            }
        }
        
        System.out.println("Monto de apertura: ");
        double openingAmount = scanner.nextDouble();
        scanner.nextLine();
  
        CashRegisterModel cashRegister = new CashRegisterModel(0,openingAmount,businessStaffID);
        
        try {
            if (cashRegisterController.addCashRegister(cashRegister)) {
                System.out.println("Caja añadida exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }      
    }
    
    private void showAllCashRegister() {
        System.out.println("\n----------------------------------------");
        System.out.println("     Listado de todas las Cajas:          ");
        System.out.println("------------------------------------------");
        cashRegisterController.getAllCashRegisterModel().forEach(cashRegister -> System.out.println(
                
                "-> ID: " + cashRegister.getCashRegisterID() +
                ", Estado: " + cashRegister.getStatus() +
                ", Monto de apertura: " + cashRegister.getOpeningAmount() +
                ", Monto de cierre: " + cashRegister.getClosingAmount() +
                ", ID Personal de Comercio: " + cashRegister.getBusinessStaffID()+ "\n"));                
    }
    
    private void showCashRegisterById(){
        int cashRegisterId;

        // Validar el ID de la caja
        while (true) {
            try {
                System.out.println("Id de la Caja a mostrar: ");
                cashRegisterId = scanner.nextInt();
                scanner.nextLine();
                
                CashRegisterModel cashRegister = cashRegisterController.getCashRegisterById(cashRegisterId);
                
                if(cashRegister != null){
                    System.out.println("\n----------------------------------------");
                    System.out.println("Detalles de la Caja:");
                    System.out.println("ID: " + cashRegister.getCashRegisterID());
                    System.out.println("Estado: " + cashRegister.getStatus());
                    System.out.println("Monto de apertura: " + cashRegister.getOpeningAmount());
                    System.out.println("Monto de cierre: " + cashRegister.getClosingAmount());
                    System.out.println("ID Personal de Comercio: " + cashRegister.getBusinessStaffID());
                    break; // Si no hay excepción, salir del bucle
                }else{
                    System.out.println("La caja no se encuentra registrada");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un ID de utilería diferente.");
            }
        }
        System.out.println("------------------------------------------");
    }
    
    private void updateCashRegister() {
    System.out.println("\n-----------------------------------------");
    System.out.println("        Actualizar Caja por ID           ");
    System.out.println("-----------------------------------------");

    System.out.print("Ingrese el ID de la caja a actualizar: ");
    int cashRegisterID = scanner.nextInt();
    scanner.nextLine(); 

    CashRegisterModel existingRegister = cashRegisterController.getCashRegisterById(cashRegisterID);
    
    if (existingRegister != null) {
        System.out.println("Caja encontrada: " + existingRegister.getStatus() + " - Monto de Apertura: " + existingRegister.getOpeningAmount());
        
        System.out.print("Nuevo monto de apertura: ");
        double openingAmount = scanner.nextDouble();
        scanner.nextLine();
        existingRegister.setOpeningAmount(openingAmount);

        System.out.print("Nuevo ID del personal de comercio: ");
        int businessStaffID = scanner.nextInt();
        scanner.nextLine();
        existingRegister.setBusinessStaffID(businessStaffID);

        if (cashRegisterController.updateCashRegister(existingRegister)) {
            System.out.println("Caja actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la caja.");
        }
    } else {
        System.out.println("Caja no encontrada con el ID especificado.");
      }
    }
    
    private void activateCashRegister() {
        System.out.print("Ingrese el ID de la caja a activar: ");
        int cashRegisterID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Monto de apertura: ");
        double openingAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("ID del personal de comercio: ");
        int businessStaffID = scanner.nextInt();
        scanner.nextLine();

        if (cashRegisterController.activateCashRegister(cashRegisterID, openingAmount, businessStaffID)) {
            System.out.println("Caja activada exitosamente.");
        } else {
            System.out.println("Error al activar la caja.");
        }
    }

    private void desactivateCashRegister() {
        System.out.print("Ingrese el ID de la caja a desactivar: ");
        int cashRegisterID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Monto de cierre: ");
        double closingAmount = scanner.nextDouble();
        scanner.nextLine();

        if (cashRegisterController.desactivateCashRegister(cashRegisterID, closingAmount)) {
            System.out.println("Caja desactivada exitosamente.");
        } else {
            System.out.println("Error al desactivar la caja.");
        }
    }
    
    public static void main(String[] args) {
        CashRegisterView view = new CashRegisterView();
        view.displayMenu();
    }
    
}
