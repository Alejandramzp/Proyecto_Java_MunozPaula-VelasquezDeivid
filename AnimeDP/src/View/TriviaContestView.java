
package View;

import Dao.TriviaContestDao;
import Model.TriviaContestModel;
import Controller.TriviaContestController;
import java.util.Scanner;

public class TriviaContestView {
    
    private TriviaContestDao triviaContestDao;
    private TriviaContestController triviaContestController;
    private Scanner scanner;
    
    public TriviaContestView(){
        this.triviaContestDao = new TriviaContestDao();
        this.scanner = new Scanner(System.in);
    }
    
    private void addTriviaContest(){
        System.out.print("Nombre del Concurso: ");
        String name = scanner.nextLine();
        
        System.out.print("ID de la categoría: ");
        int categoryID = scanner.nextInt();
        scanner.nextLine();
        
        if(!triviaContestDao.isCategoryExist(categoryID)){
            System.out.println("Error: El ID de la categoría no existe.");
            return;
        }
        
        TriviaContestModel contest = new TriviaContestModel(0, name, categoryID);
        boolean result = triviaContestDao.addTriviaContest(contest);
        
        if(result) {
            System.out.println("Concurso de Trivia agregado exitosamente");
        } else {
            System.out.println("Error al agregar el concurso de trivia.");
        }
    }
    
    private void listAllTriviaContest(){
        triviaContestController.listAllTriviaContests();
    }
    
    private void showMenu(){
        
        while (true){
            System.out.println("--------------------------------------------");
            System.out.println("'      Gestión de Concurso Trivia:         '");
            System.out.println("'                                          '");
            System.out.println("'    1. Añadir concurso de trivia          '");
            System.out.println("'    2. Listar todos loc concursos trivia  '");
            System.out.println("'    3. Iniciar concurso de trivia         '");
            System.out.println("'    4. Salir                              '");
            System.out.println("--------------------------------------------");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice){
                case 1:
                    addTriviaContest();
                    break;
                case 2:
                    listAllTriviaContest();
                    break;
                case 3:
                    
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
            
        }
    }
    
    public static void main(String[] args) {
        TriviaContestView view = new TriviaContestView();
        view.showMenu();
    }
}

