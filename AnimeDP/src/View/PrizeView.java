/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.PrizeController;
import java.util.Scanner;

public class PrizeView {

    private PrizeController prizeController;
    private Scanner scanner;

    public PrizeView() {
        this.prizeController = new PrizeController();
        this.scanner = new Scanner(System.in);
    }

    // Método principal para mostrar el menú y gestionar la interacción del usuario
    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("=== Menú de Gestión de Premios ===");
            System.out.println("1. Agregar Premio");
            System.out.println("2. Entregar Premio");
            System.out.println("3. Eliminar Premio");
            System.out.println("4. Listar Premios por Estado");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea restante

            switch (choice) {
                case 1:
                    prizeController.addPrize();
                    break;
                case 2:
                    prizeController.deliverPrize();
                    break;
                case 3:
                    prizeController.removePrize();
                    break;
                case 4:
                    prizeController.listPrizesByStatus();
                    break;
                case 5:
                    running = false;
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Clase Main para ejecutar el programa
    public static void main(String[] args) {
        PrizeView prizeView = new PrizeView();
        prizeView.showMenu();
    }
}



