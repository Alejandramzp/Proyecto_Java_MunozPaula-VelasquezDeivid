
package View;

import Controller.TicketOfficeController;
import Model.TicketOfficeModel;

import java.util.Scanner;

public class TicketOfficeView {

    private TicketOfficeController controller;

    public TicketOfficeView(TicketOfficeController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("-----------------------------------------");
            System.out.println("'          Gestión de Taquillas         '");
            System.out.println("'    1. Agregar nueva taquilla          '");
            System.out.println("'    2. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (option) {
                case 1:
                    addTicketOffice();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 2);

        scanner.close();
    }

    private void addTicketOffice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("           Agregar Nueva Taquilla        ");
        System.out.println("-----------------------------------------");

        System.out.print("Ingrese el ID del evento: ");
        int eventID = scanner.nextInt();

        scanner.nextLine(); // Consumir nueva línea

        System.out.print("Ingrese la ubicación de la taquilla: ");
        String location = scanner.nextLine();

        System.out.print("Ingrese el número de contacto: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Ingrese el ID del staff a cargo: ");
        int staffnChargeID = scanner.nextInt();

        // Crear un objeto TicketOfficeModel con los datos ingresados
        TicketOfficeModel ticketOffice = new TicketOfficeModel(eventID, location, contactNumber, staffnChargeID);

        // Intentar agregar la taquilla utilizando el controlador
        boolean success = controller.addTicketOffice(eventID, location, contactNumber, staffnChargeID);

        if (success) {
            System.out.println("Taquilla agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la taquilla. Por favor verifique los datos e intente nuevamente.");
        }
    }

    public static void main(String[] args) {
        // Crear una instancia del controlador
        TicketOfficeController controller = new TicketOfficeController();

        // Crear una instancia de la vista
        TicketOfficeView view = new TicketOfficeView(controller);

        // Mostrar el menú
        view.showMenu();
    }
}


