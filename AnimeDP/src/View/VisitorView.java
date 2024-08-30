
package View;

import Controller.VisitorController;

import java.util.Scanner;

public class VisitorView {

    private VisitorController visitorController;
    private Scanner scanner;

    public VisitorView() {
        this.visitorController = new VisitorController();
        this.scanner = new Scanner(System.in);
    }

    public void showVisitorMenu() {
        int option;

        do {
            System.out.println("-----------------------------------------");
            System.out.println("'     Menú de Gestión de Visitantes     '");
            System.out.println("'   1. Crear nuevo visitante            '");
            System.out.println("'   2. Listar todos los visitantes      '");
            System.out.println("'   3. Salir                            '");
            System.out.println("-----------------------------------------");
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
                    System.out.println("Saliendo del menú de visitantes...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }

        } while (option != 3);
    }

    // Método main separado para pruebas o ejecución directa
    public static void main(String[] args) {
        VisitorView visitorView = new VisitorView();
        visitorView.showVisitorMenu();
    }
}

