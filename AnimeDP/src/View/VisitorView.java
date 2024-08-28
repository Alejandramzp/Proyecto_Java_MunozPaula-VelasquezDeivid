/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.VisitorController;

import java.util.Scanner;

public class VisitorView {

    public static void main(String[] args) {
        VisitorController visitorController = new VisitorController();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menú de Gestión de Visitantes");
            System.out.println("1. Crear nuevo visitante");
            System.out.println("2. Listar todos los visitantes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    visitorController.createVisitor();
                    break;
                case 2:
                    visitorController.listVisitors();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 3);

        scanner.close();
    }
}

