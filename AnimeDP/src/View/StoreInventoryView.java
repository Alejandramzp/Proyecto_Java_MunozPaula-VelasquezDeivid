
package View;

import Controller.StoreInventoryController;
import Model.StoreInventory;
import java.util.List;
import java.util.Scanner;

public class StoreInventoryView {
    private StoreInventoryController inventoryController;
    private Scanner scanner;

    public StoreInventoryView() {
        this.inventoryController = new StoreInventoryController();
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        
        int option;
        do {
            System.out.println("----------------------------------------------");
            System.out.println("'        Gestión de Inventario:              '");
            System.out.println("'                                            '");
            System.out.println("'  1. Añadir producto al inventario          '");
            System.out.println("'  2. Actualizar inventario después de venta '");
            System.out.println("'  3. Mostrar productos por comercio         '");
            System.out.println("'  4. Salir                                  '");
            System.out.println("----------------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addProductToInventory();
                case 2 -> updateInventoryAfterSale();
                case 3 -> displayProductsByBusinessID();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 4);
    }
    
    public void addProductToInventory() {
        System.out.print("Ingrese el ID de la tienda: ");
        int businessID = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nombre del producto: ");
        String productName = scanner.nextLine();
        System.out.print("Ingrese la descripción del producto: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese el fabricante: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Ingrese el tipo de producto (figura, póster, etc.): ");
        String type = scanner.nextLine();
        System.out.print("Ingrese la cantidad disponible: ");
        int availableQuantity = scanner.nextInt();
        System.out.print("Ingrese el precio individual: ");
        double individualPrice = scanner.nextDouble();

        StoreInventory product = new StoreInventory(0, businessID, productName, description, manufacturer, type, availableQuantity, individualPrice);
        if (inventoryController.addProductToInventory(product)) {
            System.out.println("Producto añadido exitosamente.");
        } else {
            System.out.println("No se pudo añadir el producto. Verifique que el comercio sea de tipo 'Tienda' y que el ID del comercio exista.");
        }
    }

    public void updateInventoryAfterSale() {
        System.out.print("Ingrese el ID del inventario: ");
        int inventoryID = scanner.nextInt();
        System.out.print("Ingrese la cantidad vendida: ");
        int quantitySold = scanner.nextInt();

        if (inventoryController.updateInventoryAfterSale(inventoryID, quantitySold)) {
            System.out.println("Inventario actualizado después de la venta.");
        } else {
            System.out.println("Error al actualizar el inventario.");
        }
    }

    public void displayProductsByBusinessID() {
        System.out.print("Ingrese el ID del comercio: ");
        int businessID = scanner.nextInt();
        List<StoreInventory> products = inventoryController.getProductsByBusinessID(businessID);
        
        if (products.isEmpty()) {
            System.out.println("No hay inventario disponible para este comercio.");
        } else {
            System.out.println("\n----------------------------------------------");
            System.out.println("           Inventario de productos:           ");
            System.out.println("----------------------------------------------");
            for (StoreInventory product : products) {
                System.out.println("-> ID: " + product.getInventoryID() + ", Nombre: " + product.getProductName() +
                                   ", Cantidad Disponible: " + product.getAvailableQuantity() + ", Precio: $" + product.getIndividualPrice());
            }
            System.out.println("----------------------------------------------");
        }
    }

    public static void main(String[] args) {
        StoreInventoryView inventoryView = new StoreInventoryView();
        inventoryView.displayMenu();
    }
}

