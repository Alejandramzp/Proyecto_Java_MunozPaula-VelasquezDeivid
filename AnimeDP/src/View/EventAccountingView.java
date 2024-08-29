/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.EventAccountingController;
import java.util.Scanner;

public class EventAccountingView {

    public static void main(String[] args) {
        EventAccountingController accountingController = new EventAccountingController();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nMenú de Gestión de Contabilidad de Eventos");
            System.out.println("1. Mostrar total de boletos vendidos");
            System.out.println("2. Mostrar monto total recaudado por categoría");
            System.out.println("3. Mostrar boletos disponibles para un evento");
            System.out.println("4. Mostrar participantes por actividad");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    accountingController.generateTotalTicketsSoldReport(); 
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la categoría: ");
                    int categoryID = scanner.nextInt();
                    accountingController.generateTotalAmountByCategoryReport(categoryID);
                    break;
                case 3:
                    System.out.print("Ingrese el ID del evento: ");
                    int eventID = scanner.nextInt();
                    accountingController.generateAvailableTicketsReport(eventID);
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la actividad: ");
                    int activityID = scanner.nextInt();
                    accountingController.generateParticipantsByActivityReport(activityID); 
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 5);

        scanner.close();
    }
}


