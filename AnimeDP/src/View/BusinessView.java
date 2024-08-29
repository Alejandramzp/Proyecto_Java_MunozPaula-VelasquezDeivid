
package View;

import Controller.BusinessController;
import Model.BusinessModel;

import java.util.List;
import java.util.Scanner;

public class BusinessView {
    private BusinessController businessController;
    private Scanner scanner;

    public BusinessView() {
        this.businessController = new BusinessController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'          Gestión de Comercios:        '");
            System.out.println("'                                       '");
            System.out.println("'    1. Añadir Comercio                 '");
            System.out.println("'    2. Mostrar todos los Comercios     '");
            System.out.println("'    3. Mostrar Comercio por ID         '");
            System.out.println("'    4. Actualizar Comercio             '");
            System.out.println("'    5. Asignar Personal a Comercio     '");
            System.out.println("'    6. Mostrar Personal de Comercio    '");
            System.out.println("'    7. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1 -> addBusiness();
                case 2 -> showAllBusinesses();
                case 3 -> showBusinessById();
                case 4 -> updateBusiness();
                case 5 -> assignStaffToBusiness();
                case 6 -> showStaffByBusiness();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 7);
    }

    private void addBusiness() {
        System.out.println("\n-----------------------------------------");
        System.out.println("           Añadir Nuevo Comercio         ");
        System.out.println("-----------------------------------------");

        System.out.print("Nombre del Negocio: ");
        String name = scanner.nextLine();
        
        System.out.println("\n-----------------------------------------");
        System.out.println("     Ingrese el tipo de Comercio:          ");
        System.out.println("                                           ");
        System.out.println("    1. Tienda                              ");
        System.out.println("    2. Restaurante                         ");
        System.out.println("      Seleccione una opción:               ");
        System.out.println("-------------------------------------------");
        
        int optionS;
        String type = "";
        
        while (true) {
            try {
                optionS = scanner.nextInt();
                scanner.nextLine();
                if (optionS == 1){
                    type = "Tienda"; 
                    break;
                }else if(optionS == 2){
                    type = "Restaurante";
                    break;
                }else{
                    System.out.println("Opción no valida. Por favor, seleccione 1 o 2.");
                }
            }catch (Exception e) {
                System.out.println("Entrada no valida. Por favor, ingrese un número entero.");
                scanner.next();
            }
        }

        System.out.print("ID del responsable: ");
        int inChargeID = scanner.nextInt();
        scanner.nextLine();

        BusinessModel business = new BusinessModel(0, name, type, inChargeID);

        if (businessController.addBusiness(business)) {
            System.out.println("Comercio añadido exitosamente.");
        } else {
            System.out.println("Error al añadir el Comercio.");
        }
    }

    private void showAllBusinesses() {
        System.out.println("\n-----------------------------------------");
        System.out.println("       Listado de todos los Comercios     ");
        System.out.println("-----------------------------------------");

        businessController.getAllBusinesses().forEach(business -> {
            System.out.println(
                    "-> ID: " + business.getBusinessID() +
                    ", Nombre: " + business.getName() +
                    ", Tipo: " + business.getType() +
                    ", ID Responsable: " + business.getInChargeID() + "\n"
            );
        });
    }

    private void showBusinessById() {
        System.out.print("Ingrese el ID del Comercio: ");
        int businessID = scanner.nextInt();
        scanner.nextLine();

        BusinessModel business = businessController.getBusinessById(businessID);

        if (business != null) {
            System.out.println(
                    "-> ID: " + business.getBusinessID() +
                    ", Nombre: " + business.getName() +
                    ", Tipo: " + business.getType() +
                    ", ID Responsable: " + business.getInChargeID() + "\n"
            );
        } else {
            System.out.println("Comercio no encontrado.");
        }
    }

    private void updateBusiness() {
        System.out.print("Ingrese el ID del Comercio a actualizar: ");
        int businessID = scanner.nextInt();
        scanner.nextLine();

        BusinessModel business = businessController.getBusinessById(businessID);

        if (business != null) {
            System.out.print("Nuevo nombre del Comercio: ");
            String newName = scanner.nextLine();
            
            System.out.println("\n-----------------------------------------");
            System.out.println("    Ingrese el nuevo tipo de Comercio:     ");
            System.out.println("                                           ");
            System.out.println("    1. Tienda                              ");
            System.out.println("    2. Restaurante                         ");
            System.out.println("      Seleccione una opción:               ");
            System.out.println("-------------------------------------------");
        
        int optionS;
        String newType = "";
        
        while (true) {
            try {
                optionS = scanner.nextInt();
                scanner.nextLine();
                if (optionS == 1){
                    newType = "Tienda"; 
                    break;
                }else if(optionS == 2){
                    newType = "Restaurante";
                    break;
                }else{
                    System.out.println("Opción no valida. Por favor, seleccione 1 o 2.");
                }
            }catch (Exception e) {
                System.out.println("Entrada no valida. Por favor, ingrese un número entero.");
                scanner.next();
            }
        }

            System.out.print("Nuevo ID del responsable: ");
            int newInChargeID = scanner.nextInt();
            scanner.nextLine();

            business.setName(newName);
            business.setType(newType);
            business.setInChargeID(newInChargeID);

            if (businessController.updateBusiness(business)) {
                System.out.println("Comercio actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el Comercio.");
            }
        } else {
            System.out.println("Comercio no encontrado.");
        }
    }

    private void assignStaffToBusiness() {
        System.out.print("Ingrese el ID del Comercio: ");
        int businessID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el ID del personal a asignar: ");
        int staffID = scanner.nextInt();
        scanner.nextLine();

        if (businessController.addStaffToBusiness(staffID, businessID)) {
            System.out.println("Personal asignado exitosamente.");
        } else {
            System.out.println("Error al asignar personal.");
        }
    }

    private void showStaffByBusiness() {
        System.out.print("Ingrese el ID del Comercio: ");
        int businessID = scanner.nextInt();
        scanner.nextLine();

        List<Integer> staffList = businessController.getStaffByBusiness(businessID);

        if (!staffList.isEmpty()) {
            System.out.println("\n-----------------------------------------");
            System.out.println("       Listado de personal del Comercio    ");
            System.out.println("-----------------------------------------");
            staffList.forEach(staffID -> {
                System.out.println("-> ID del Personal: " + staffID);
            });
        } else {
            System.out.println("No hay personal asignado a este Comercio.");
        }
    }
    
    public static void main(String[] args) {
        BusinessView  business = new BusinessView();
        business.displayMenu();
    }
}

