/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Dao.TriviaQuestionDao;
import Model.TriviaQuestioModel;
import Controller.TriviaQuestionController;
import java.util.Scanner;

public class TriviaQuestionView {
    
    private TriviaQuestionDao triviaQuestionDao;
    private TriviaQuestionController triviaQuestionController;
    private Scanner scanner;
    
    public TriviaQuestionView(){
        this.triviaQuestionDao = new TriviaQuestionDao();
        this.scanner = new Scanner(System.in);
    }
    
    public void addTriviaQuestion(){
        System.out.println("Escribe la pregunta: ");
        String Question = scanner.nextLine();
        
        System.out.println("Escribe la respuesta de la pregunta: ");
        String CorrectAnswer = scanner.nextLine();
        
        System.out.println("Nombre de la categoria: ");
        String Category = scanner.nextLine();
        
        System.out.println("Ingresa la dificulta de la pregunta(Facil, Intermedio Dificil): ");
        String Difficulty = scanner.nextLine();
        
        TriviaQuestioModel question = new TriviaQuestioModel(0, Question, CorrectAnswer, Category, Difficulty);
        boolean result = triviaQuestionDao.addTriviaQuestion(question);
        
        if(result){
            System.out.println("Pregunta Ingresada exitosamente.");
        }else {
            System.out.println("Error al agregar la pregunta trivia.");
        }
    }
    
    private void lisAllTriviaQuestion() {
        triviaQuestionController.listAllTriviaQuestion();
    }
    
    public void showMenu(){
        while(true){
            System.out.println("--------------------------------------------");
            System.out.println("'      Gestión de Concurso Trivia:         '");
            System.out.println("'                                          '");
            System.out.println("'    1. Añadir pregunta trivia             '");
            System.out.println("'    2. Listar todas las preguntas trivias '");
            System.out.println("'    3. Salir                              '");
            System.out.println("--------------------------------------------");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
                case 1:
                    addTriviaQuestion();
                    break;
                case 2:
                    lisAllTriviaQuestion();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
            
    }
    
    public static void main(String[] args) {
        TriviaQuestionView view = new TriviaQuestionView();
        view.showMenu();
    }
}
