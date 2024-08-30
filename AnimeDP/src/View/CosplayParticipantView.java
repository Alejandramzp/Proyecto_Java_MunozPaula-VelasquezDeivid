
package View;

import Dao.CosplayParticipantDao;
import Controller.CosplayParticipantController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CosplayParticipantView {
    private CosplayParticipantController cosplayParticipantController;
    private Scanner scanner;

    public CosplayParticipantView(CosplayParticipantController cosplayParticipantController) {
        this.cosplayParticipantController = cosplayParticipantController;
        this.scanner = new Scanner(System.in);
    }

    public void addCosplayParticipant() {
        System.out.print("ID del participante: ");
        int participantID = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nombre del Cosplay: ");
        String name = scanner.nextLine();

        System.out.print("ID del concurso de cosplay: ");
        int cosplayContestID = scanner.nextInt();

        double[] scores = new double[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Puntuación del juez " + (i + 1) + " (1-10): ");
            scores[i] = scanner.nextDouble();
        }

        boolean result = cosplayParticipantController.addCosplayParticipant(participantID, name, scores, cosplayContestID);

        if (result) {
            System.out.println("Participante de cosplay agregado exitosamente.");
        } else {
            System.out.println("Error al agregar al participante de cosplay.");
        }
    }

    public void showTopParticipants() {
        System.out.print("ID del concurso de cosplay para mostrar los mejores participantes: ");
        int cosplayContestID = scanner.nextInt();

        try (ResultSet rs = cosplayParticipantController.getTopParticipants(cosplayContestID)) {
            if (rs != null) {
                System.out.println("El primer lugar es:");
                while (rs.next()) {
                    System.out.println(" -> Nombre: " + rs.getString("Name") +
                            ", Puntaje: " + rs.getDouble("Score"));
                }
            } else {
                System.out.println("Error al recuperar los mejores participantes.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("-------------------------------------------");
            System.out.println("'     Menú de Participantes de Cosplay    '");
            System.out.println("'                                         '");
            System.out.println("'  1. Agregar participante de cosplay     '");
            System.out.println("'  2. Mostrar el 1 primer lugar           '");
            System.out.println("'  3. Salir                               '");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addCosplayParticipant();
                    break;
                case 2:
                    showTopParticipants();
                    break;
                case 3:
                    System.out.println("Saliendo del menú de participantes.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
    
    public static void main(String[] args) {
        CosplayParticipantDao dao = new CosplayParticipantDao();
        
        CosplayParticipantController controller = new CosplayParticipantController(dao);
        
        CosplayParticipantView view = new CosplayParticipantView(controller);
        
        view.showMenu();
    }
}

