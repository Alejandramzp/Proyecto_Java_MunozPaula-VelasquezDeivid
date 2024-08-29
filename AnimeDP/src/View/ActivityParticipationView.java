/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ActivityParticipationController;

import java.util.Scanner;

public class ActivityParticipationView {

    public static void main(String[] args) {
        ActivityParticipationController participationController = new ActivityParticipationController();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menú de Gestión de Participación en Actividades");
            System.out.println("1. Añadir participación");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    participationController.addParticipation();
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 2);

        scanner.close();
    }
}

