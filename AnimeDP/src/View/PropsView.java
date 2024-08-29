
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
            System.out.println("-----------------------------------------");
            System.out.println("'         Gestión de Utilería:          '");
            System.out.println("'                                       '");
            System.out.println("'    1. Añadir Utilería                 '");
            System.out.println("'    2. Mostrar todas las Utilerías     '");
            System.out.println("'    3. Mostrar todas las Utilerías     '");
            System.out.println("'    4. Actualizar Estado de Utilería   '");
            System.out.println("'    5. Actualizar Cantidad de Utilería '");
            System.out.println("'    6. Eliminar Utilería               '");
            System.out.println("'    7. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addProps();
                case 2 -> showAllProps();
                case 3 -> showPropsById();
                case 4 -> updatePropsStatus();
                case 5 -> updatePropsQuantity();
                case 6 -> System.out.println("eliminar");
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 7);
    }
    
    private void addProps() {
        System.out.println("\n-----------------------------------------");
        System.out.println("           Añadir nueva utilería           ");
        System.out.println("-------------------------------------------");
        
        int eventId;
        
        // Validar el ID del evento
        while (true) {
            try {
                System.out.println("Id del evento al que pertenece la utilería a crear: ");
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
        
        System.out.println("\n-----------------------------------------");
        System.out.println("     Ingrese el estado de la utilería:     ");
        System.out.println("                                           ");
        System.out.println("    1. En el sitio                         ");
        System.out.println("    2. En el almacen                       ");
        System.out.print("      Seleccione una opción:                 ");
        System.out.println("-------------------------------------------");
        
        int optionS;
        String status = "";
        
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
        System.out.println("\n----------------------------------------");
        System.out.println("     Listado de toda la Utilería:          ");
        System.out.println("------------------------------------------");
        propsController.getAllProps().forEach(prop -> System.out.println(
                
                "-> ID: " + prop.getId() +
                ", Nombre: " + prop.getName() +
                ", Cantidad: " + prop.getQuantity() +
                ", Estado: " + prop.getStatus() +
                ", EnventoID: " + prop.getEventId()+ "\n"));                
    }
    
    private void showPropsById(){
        System.out.println("\n----------------------------------------");

        int propsId;

        // Validar el ID de la utilería
        while (true) {
            try {
                System.out.println("Id de la utilería a mostrar: ");
                propsId = scanner.nextInt();
                scanner.nextLine();
                
                PropsModel props = propsController.getPropsById(propsId);
                
                if(props != null){
                    System.out.println("Detalles de la utilería:");
                    System.out.println("ID: " + props.getId());
                    System.out.println("Nombre: " + props.getName());
                    System.out.println("Cantidad: " + props.getQuantity());
                    System.out.println("Estado: " + props.getStatus());
                    System.out.println("ID del Evento: " + props.getEventId());
                    break; // Si no hay excepción, salir del bucle
                }else{
                    System.out.println("El evento no se encuentra registrado");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un ID de utilería diferente.");
            }
        }
        System.out.println("------------------------------------------");
    }
      
    private void updatePropsStatus() {
        try {
            propsController.updatePropsStatus();
        } catch (Exception e) {
            System.out.println("Se produjo un error al intentar actualizar el estado de la utilería: " + e.getMessage());
        }
    }
    
    private void updatePropsQuantity() {
        try {
            propsController.updatePropsQuantity();
        } catch (Exception e) {
            System.out.println("Se produjo un error al intentar actualizar el estado de la utilería: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        PropsView view = new PropsView();
        view.displayMenu();
    }
}
