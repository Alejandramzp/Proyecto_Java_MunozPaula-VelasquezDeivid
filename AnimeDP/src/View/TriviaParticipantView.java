
package View;

import Dao.TriviaParticipantDao;
import Controller.TriviaParticipantController;

import java.sql.*;
import java.util.Scanner;

public class TriviaParticipantView {
    private TriviaParticipantController triviaparticipantController;
    private Scanner scanner;
    
    public TriviaParticipantView(TriviaParticipantController triviaparticipantController){
        this.triviaparticipantController = triviaparticipantController;
        this.scanner = new Scanner(System.in);
    }
    
    public void addTriviaParticipant(){
        System.out.println("ID del participante: ");
        int participantID = scanner.nextInt();
        scanner.nextLine();
        
        double score = 0;
        
        System.out.println("ID del concurso de Trivia: ");
        int triviaContestID = scanner.nextInt();
        scanner.nextLine();
        
        boolean result = triviaparticipantController.addTriviaParticipant(participantID, score, triviaContestID);
        
        if(result){
            System.out.println("Participante de Trivia agregado exitosamente.");
        } else {
            System.out.println("Error al agregar al participante de trivia.");
        }
    }
    
    public void showTopParticipants(){
        System.out.print("ID del concurso de trivia para mostrar los mejores participantes: ");
        int triviaContestID = scanner.nextInt();
        
        try(ResultSet rs = triviaparticipantController.getTopParticipants(triviaContestID)){
            if(rs != null){
                System.out.println("El primer lugar es:");
                while (rs.next()) {
                    System.out.println(" -> Nombre: " + rs.getString("Name") +
                            ", Puntaje: " + rs.getDouble("Score"));
                }
            } else {
                System.out.println("Error al recuperar los mejores participantes.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void showMenu(){
        while (true){
            System.out.println("-------------------------------------------");
            System.out.println("'     Menú de Participantes de Trivia     '");
            System.out.println("'                                         '");
            System.out.println("'  1. Agregar participante de trivia      '");
            System.out.println("'  2. Mostrar el 1 primer lugar           '");
            System.out.println("'  3. Salir                               '");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice){
                case 1:
                    addTriviaParticipant();
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
        TriviaParticipantDao dao = new TriviaParticipantDao();
        TriviaParticipantController controller = new TriviaParticipantController(dao);
        TriviaParticipantView view = new TriviaParticipantView(controller);
        
        view.showTopParticipants();
    }
}
