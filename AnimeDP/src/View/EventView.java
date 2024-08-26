/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.EventController;
import Model.EventModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EventView {

    public static void main(String[] args) {
        EventController controller = new EventController();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Ingrese los detalles del evento:");

        String name = "";
        boolean validName = false;
        while (!validName) {
            System.out.print("Nombre del Evento: ");
            name = scanner.nextLine();

            if (controller.isEventNameExists(name)) {
                System.out.println("Ya existe un evento con este nombre. Por favor, ingrese un nombre diferente.");
            } else {
                validName = true;
            }
        }

        System.out.print("País: ");
        String country = scanner.nextLine();

        System.out.print("Ciudad: ");
        String city = scanner.nextLine();

        System.out.print("Dirección: ");
        String address = scanner.nextLine();

        System.out.print("Capacidad Máxima de Personas: ");
        int maxPersonCapacity = Integer.parseInt(scanner.nextLine());

        System.out.print("Capacidad Máxima de Tiendas: ");
        int maxStoreCapacity = Integer.parseInt(scanner.nextLine());

        System.out.print("Capacidad Máxima de Restaurantes: ");
        int maxRestaurantCapacity = Integer.parseInt(scanner.nextLine());

        LocalDate eventDate = null;
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Fecha (formato yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            try {
                eventDate = LocalDate.parse(dateInput, dateFormatter);
                LocalDate currentDate = LocalDate.now();

                if (eventDate.isAfter(currentDate.plusDays(7))) {
                    validDate = true;
                } else {
                    System.out.println("La fecha debe ser al menos 7 días en el futuro. Intente nuevamente.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }

        System.out.print("Hora (formato HH:mm): ");
        String time = scanner.nextLine();

        System.out.print("Organizador: ");
        String organizer = scanner.nextLine();

        System.out.print("Clasificación por Edad: ");
        String ageRating = scanner.nextLine();

        System.out.print("Estado (Activo/Completado/Pendiente): ");
        String status = scanner.nextLine();


        EventModel event = new EventModel(
                0, 
                name, country, city, address,
                maxPersonCapacity, maxStoreCapacity, maxRestaurantCapacity,
                eventDate.toString(), time, organizer, ageRating, status
        );

        if (controller.addEvent(event)) {
            System.out.println("Evento añadido exitosamente.");
        } else {
            System.out.println("No se pudo añadir el evento.");
        }

        System.out.println("Lista de todos los eventos:");
        controller.getAllEvents().forEach(System.out::println);

        scanner.close();
    }
}



