/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ActivityController;
import Model.ActivityModel;

import java.util.List;
import java.util.Scanner;

public class ActivityView {
    private ActivityController activityController;
    private Scanner scanner;

    public ActivityView() {
        activityController = new ActivityController();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("\n=== Menú de Actividades ===");
            System.out.println("1. Agregar actividad");
            System.out.println("2. Mostrar todas las actividades");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    addActivity();
                    break;
                case 2:
                    showAllActivities();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (option != 3);
    }

    private void addActivity() {
        System.out.print("Ingrese el nombre de la actividad: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el tipo de actividad (Cosplay/Trivia): ");
        String type = scanner.nextLine();
        System.out.print("Ingrese la categoría ID (0 si no aplica): ");
        int categoryID = scanner.nextInt();
        System.out.print("Ingrese el número de participantes: ");
        int numberOfParticipants = scanner.nextInt();
        System.out.print("Ingrese el evento ID: ");
        int eventID = scanner.nextInt();
        System.out.print("Ingrese la hora de inicio (HH:MM:SS): ");
        String startTime = scanner.next();
        System.out.print("Ingrese el personal ID: ");
        int staffID = scanner.nextInt();

        ActivityModel activity = new ActivityModel(0, name, type, categoryID, numberOfParticipants, eventID, startTime, staffID);
        boolean isAdded = activityController.addActivity(activity);

        if (isAdded) {
            System.out.println("Actividad agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la actividad.");
        }
    }

    private void showAllActivities() {
        List<ActivityModel> activities = activityController.getAllActivities();
        if (activities.isEmpty()) {
            System.out.println("No hay actividades disponibles.");
        } else {
            System.out.println("\n=== Lista de Actividades ===");
            for (ActivityModel activity : activities) {
                System.out.println("ID: " + activity.getActivityID() +
                                   ", Nombre: " + activity.getName() +
                                   ", Tipo: " + activity.getType() +
                                   ", Categoría ID: " + activity.getCategoryID() +
                                   ", Número de Participantes: " + activity.getNumberOfParticipants() +
                                   ", Evento ID: " + activity.getEventID() +
                                   ", Hora de Inicio: " + activity.getStartTime() +
                                   ", Personal ID: " + activity.getStaffID());
            }
        }
    }

    public static void main(String[] args) {
        ActivityView activityView = new ActivityView();
        activityView.displayMenu();
    }
}

