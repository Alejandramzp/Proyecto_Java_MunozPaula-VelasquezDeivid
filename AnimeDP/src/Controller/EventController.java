/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.EventDao;
import Model.EventModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class EventController {
    private EventDao eventDao;
    private Scanner scanner;

    public EventController() {
        this.eventDao = new EventDao();
        this.scanner = new Scanner(System.in);
    }

    public boolean addEvent(EventModel event) {
        return eventDao.addEvent(event);
    }

    public List<EventModel> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public boolean isEventNameExists(String name) {
        return eventDao.isEventNameExists(name);
    }

    public boolean isDateValid(String eventDate) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventLocalDate = LocalDate.parse(eventDate, formatter);

        long daysDifference = ChronoUnit.DAYS.between(today, eventLocalDate);

        return daysDifference >= 7;
    }

    public void updateEventStatus() {
        // Solicitar al usuario el ID del evento
        System.out.print("Ingrese el ID del evento a actualizar: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        // Obtener los detalles del evento
        EventModel event = eventDao.getEventById(eventId);
        if (event == null) {
            System.out.println("Evento con ID " + eventId + " no encontrado.");
            return;
        }

        // Mostrar estado actual del evento
        System.out.println("Estado actual del evento: " + event.getStatus());

        // Solicitar al usuario el nuevo estado
        System.out.println("Elija el nuevo estado del evento:");
        System.out.println("1. Activo");
        System.out.println("2. Finalizado");
        System.out.print("Seleccione una opción: ");
        int statusOption = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        String newStatus;
        LocalDate eventDate = LocalDate.parse(event.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate currentDate = LocalDate.now();

        switch (statusOption) {
            case 1:
                if (eventDate.equals(currentDate)) {
                    newStatus = "Activo";
                } else {
                    System.out.println("No se puede activar el evento. La fecha del evento debe ser hoy.");
                    return;
                }
                break;

            case 2:
                if (eventDate.isBefore(currentDate)) {
                    newStatus = "Finalizado";
                } else {
                    System.out.println("No se puede finalizar el evento. La fecha del evento debe ser anterior a hoy.");
                    return;
                }
                break;

            default:
                System.out.println("Opción de estado no válida. Intente de nuevo.");
                return;
        }

        // Actualizar el estado del evento
        event.setStatus(newStatus);
        if (eventDao.updateEventStatus(event)) {
            System.out.println("Estado del evento actualizado exitosamente a " + newStatus + ".");
        } else {
            System.out.println("Error al actualizar el estado del evento. Por favor, intente de nuevo.");
        }
    }

    // Método para actualizar el estado del evento basado en la fecha actual
    public void updateEventStatusByDate() {
        List<EventModel> events = getAllEvents();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (EventModel event : events) {
            LocalDate eventDate = LocalDate.parse(event.getDate(), formatter);

            if (currentDate.equals(eventDate)) {
                event.setStatus("Activo");
            } else if (currentDate.isAfter(eventDate)) {
                event.setStatus("Finalizado");
            }
            eventDao.updateEventStatus(event);
        }
    }
}



