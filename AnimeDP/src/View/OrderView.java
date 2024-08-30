
package View;

import Controller.OrderController;
import Model.OrderModel;
import java.util.Scanner;

public class OrderView {
    private OrderController orderController;
    private Scanner scanner;

    public OrderView() {
        this.orderController = new OrderController();
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'         Gestión de Pedidos:           '");
            System.out.println("'                                       '");
            System.out.println("'    1. Añadir pedido                   '");
            System.out.println("'    2. Mostrar pedido por ID           '");
            System.out.println("'    3. Avanzar el estado del pedido    '");
            System.out.println("'    4. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addOrder();
                case 2 -> showOrderById();
                case 3 -> advanceOrderStatus();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 4);
    }

    public void addOrder() {
        System.out.println("\n-----------------------------------------");
        System.out.println("          Añadir un nuevo pedido:          ");
        System.out.println("-------------------------------------------");
        
        System.out.print("ID del visitante: ");
        int visitorID = scanner.nextInt();

        System.out.print("ID del comercio: ");
        int businessID = scanner.nextInt();

        System.out.print("ID de la caja: ");
        int cashRegisterID = scanner.nextInt();

        System.out.print("Valor total: ");
        double totalValue = scanner.nextDouble();

        OrderModel order = new OrderModel(visitorID, businessID, cashRegisterID, totalValue);

        if (orderController.createOrder(order)) {
            System.out.println("Pedido creado exitosamente con ID: " + order.getOrderID());
        } else {
            System.out.println("Error al crear el pedido.");
        }
    }
    
    private void showOrderById(){
        int orderId;

        // Validar el ID del pedido
        while (true) {
            try {
                System.out.print("Id del pedido a mostrar: ");
                orderId = scanner.nextInt();
                scanner.nextLine();
                
                OrderModel order = orderController.getOrderById(orderId);
                
                if(order != null){
                    System.out.println("\n----------------------------------------");
                    System.out.println("   Detalles del pedido:     ");
                    System.out.println("ID: " + order.getOrderID());
                    System.out.println("Visitante: " + order.getVisitorID());
                    System.out.println("Comercio: " + order.getBusinessID());
                    System.out.println("Caja: " + order.getCashRegisterID());
                    System.out.println("Valor total: " + order.getTotalValue());
                    System.out.println("Estado: " + order.getStatus());
                    break; // Si no hay excepción, salir del bucle
                }else{
                    System.out.println("El pedido no se encuentra registrado");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, introduzca un ID de pedido diferente.");
            }
        }
        System.out.println("------------------------------------------");
    }

    public void advanceOrderStatus() {
        System.out.print("Ingrese el ID del pedido a avanzar: ");
        int orderID = scanner.nextInt();

        if (orderController.advanceOrderStatus(orderID)) {
            OrderModel order = orderController.getOrderById(orderID);
            System.out.println("\n-------------------------------------------");
            System.out.println(" Nuevo estado del pedido: " + order.getStatus());
            System.out.println("-------------------------------------------");
            System.out.println("Estado del pedido actualizado.");
        } else {
            System.out.println("Error al actualizar el estado del pedido.");
        }
    }

    public static void main(String[] args) {
        OrderView orderView = new OrderView();
        orderView.displayMenu();
    }
}
