
package View;

import Controller.DishController;
import Controller.RestaurantMenuController;
import Model.DishModel;
import Model.RestaurantMenuModel;
import java.util.Scanner;

public class RestaurantMenuView {
    
    private RestaurantMenuController restaurantMenuController;
    private DishController dishController;
    private Scanner scanner;

    public RestaurantMenuView() {
        this.restaurantMenuController = new RestaurantMenuController();
        this.dishController = new DishController();
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'    Gestión de Menú de Restaurantes:   '");
            System.out.println("'                                       '");
            System.out.println("'    1. Añadir Plato                    '");
            System.out.println("'    2. Actualizar Plato                '");
            System.out.println("'    3. Añadir Menú de Restaurante      '");
            System.out.println("'    4. Actualizar Menú de Restaurante  '");
            System.out.println("'    5. Mostar el Menú de Restaurante   '");
            System.out.println("'    6. Salir                           '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> addDish();
                case 2 -> updateDish();
                case 3 -> addRestaurantMenu();
                case 4 -> updateRestaurantMenu();
                case 5 -> showMenuById();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 6);
    }
    
    public void addDish() {
        System.out.println("\n-----------------------------------------");
        System.out.println("           Añadir Nuevo Plato            ");
        System.out.println("-----------------------------------------");

        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        System.out.print("Tipo (Entrada, Bebida, Plato Fuerte, Aperitivo): ");
        String type = scanner.nextLine();

        System.out.print("Tiempo de Preparación (minutos): ");
        int preparationTimeMinutes = scanner.nextInt();
        scanner.nextLine();

        DishModel dish = new DishModel(0, description, type, preparationTimeMinutes);

        try {
            if (dishController.addDish(dish)) {
                System.out.println("Plato añadido exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al añadir el plato.");
            System.out.println("Error: " + e.getMessage());
        } 
    }

    public void updateDish() {
        System.out.println("\n-----------------------------------------");
        System.out.println("        Actualizar Información del Plato  ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Plato: ");
        int dishID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nueva Descripción: ");
        String description = scanner.nextLine();

        System.out.print("Nuevo Tipo (Entrada, Bebida, Plato Fuerte, Aperitivo): ");
        String type = scanner.nextLine();

        System.out.print("Nuevo Tiempo de Preparación (minutos): ");
        int preparationTimeMinutes = scanner.nextInt();
        scanner.nextLine();

        DishModel dish = new DishModel(dishID, description, type, preparationTimeMinutes);

        try {
            if (dishController.updateDish(dish)) {
                System.out.println("Plato actualizado exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar el plato.");
            System.out.println("Error: " + e.getMessage());
        } 
    }
    
    public void addRestaurantMenu() {
        System.out.println("\n-----------------------------------------");
        System.out.println("        Añadir Nuevo Menú de Restaurante  ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Comercio (debe ser Tienda): ");
        int businessID = scanner.nextInt();
        scanner.nextLine();

        // Verificar si el comercio es del tipo "Tienda"
        if (!restaurantMenuController.isStore(businessID)) {
            System.out.println("El comercio no es del tipo 'Tienda'. No se puede añadir menú.");
            return;
        }

        System.out.print("ID del Plato: ");
        int dishID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Cantidad Disponible: ");
        int availableQuantity = scanner.nextInt();
        scanner.nextLine();

        RestaurantMenuModel menu = new RestaurantMenuModel(businessID, dishID, availableQuantity);

        try {
            if (restaurantMenuController.addRestaurantMenu(menu)) {
                System.out.println("Menú añadido exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al añadir el menú.");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateRestaurantMenu() {
        System.out.println("\n-----------------------------------------");
        System.out.println("     Actualizar Información del Menú      ");
        System.out.println("-----------------------------------------");

        System.out.print("ID del Comercio (debe ser Tienda): ");
        int businessID = scanner.nextInt();
        scanner.nextLine();

        // Verificar si el comercio es del tipo "Tienda"
        if (!restaurantMenuController.isStore(businessID)) {
            System.out.println("El comercio no es del tipo 'Tienda'. No se puede actualizar menú.");
            return;
        }

        System.out.print("ID del Plato: ");
        int dishID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nueva Cantidad Disponible: ");
        int availableQuantity = scanner.nextInt();
        scanner.nextLine();

        RestaurantMenuModel menu = new RestaurantMenuModel(businessID, dishID, availableQuantity);

        try {
            if (restaurantMenuController.updateRestaurantMenu(menu)) {
                System.out.println("Menú actualizado exitosamente.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar el menú.");
            System.out.println("Error: " + e.getMessage());
        } 
    }
 
    private void showMenuById(){
        RestaurantMenuController menuController = new RestaurantMenuController();

        System.out.println("Ingrese el ID del restaurante para ver el menú:");
        int businessId = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        menuController.displayMenuWithDishes(businessId);
    }

    public static void main(String[] args) {
        RestaurantMenuView menuView = new RestaurantMenuView();
        menuView.displayMenu();
    }
}

