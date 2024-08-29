/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ActivityParticipationDao;
import Model.ActivityParticipationModel;

import java.util.Scanner;

public class ActivityParticipationController {
    private ActivityParticipationDao participationDao;

    public ActivityParticipationController() {
        this.participationDao = new ActivityParticipationDao();
    }

    public void addParticipation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del visitante:");
        int visitorID = scanner.nextInt();

        System.out.println("Ingrese el ID de la actividad:");
        int activityID = scanner.nextInt();

        ActivityParticipationModel participation = new ActivityParticipationModel(0, visitorID, activityID);
        
        if (participationDao.addActivityParticipation(participation)) {
            System.out.println("Participación añadida exitosamente.");
        } else {
            System.out.println("Error al añadir la participación.");
        }
    }
}

