
package View;

import Controller.DiscountPromotionController;
import Dao.OrderItemDao;
import Model.DiscountPromotionModel;
import Model.OrderItemModel;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DiscountPromotionView {
    private DiscountPromotionController discountPromotionController;
    private Scanner scanner;

    public DiscountPromotionView() {
        this.discountPromotionController = new DiscountPromotionController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("'            Gestión de Descuentos:          '");
            System.out.println("'                                            '");
            System.out.println("'    1. Añadir Descuento                     '");
            System.out.println("'    2. Mostrar todos los Descuentos         '");
            System.out.println("'    3. Mostrar Descuento por ID             '");
            System.out.println("'    4. Actualizar Descuento por ID          '");
            System.out.println("'    5. Eliminar Descuento por ID            '");
            System.out.println("'    6. Aplicar Descuento a Ítems del pedido '");
            System.out.println("'    7. Salir                                '");
            System.out.println("----------------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addDiscountPromotion();
                case 2 -> showAllDiscountPromotions();
                case 3 -> showDiscountPromotionById();
                case 4 -> updateDiscountPromotion();
                case 5 -> deleteDiscountPromotion();
                case 6 -> applyDiscountToOrder();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 7);
    }

    private void addDiscountPromotion() {
        System.out.println("\n-----------------------------------------");
        System.out.println("           Añadir Nuevo Descuento       ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Inventario: ");
        int inventoryID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Descripción: ");
        String description = scanner.nextLine();

                System.out.print("Tipo (combo, 2x1, mejor precio por cantidad, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Valor del Descuento: ");
        double discountValue = scanner.nextDouble();
        scanner.nextLine(); // Consume la nueva línea

        DiscountPromotionModel discountPromotion = new DiscountPromotionModel(0, inventoryID, description, type, discountValue);

        if (discountPromotionController.addDiscountPromotion(discountPromotion)) {
            System.out.println("Descuento añadido con éxito.");
        } else {
            System.out.println("Error al añadir el descuento.");
        }
    }

    private void showAllDiscountPromotions() {
        System.out.println("\n-----------------------------------------");
        System.out.println("        Mostrar Todos los Descuentos     ");
        System.out.println("-----------------------------------------");

        List<DiscountPromotionModel> discountPromotions = discountPromotionController.getAllDiscountPromotions();
        if (discountPromotions.isEmpty()) {
            System.out.println("No hay descuentos disponibles.");
        } else {
            discountPromotions.forEach(dp -> {
                System.out.println("ID: " + dp.getDiscountID());
                System.out.println("ID Inventario: " + dp.getInventoryID());
                System.out.println("Descripción: " + dp.getDescription());
                System.out.println("Tipo: " + dp.getType());
                System.out.println("Valor del Descuento: " + dp.getDiscountValue());
                System.out.println("-----------------------------------------");
            });
        }
    }

    private void showDiscountPromotionById() {
        System.out.println("\n-----------------------------------------");
        System.out.println("       Mostrar Descuento por ID          ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Descuento: ");
        int discountID = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea

        DiscountPromotionModel discountPromotion = discountPromotionController.getDiscountPromotionById(discountID);
        if (discountPromotion != null) {
            System.out.println("ID: " + discountPromotion.getDiscountID());
            System.out.println("ID Inventario: " + discountPromotion.getInventoryID());
            System.out.println("Descripción: " + discountPromotion.getDescription());
            System.out.println("Tipo: " + discountPromotion.getType());
            System.out.println("Valor del Descuento: " + discountPromotion.getDiscountValue());
        } else {
            System.out.println("Descuento no encontrado.");
        }
    }

    private void updateDiscountPromotion() {
        System.out.println("\n-----------------------------------------");
        System.out.println("       Actualizar Descuento por ID       ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Descuento: ");
        int discountID = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea

        System.out.print("ID del Inventario: ");
        int inventoryID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        System.out.print("Tipo (combo, 2x1, mejor precio por cantidad, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Valor del Descuento: ");
        double discountValue = scanner.nextDouble();
        scanner.nextLine(); // Consume la nueva línea

        DiscountPromotionModel discountPromotion = new DiscountPromotionModel(discountID, inventoryID, description, type, discountValue);

        if (discountPromotionController.updateDiscountPromotion(discountPromotion)) {
            System.out.println("Descuento actualizado con éxito.");
        } else {
            System.out.println("Error al actualizar el descuento.");
        }
    }

    private void deleteDiscountPromotion() {
        System.out.println("\n-----------------------------------------");
        System.out.println("       Eliminar Descuento por ID         ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Descuento: ");
        int discountID = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea

        if (discountPromotionController.deleteDiscountPromotion(discountID)) {
            System.out.println("Descuento eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el descuento.");
        }
    }
    
    private void applyDiscountToOrder() {
        System.out.println("\n-----------------------------------------");
        System.out.println("   Aplicar Descuento a Ítems del Pedido    ");
        System.out.println("-------------------------------------------");

        System.out.print("ID del Pedido: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea
        
        OrderItemDao orderItemDao = new OrderItemDao();

        List<OrderItemModel> orderItems = orderItemDao.getItemsByOrderId(orderId);
        if (orderItems.isEmpty()) {
            System.out.println("No hay ítems para este pedido.");
            return;
        }

        System.out.println("Ítems del pedido:");
        orderItems.forEach(item -> {
            System.out.println("ID: " + item.getOrderItemID() + " - " + item.getItemName() + " - Cantidad: " + item.getQuantity() + " - Valor: " + item.getIndividualValue());
        });

        System.out.print("Ingrese los IDs de los ítems a los cuales aplicar el descuento (separados por comas): ");
        String[] itemIdsStr = scanner.nextLine().split(",");
        List<Integer> itemIds = Arrays.stream(itemIdsStr).map(Integer::parseInt).collect(Collectors.toList());

        List<DiscountPromotionModel> discounts = discountPromotionController.getAllDiscountPromotions(); // Obtener todos los descuentos disponibles

        double totalDiscountedValue = discountPromotionController.applyDiscountToOrderItems(orderId, itemIds, discounts);

        System.out.println("El valor total con el descuento aplicado es: " + totalDiscountedValue);
    }

  
    public static void main(String[] args) {
        DiscountPromotionView discountPromotion = new DiscountPromotionView();
        discountPromotion.displayMenu();
    }
}

