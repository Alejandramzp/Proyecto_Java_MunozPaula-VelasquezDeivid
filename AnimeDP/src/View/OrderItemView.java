
package View;

import Controller.OrderItemController;
import Model.OrderItemModel;

import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    private OrderItemController orderItemController;
    private Scanner scanner;

    public OrderItemView() {
        this.orderItemController = new OrderItemController();
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'      Gestión de Ítems del Pedido:     '");
            System.out.println("'                                       '");
            System.out.println("'    1. Añadir ítem al pedido           '");
            System.out.println("'    2. Consultar ítems de un pedido    '");
            System.out.println("'    3. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addOrderItem();
                case 2 -> showOrderItems();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 3);
    }
    
    public void addOrderItem() {
        System.out.println("\n-----------------------------------------");
        System.out.println("         Añadir un ítem al pedido:         ");
        System.out.println("-------------------------------------------");

        int orderID;
        
        // Validar el ID del pedido
        while (true) {
            try {
                System.out.println("ID del pedido al que pertenece el item a añadir: ");
                orderID = scanner.nextInt();
                scanner.nextLine();
                
                if(orderItemController.orderExists(orderID)){
                    break; // Si no hay excepción, salir del bucle
                }else{
                    System.out.println("El pedido no se encuentra registrado");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un ID de pedido diferente.");
            }
        }

        System.out.print("Nombre del ítem: ");
        String itemName = scanner.next();

        System.out.print("Cantidad: ");
        int quantity = scanner.nextInt();

        System.out.print("Valor individual: ");
        double individualValue = scanner.nextDouble();

        OrderItemModel orderItem = new OrderItemModel(orderID, itemName, quantity, individualValue);

        if (orderItemController.addOrderItem(orderItem)) {
            System.out.println("Ítem añadido al pedido exitosamente.");
        } else {
            System.out.println("Error al añadir el ítem al pedido: El pedido con ID " + orderID + " no existe.");
        }
    }

    public void showOrderItems() {
        System.out.print("Ingrese el ID del pedido para consultar sus detalles: ");
        int orderID = scanner.nextInt();

        List<OrderItemModel> items = orderItemController.getOrderItemsByOrderId(orderID);
        if (items.isEmpty()) {
            System.out.println("No se encontraron ítems para el pedido con ID: " + orderID);
        } else {
            System.out.println("\n----------------------------------------------------------");
            System.out.println("Detalles del pedido con ID " + orderID + ":");
            for (OrderItemModel item : items) {
                System.out.println("-> Ítem: " + item.getItemName() +
                                   " | Cantidad: " + item.getQuantity() +
                                   " | Valor Individual: $" + item.getIndividualValue());
            }
        }
        System.out.println("----------------------------------------------------------");
    }

    public static void main(String[] args) {
        OrderItemView orderItemView = new OrderItemView();
        orderItemView.displayMenu();
    }
}

