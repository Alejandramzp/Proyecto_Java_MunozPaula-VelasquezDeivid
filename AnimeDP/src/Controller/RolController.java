
package Controller;

import Dao.RolDao;
import java.util.Scanner;

public class RolController {
    private RolDao rolDao;
    private Scanner scanner;

    public RolController() {
        this.rolDao = new RolDao();
        this.scanner = new Scanner(System.in);
    }

    private int getValidActivityID(String prompt) {
        while (true) {
            System.out.print(prompt);
            int activityID = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            if (rolDao.isActivityRoleIDExists(activityID)) {
                return activityID;
            } else {
                System.out.println("ID de actividad no válido. Por favor, intente nuevamente.");
            }
        }
    }

    public void addRole(String roleName, int activity1ID, int activity2ID) {
        rolDao.addRole(roleName, activity1ID, activity2ID);
        
    }
    
    public void viewAllRoles() {
        rolDao.viewAllRoles();
    }


}

