
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
            System.out.println("'    1. Crear pedido                    '");
            System.out.println("'    2. Avanzar el estado del pedido    '");
            System.out.println("'    3. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> createOrder();
                case 2 -> advanceOrderStatus();
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 3);
    }

    public void createOrder() {
        System.out.println("Crear un nuevo pedido:");

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

    public void advanceOrderStatus() {
        System.out.print("Ingrese el ID del pedido a avanzar: ");
        int orderID = scanner.nextInt();

        if (orderController.advanceOrderStatus(orderID)) {
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
