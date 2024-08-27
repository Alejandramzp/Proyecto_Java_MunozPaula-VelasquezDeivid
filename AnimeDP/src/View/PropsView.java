
package View;

import Controller.PropsController;
import java.util.Scanner;


public class PropsView {
    private PropsController propsController;
    private Scanner scanner;

    public PropsView() {
        this.propsController = new PropsController();
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        
        int option;
        do {
            System.out.println("\nGestión de Utilería:");
            System.out.println("1. Añadir Utilería");
            System.out.println("2. Mostrar todas las Utilerías");
            System.out.println("3. Actualizar Estado de Utilería");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> System.out.println("Añadir");
                case 2 -> System.out.println("Mostar");
                case 3 -> System.out.println("Actualizar");
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 4);
    }
    
    private void addProps() {
        System.out.println("\nAñadir nueva utilería:");
        
        
    }
}
