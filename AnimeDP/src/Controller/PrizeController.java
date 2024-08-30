
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
        System.out.print("Ingrese el tipo del premio: ");
        String type = scanner.nextLine();
        
        System.out.print("Ingrese la descripción del premio: ");
        String description = scanner.nextLine();
        
        System.out.print("Ingrese el valor del premio: ");
        double value = scanner.nextDouble();
        
        System.out.print("Ingrese el ID de la actividad: ");
        int activityID = scanner.nextInt();
        
        System.out.print("Ingrese el ID del negocio: ");
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
        System.out.print("Ingrese el ID del premio:");
        int prizeID = scanner.nextInt();
        
        System.out.print("Ingrese el ID del visitante:");
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
        System.out.print("Ingrese el ID del premio a eliminar:");
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
        System.out.print("Ingrese el estado de los premios a listar:");
        String status = scanner.nextLine();
        
        prizeDao.listPrizesByStatus(status);
    }
}


