
package Controller;

import Dao.ActivityRoleDao;
import java.util.Scanner;

public class ActivityRoleController {
    private ActivityRoleDao activityRoleDao;
    private Scanner scanner;

    public ActivityRoleController() {
        this.activityRoleDao = new ActivityRoleDao();
        this.scanner = new Scanner(System.in);
    }

    public void addActivityRole(String activityName) {
        System.out.print("Ingrese el nombre de la actividad: ");

        if (activityRoleDao.isActivityNameExists(activityName)) {
            System.out.println("La actividad ya existe.");
        } else {
            boolean success = activityRoleDao.addActivityRole(activityName);
            if (success) {
                System.out.println("Actividad registrada exitosamente.");
            } else {
                System.out.println("Error al registrar la actividad.");
            }
        }
    }

    public void viewActivityRoles() {
        System.out.println("\n-----------------------------------------");
        System.out.println("  Visualizando todas las actividades:");
        System.out.println("-----------------------------------------");
        activityRoleDao.viewActivityRoles();
    }
}
