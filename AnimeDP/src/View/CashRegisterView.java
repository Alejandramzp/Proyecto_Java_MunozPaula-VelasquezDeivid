
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
            System.out.println("'    3. Mostrar las Cajas por ID        '");
            System.out.println("'    4. Actualizar Caja por ID          '");
            System.out.println("'    5. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addCashRegister();
                case 2 -> System.out.println("Saliendo...");
                case 3 -> System.out.println("Saliendo...");
                case 4 -> System.out.println("Saliendo...");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 5);  
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
    
    
    
    
    
    public static void main(String[] args) {
        CashRegisterView view = new CashRegisterView();
        view.displayMenu();
    }
    
}
