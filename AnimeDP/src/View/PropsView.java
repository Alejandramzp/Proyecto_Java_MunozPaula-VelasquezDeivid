
package View;

import Controller.PropsController;
import Model.PropsModel;
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
                case 1 -> addProps();
                case 2 -> showAllProps();
                case 3 -> System.out.println("Actualizar");
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 4);
    }
    
    private void addProps() {
        System.out.println("\nAñadir nueva utilería:");
        
        int eventId;
        
        // Validar el ID del evento
        while (true) {
            try {
                System.out.print("Id del evento al que pertenece la utileria a crear: ");
                eventId = scanner.nextInt();
                scanner.nextLine();
                
                if(propsController.isEventExists(eventId)){
                    break; // Si no hay excepción, salir del bucle
                }else{
                    System.out.println("El evento no se encuentra registrado");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un ID de evento diferente.");
            }
        }
        
        System.out.println("Nombre: ");
        String name = scanner.nextLine();
        
        System.out.println("Cantidad");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Ingrese el estado de la utilería: ");
        System.out.println("1. En el sitio ");
        System.out.println("2. En el almacen");
        System.out.print("Seleccione una opción: ");
        
        int optionS;
        String status = "";
        
        OUTER:
        while (true) {
            try {
                optionS = scanner.nextInt();
                scanner.nextLine();
                if (optionS == 1){
                    status = "En sitio"; 
                    break;
                }else if(optionS == 2){
                    status = "En almacen";
                    break;
                }else{
                    System.out.println("Opción no valida. Por favor, seleccione 1 o 2.");
                }
            }catch (Exception e) {
                System.out.println("Entrada no valida. Por favor, ingrese un número entero.");
                scanner.next();
            }
        }
       
        PropsModel props = new PropsModel(0,name,quantity,status,eventId);
        
        try {
            if (propsController.addProps(props)) {
                System.out.println("Utilería añadida exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }      
    }
    
    private void showAllProps() {
        System.out.println("\nListado de toda la Utilería:");
        propsController.getAllProps().forEach(prop -> System.out.println(
                
                "ID: " + prop.getId() +
                ", Nombre: " + prop.getName() +
                ", Cantidad: " + prop.getQuantity() +
                ", Estado: " + prop.getStatus() +
                ", EnventoID: " + prop.getEventId()));
                        
    }
    
    public static void main(String[] args) {
        PropsView view = new PropsView();
        view.displayMenu();
    }
}
