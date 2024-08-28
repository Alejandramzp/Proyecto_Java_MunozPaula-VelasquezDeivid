/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.TicketController;

import java.util.Scanner;

public class TicketView {

    public static void main(String[] args) {
        TicketController ticketController = new TicketController();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menú de Gestión de Tickets");
            System.out.println("1. Crear nuevo ticket");
            System.out.println("2. Listar todos los tickets");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    ticketController.createTicket();
                    break;
                case 2:
                    ticketController.listTickets();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 3);

        scanner.close();
    }
}

