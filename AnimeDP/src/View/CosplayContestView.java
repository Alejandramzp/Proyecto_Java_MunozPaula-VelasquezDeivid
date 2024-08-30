
package View;

import Dao.CosplayContestDao;
import Model.CosplayContestModel;
import Controller.CosplayContestController;
import java.util.Scanner;

public class CosplayContestView {

    private CosplayContestDao cosplayContestDao;
    private CosplayContestController cosplayContestController;
    private Scanner scanner;

    public CosplayContestView() {
        this.cosplayContestDao = new CosplayContestDao();
        this.scanner = new Scanner(System.in);
    }

    private void addCosplayContest() {
        System.out.print("Nombre del concurso: ");
        String name = scanner.nextLine();

        System.out.print("ID de la categoría: ");
        int categoryID = scanner.nextInt();
        scanner.nextLine(); 

        if (!cosplayContestDao.isCategoryExist(categoryID)) {
            System.out.println("Error: El ID de la categoría no existe.");
            return;
        }

        CosplayContestModel contest = new CosplayContestModel(0, name, categoryID);
        boolean result = cosplayContestDao.addCosplayContest(contest); // Asumiendo que este método también existe

        if (result) {
            System.out.println("Concurso de cosplay agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el concurso de cosplay.");
        }
    }
    
    private void listAllCosplayContests() {
        cosplayContestController.listAllCosplayContests();
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println("                   Cosplay                 ");
            System.out.println("   1. Agregar concurso de cosplay          ");
            System.out.println("   2. Listar todos los concursos de cosplay");
            System.out.println("   3. Salir                                ");
            System.out.println("-------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addCosplayContest();
                    break;
                case 2:
                    listAllCosplayContests();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    }

    public static void main(String[] args) {
        CosplayContestView view = new CosplayContestView();
        view.showMenu();
    }
}








