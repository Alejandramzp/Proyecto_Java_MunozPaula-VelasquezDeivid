
package View;

import Controller.ActivityParticipationController;

import java.util.Scanner;

public class ActivityParticipationView {

    private ActivityParticipationController participationController;
    private Scanner scanner;

    public ActivityParticipationView() {
        this.participationController = new ActivityParticipationController();
        this.scanner = new Scanner(System.in);
    }

    public void showParticipationMenu() {
        int option;

        do {
            System.out.println("---------------------------------------------------");
            System.out.println("' Menú de Gestión de Participación en Actividades '");
            System.out.println("'            1. Añadir participación              '");
            System.out.println("'            2. Salir                             '");
            System.out.println("---------------------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    participationController.addParticipation();
                    break;
                case 2:
                    System.out.println("Saliendo del menú de participación...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 2);
    }

    // Método main separado para pruebas o ejecución directa
    public static void main(String[] args) {
        ActivityParticipationView participationView = new ActivityParticipationView();
        participationView.showParticipationMenu();
    }
}


