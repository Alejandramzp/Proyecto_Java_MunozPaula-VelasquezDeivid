/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.CategoryController;
import Model.CategoryModel;

import java.util.List;
import java.util.Scanner;

public class CategoryView {
    private CategoryController categoryController;
    private Scanner scanner;

    public CategoryView() {
        categoryController = new CategoryController();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;

        do {
            System.out.println("=== Menú de Categorías ===");
            System.out.println("1. Agregar categoría");
            System.out.println("2. Mostrar todas las categorías");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    showAllCategories();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (option != 3);
    }

    private void addCategory() {
        System.out.print("Ingresa el nombre de la categoría: ");
        String categoryName = scanner.nextLine();
        
        System.out.print("Ingresa la edad: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        System.out.print("Ingresa el género(Hombre / Mujer): ");
        String gender = scanner.nextLine();
        
        int categoryID = 0; 

        boolean isAdded = categoryController.addCategory(categoryID, categoryName, age, gender);
        if (isAdded) {
            System.out.println("Categoría agregada exitosamente.");
        } else {
            System.out.println("Hubo un error al agregar la categoría.");
        }
    }

    private void showAllCategories() {
        List<CategoryModel> categories = categoryController.getAllCategories();
        System.out.println("=== Lista de Categorías ===");
        for (CategoryModel category : categories) {
            System.out.println("ID: " + category.getCategoryID() + ", Nombre: " + category.getCategoryName() +
                    ", Edad: " + category.getAge() + ", Género: " + category.getGender());
        }
    }
    public static void main(String[] args) {
        CategoryView categoryView = new CategoryView();
        categoryView.displayMenu();
    }
}

