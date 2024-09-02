/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.TriviaController;
import Controller.TriviaParticipantController;
import Dao.TriviaQuestionDao;
import Dao.TriviaParticipantDao;
import Model.TriviaQuestioModel;

import java.sql.SQLException;
import java.util.Scanner;

public class TriviaView {
    private TriviaParticipantController triviaParticipantController;
    private TriviaQuestionDao triviaQuestionDao;
    private TriviaController triviaController;
    private TriviaQuestionView triviaQuestionView;
    private TriviaParticipantView TriviaParticipantView;
    private Scanner scanner;

    public TriviaView(TriviaParticipantController triviaParticipantController) {
        this.triviaParticipantController = triviaParticipantController;
        this.triviaQuestionDao = new TriviaQuestionDao();
        this.triviaController = new TriviaController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    iniciarConcurso();
                    break;
                case 2:
                    TriviaQuestionView question = new TriviaQuestionView();
                    question.showMenu();
                    break;
                case 3:
                    TriviaParticipantView participant = new TriviaParticipantView(triviaParticipantController);
                    participant.showMenu();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void mostrarMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("'      Gestión de Concurso Trivia:         '");
        System.out.println("'                                          '");
        System.out.println("'    1. Iniciar Concurso                   '");
        System.out.println("'    2. Gestion de preguntas               '");
        System.out.println("'    3. Gestion participantes              '");
        System.out.println("'    4. Salir                              '");
        System.out.println("--------------------------------------------");
        System.out.print("Seleccione una opción: ");
    }

    private void iniciarConcurso() {
        try {
            System.out.print("Ingrese el ID del concurso: ");
            int contestID = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            triviaController.iniciarConcurso(contestID); // Este método debería manejar excepciones adecuadamente
        } catch (SQLException e) {
            System.out.println("Error al iniciar el concurso: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: no hay suficientes datos para iniciar el concurso. " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        TriviaParticipantDao triviaParticipantDao = new TriviaParticipantDao();
        TriviaParticipantController triviaParticipantController = new TriviaParticipantController(triviaParticipantDao);
        TriviaView triviaView = new TriviaView(triviaParticipantController);
        triviaView.start();
    }
}


