/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.EventController;
import Model.EventModel;

import java.util.Scanner;

public class EventView {
    private EventController eventController;
    private Scanner scanner;

    public EventView() {
        this.eventController = new EventController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("\nGestión de Eventos:");
            System.out.println("1. Añadir Evento");
            System.out.println("2. Mostrar todos los Eventos");
            System.out.println("3. Actualizar Estado de Eventos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addEvent();
                case 2 -> showAllEvents();
                case 3 -> updateEventStatus();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 4);
    }

    private void addEvent() {
        System.out.println("\nAñadir nuevo evento:");

        String name = null;
        String date = null;

        // Validar nombre del evento
        while (true) {
            try {
                System.out.print("Nombre del evento: ");
                name = scanner.nextLine();
                eventController.isEventNameExists(name);
                break; // Si no hay excepción, salir del bucle
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un nombre de evento diferente.");
            }
        }

        System.out.print("País: ");
        String country = scanner.nextLine();

        System.out.print("Ciudad: ");
        String city = scanner.nextLine();

        System.out.print("Dirección: ");
        String address = scanner.nextLine();

        System.out.print("Capacidad máxima de personas: ");
        int maxPersonCapacity = scanner.nextInt();

        System.out.print("Capacidad máxima de tiendas: ");
        int maxStoreCapacity = scanner.nextInt();

        System.out.print("Capacidad máxima de restaurantes: ");
        int maxRestaurantCapacity = scanner.nextInt();

        scanner.nextLine(); // Consume la nueva línea

        // Validar fecha del evento
        while (true) {
            try {
                System.out.print("Fecha (yyyy-mm-dd): ");
                date = scanner.nextLine();
                eventController.isDateValid(date);
                break; // Si no hay excepción, salir del bucle
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca una fecha válida (al menos 7 días desde hoy).");
            }
        }

        System.out.print("Hora (HH:MM): ");
        String time = scanner.nextLine();

        System.out.print("Organizador: ");
        String organizer = scanner.nextLine();

        System.out.print("Clasificación por edades: ");
        String ageRating = scanner.nextLine();

        EventModel event = new EventModel(0, name, country, city, address, maxPersonCapacity, maxStoreCapacity, maxRestaurantCapacity, date, time, organizer, ageRating);

        try {
            if (eventController.addEvent(event)) {
                System.out.println("Evento añadido exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAllEvents() {
        System.out.println("\nListado de todos los eventos:");
        eventController.getAllEvents().forEach(event -> System.out.println(
                "ID: " + event.getId() +
                ", Nombre: " + event.getName() +
                ", Fecha: " + event.getDate() +
                ", Estado: " + event.getStatus()));
    }
    
    private void updateEventStatus() {
        try {
            eventController.updateEventStatus();
        } catch (Exception e) {
            System.out.println("Se produjo un error al intentar actualizar el estado del evento: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventView view = new EventView();
        view.displayMenu();
    }
}
