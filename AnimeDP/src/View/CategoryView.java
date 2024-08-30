
package View;

import Controller.CategoryController;
import Model.CategoryModel;

import java.util.List;
import java.util.Scanner;

public class CategoryView {

    private CategoryController categoryController;
    private Scanner scanner;

    public CategoryView() {
        this.categoryController = new CategoryController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println("'                 Categoría               '");
            System.out.println("'         1. Agregar Categoría            '");
            System.out.println("'         2. Eliminar Categoría           '");
            System.out.println("'         3. Listar Categorías            '");
            System.out.println("'         4. Salir                        '");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    deleteCategory();
                    break;
                case 3:
                    listCategories();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void addCategory() {
        System.out.print("Ingrese el nombre de la categoría: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la edad: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el género (Hombre/Mujer/Otros): ");
        String gender = scanner.nextLine();

        boolean success = categoryController.addCategory(name, age, gender);
        if (success) {
            System.out.println("Categoría agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la categoría.");
        }
    }

    private void deleteCategory() {
        System.out.print("Ingrese el ID de la categoría a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        boolean success = categoryController.deleteCategory(id);
        if (success) {
            System.out.println("Categoría eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la categoría.");
        }
    }

    private void listCategories() {
        List<CategoryModel> categories = categoryController.listCategories();
        if (categories.isEmpty()) {
            System.out.println("No hay categorías disponibles.");
        } else {
            for (CategoryModel category : categories) {
                System.out.println(category);
            }
        }
    }
}