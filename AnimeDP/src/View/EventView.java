/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.EventModel;
import Dao.EventDao;

import java.util.Scanner;

public class EventView {

    private Scanner scanner;
    private EventDao dao;

    public EventView() {
        scanner = new Scanner(System.in);
        dao = new EventDao();
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nEvent Management System");
            System.out.println("1. Create Event");
            System.out.println("2. View Event by ID");
            System.out.println("3. Update Event Status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    viewEvent();
                    break;
                case 3:
                    updateEventStatus();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createEvent() {
        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter max person capacity: ");
        int maxPersonCapacity = scanner.nextInt();
        System.out.print("Enter max store capacity: ");
        int maxStoreCapacity = scanner.nextInt();
        System.out.print("Enter max restaurant capacity: ");
        int maxRestaurantCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine();
        System.out.print("Enter organizer: ");
        String organizer = scanner.nextLine();
        System.out.print("Enter age rating: ");
        String ageRating = scanner.nextLine();
        System.out.print("Enter status (Pendiente, Activo, Completado): ");
        String status = scanner.nextLine();

        EventModel event = new EventModel(name, country, city, address, maxPersonCapacity, maxStoreCapacity, maxRestaurantCapacity, date, time, organizer, ageRating, status);

        if (dao.isEventNameExists(name)) {
            System.out.println("Event with this name already exists.");
            return;
        }

        boolean success = dao.addEvent(event);
        if (success) {
            System.out.println("Event created successfully.");
        } else {
            System.out.println("Failed to create event.");
        }
    }

    private void viewEvent() {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        EventModel event = dao.getEventById(eventId);
        if (event != null) {
            System.out.println("Event ID: " + event.getId());
            System.out.println("Name: " + event.getName());
            System.out.println("Country: " + event.getCountry());
            System.out.println("City: " + event.getCity());
            System.out.println("Address: " + event.getAddress());
            System.out.println("Max Person Capacity: " + event.getMaxPersonCapacity());
            System.out.println("Max Store Capacity: " + event.getMaxStoreCapacity());
            System.out.println("Max Restaurant Capacity: " + event.getMaxRestaurantCapacity());
            System.out.println("Date: " + event.getDate());
            System.out.println("Time: " + event.getTime());
            System.out.println("Organizer: " + event.getOrganizer());
            System.out.println("Age Rating: " + event.getAgeRating());
            System.out.println("Status: " + event.getStatus());
        } else {
            System.out.println("Event not found.");
        }
    }

    private void updateEventStatus() {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new status (Pendiente, Activo, Completado): ");
        String status = scanner.nextLine();

        EventModel event = dao.getEventById(eventId);
        if (event != null) {
            event.setStatus(status);
            boolean success = dao.updateEventStatus(event);
            if (success) {
                System.out.println("Event status updated successfully.");
            } else {
                System.out.println("Failed to update event status.");
            }
        } else {
            System.out.println("Event not found.");
        }
    }

    public static void main(String[] args) {
        EventView eventView = new EventView();
        
        eventView.showMenu();
    }
}

