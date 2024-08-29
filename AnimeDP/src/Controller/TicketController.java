/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.TicketDao;
import Model.TicketModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class TicketController {
    private TicketDao ticketDao;

    public TicketController() {
        this.ticketDao = new TicketDao();
    }

    public void createTicket() {
        Scanner scanner = new Scanner(System.in);
        
        int TicketID = 0;
        
        System.out.println("Ingrese el nombre del ticket:");
        String ticketName = scanner.nextLine();

        System.out.println("Ingrese el precio del ticket:");
        double price = scanner.nextDouble();

        System.out.println("Ingrese la clasificación por edades del ticket (opcional):");
        scanner.nextLine();  // Consume newline
        String ageRating = scanner.nextLine();

        System.out.println("Ingrese el costo adicional del ticket (opcional):");
        double additionalCost = scanner.nextDouble();

        System.out.println("Seleccione el estado del ticket:");
        System.out.println("1. Pagado");
        System.out.println("2. Reservado");
        int statusChoice = scanner.nextInt();
        String status = (statusChoice == 1) ? "Pagado" : "Reservado";

        System.out.println("Ingrese el ID del visitante:");
        int visitorID = scanner.nextInt();

        System.out.println("Ingrese el ID de la taquilla:");
        int ticketOfficeID = scanner.nextInt();

        TicketModel ticket = new TicketModel(TicketID, ticketName, price, ageRating, additionalCost, status, visitorID, ticketOfficeID);
        if (ticketDao.addTicket(ticket)) {
            System.out.println("Ticket añadido exitosamente.");
        } else {
            System.out.println("Error al añadir el ticket.");
        }
    }

    public void listTickets() {
        List<TicketModel> tickets = ticketDao.getAllTickets();
        tickets.forEach(ticket -> {
            System.out.println("ID: " + ticket.getTicketID());
            System.out.println("Nombre: " + ticket.getTicketName());
            System.out.println("Precio: " + ticket.getPrice());
            System.out.println("Clasificación por Edades: " + ticket.getAgeRating());
            System.out.println("Costo Adicional: " + ticket.getAdditionalCost());
            System.out.println("Estado: " + ticket.getStatus());
            System.out.println("ID del Visitante: " + ticket.getVisitorID());
            System.out.println("ID de la Taquilla: " + ticket.getTicketOfficeID());
            System.out.println("-------------");
        });
    }
}

