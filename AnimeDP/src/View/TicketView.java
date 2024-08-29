/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.TicketController;

import java.util.Scanner;

public class TicketView {

    private TicketController ticketController;
    private Scanner scanner;

    // Constructor para inicializar el controlador y el escáner
    public TicketView() {
        this.ticketController = new TicketController();
        this.scanner = new Scanner(System.in);
    }

    // Método para mostrar el menú de gestión de tickets
    public void showTicketMenu() {
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
                    System.out.println("Saliendo del menú de gestión de tickets...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 3);
    }

    // Método main para pruebas o ejecución directa
    public static void main(String[] args) {
        TicketView ticketView = new TicketView();
        ticketView.showTicketMenu();
    }
}

