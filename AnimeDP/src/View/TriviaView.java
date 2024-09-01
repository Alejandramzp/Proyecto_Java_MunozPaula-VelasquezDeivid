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
                    registrarPregunta();
                    break;
                case 3:
                    registrarParticipante();
                    break;
                case 4:
                    mostrarResultados();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void mostrarMenu() {
        System.out.println("\n*** Menú del Concurso de Trivia ***");
        System.out.println("1. Iniciar Concurso");
        System.out.println("2. Registrar Pregunta");
        System.out.println("3. Registrar Participante");
        System.out.println("4. Mostrar Resultados");
        System.out.println("5. Salir");
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

    private void registrarPregunta() {
        System.out.println("Escribe la pregunta: ");
        String question = scanner.nextLine();

        System.out.println("Escribe la respuesta de la pregunta: ");
        String correctAnswer = scanner.nextLine();

        System.out.println("Nombre de la categoría: ");
        String category = scanner.nextLine();

        System.out.println("Ingresa la dificultad de la pregunta (Fácil, Intermedio, Difícil): ");
        String difficulty = scanner.nextLine();

        TriviaQuestioModel questionModel = new TriviaQuestioModel(0, question, correctAnswer, category, difficulty);
        boolean result = triviaQuestionDao.addTriviaQuestion(questionModel);

        if (result) {
            System.out.println("Pregunta ingresada exitosamente.");
        } else {
            System.out.println("Error al agregar la pregunta trivia.");
        }
    }

    private void registrarParticipante() {
        System.out.println("ID del participante: ");
        int participantID = scanner.nextInt();
        scanner.nextLine();

        double score = 0;

        System.out.println("ID del concurso de Trivia: ");
        int triviaContestID = scanner.nextInt();
        scanner.nextLine();

        boolean result = triviaParticipantController.addTriviaParticipant(participantID, score, triviaContestID);

        if (result) {
            System.out.println("Participante de Trivia agregado exitosamente.");
        } else {
            System.out.println("Error al agregar al participante de trivia.");
        }
    }

    private void mostrarResultados() {
        triviaController.mostrarResultados();
    }

    public static void main(String[] args) {
        TriviaParticipantDao triviaParticipantDao = new TriviaParticipantDao();
        TriviaParticipantController triviaParticipantController = new TriviaParticipantController(triviaParticipantDao);
        TriviaView triviaView = new TriviaView(triviaParticipantController);
        triviaView.start();
    }
}


