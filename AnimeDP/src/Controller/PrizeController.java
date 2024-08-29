/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.PrizeDao;
import java.util.Scanner;

public class PrizeController {

    private PrizeDao prizeDao;
    private Scanner scanner;

    public PrizeController() {
        this.prizeDao = new PrizeDao();
        this.scanner = new Scanner(System.in);
    }

    // Método para agregar un nuevo premio
    public void addPrize() {
        System.out.println("Ingrese el tipo del premio:");
        String type = scanner.nextLine();
        
        System.out.println("Ingrese la descripción del premio:");
        String description = scanner.nextLine();
        
        System.out.println("Ingrese el valor del premio:");
        double value = scanner.nextDouble();
        
        System.out.println("Ingrese el ID de la actividad:");
        int activityID = scanner.nextInt();
        
        System.out.println("Ingrese el ID del negocio:");
        int businessID = scanner.nextInt();
        
        scanner.nextLine(); // Consumir el salto de línea restante
        
        boolean success = prizeDao.addPrize(type, description, value, activityID, businessID);
        if (success) {
            System.out.println("Premio agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el premio.");
        }
    }

    // Método para entregar un premio
    public void deliverPrize() {
        System.out.println("Ingrese el ID del premio:");
        int prizeID = scanner.nextInt();
        
        System.out.println("Ingrese el ID del visitante:");
        int visitorID = scanner.nextInt();
        
        scanner.nextLine(); // Consumir el salto de línea restante
        
        boolean success = prizeDao.deliverPrize(prizeID, visitorID);
        if (success) {
            System.out.println("Premio entregado exitosamente.");
        } else {
            System.out.println("Error al entregar el premio.");
        }
    }

    // Método para eliminar un premio
    public void removePrize() {
        System.out.println("Ingrese el ID del premio a eliminar:");
        int prizeID = scanner.nextInt();
        
        scanner.nextLine(); // Consumir el salto de línea restante
        
        boolean success = prizeDao.removePrize(prizeID);
        if (success) {
            System.out.println("Premio eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el premio.");
        }
    }

    // Método para listar premios por estado
    public void listPrizesByStatus() {
        System.out.println("Ingrese el estado de los premios a listar:");
        String status = scanner.nextLine();
        
        prizeDao.listPrizesByStatus(status);
    }
}


