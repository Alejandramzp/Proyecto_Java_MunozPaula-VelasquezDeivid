/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.StaffController;
import Controller.RolController;
import Controller.ActivityRoleController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StaffView {
    private StaffController staffController;
    private RolController rolController;
    private ActivityRoleController activityRoleController;
    private Scanner scanner;

    public StaffView() {
        this.staffController = new StaffController();
        this.rolController = new RolController();
        this.activityRoleController = new ActivityRoleController();
        this.scanner = new Scanner(System.in);
    }

    // Método principal para mostrar el menú e interactuar con el usuario
    public void displayMenu() {
        int option;
        do {
            System.out.println("\n--- Menú de Gestión de Personal ---");
            System.out.println("1. Agregar nuevo miembro de personal");
            System.out.println("2. Ver todos los miembros del personal");
            System.out.println("3. Ver detalles de la relación entre Actividad, Rol y Personal");
            System.out.println("4. Agregar nuevo Rol");
            System.out.println("5. Agregar nueva Relación Actividad-Rol");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    addStaff();
                    break;
                case 2:
                    viewAllStaff();
                    break;
                case 3:
                    viewActivityRoleStaffDetails();
                    break;
                case 4:
                    addRole();
                    break;
                case 5:
                    addActivityRole();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (option != 0);
    }

    // Método para agregar un nuevo miembro de personal
    private void addStaff() {
        System.out.print("Ingresa el ID del evento: ");
        int eventID = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingresa el nombre: ");
        String name = scanner.nextLine();

        System.out.print("Ingresa la identificación: ");
        String identification = scanner.nextLine();

        System.out.print("Ingresa la fecha de nacimiento (YYYY-MM-DD): ");
        String dobInput = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dobInput, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Ingresa el ID del rol: ");
        int roleID = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String statusCode = "No Trabajando";

        boolean result = staffController.addStaff(eventID, name, identification, dateOfBirth, roleID, statusCode);
        if (result) {
            System.out.println("¡Miembro de personal agregado exitosamente!");
        } else {
            System.out.println("Error al agregar el miembro de personal.");
        }
    }

    // Método para ver todos los miembros del personal
    private void viewAllStaff() {
        staffController.viewAllStaff();
    }

    // Método para ver detalles de la relación entre Actividad, Rol y Personal
    private void viewActivityRoleStaffDetails() {
        System.out.println("Mostrando detalles de la relación entre Actividad, Rol y Personal:");
        rolController.viewAllRoles(); // Asume que esto imprimirá la información relacionada
    }

    // Método para agregar un nuevo rol
    private void addRole() {
        System.out.print("Ingresa el nombre del rol: ");
        String roleName = scanner.nextLine();

        System.out.print("Ingresa el ID de la primera actividad: ");
        int activity1ID = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingresa el ID de la segunda actividad: ");
        int activity2ID = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        rolController.addRole(roleName, activity1ID, activity2ID);
        
    }

    // Método para agregar una nueva relación ActivityRole
    private void addActivityRole() {
        System.out.print("Ingresa el nombre de la actividad: ");
        String activityName = scanner.nextLine();

        activityRoleController.addActivityRole(activityName);
    }

    public static void main(String[] args) {
        StaffView staffView = new StaffView();
        staffView.displayMenu();
    }
}

