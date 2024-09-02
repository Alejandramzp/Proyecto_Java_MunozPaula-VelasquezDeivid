package AnimeDP;

import Controller.CosplayParticipantController;
import Controller.TicketOfficeController;
import Controller.TriviaParticipantController;
import Dao.CosplayParticipantDao;
import Dao.TriviaParticipantDao;
import View.*;

import java.util.Scanner;

public class Main {
    private Scanner scanner;

    public Main() {
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'        Menú Principal del Sistema      '");
            System.out.println("' 1. Gestión de Eventos                  '");
            System.out.println("' 2. Gestión de Actividades              '");
            System.out.println("' 5. Gestión de Taquillas                '");
            System.out.println("' 6. Gestión de Comercios                '");
            System.out.println("' 7. Salir                              '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    eventManagement();
                    break;
                case 2:
                    activityManagement();
                    break;
                case 3:
                    staffManagement();
                    break;
                case 4:
                    storeInventoryManagement();
                    break;
                case 5:
                    TicketOfficeView ticketOfficeView = new TicketOfficeView(new TicketOfficeController());
                    ticketOfficeView.showMenu();
                    break;
                case 6:
                    commerceManagement();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (option != 7);
    }

    private void eventManagement() {
        EventView eventView = new EventView();
        int option;
        do {
            System.out.println("----------------------------------------");
            System.out.println("'       Gestión de Eventos             '");
            System.out.println("' 1. Gestion de eventos                '");
            System.out.println("' 2. Gestión de Personal               '");
            System.out.println("' 3. Gestión de Inventario             '");
            System.out.println("' 4. Gestión de Taquillas              '");
            System.out.println("' 5. Salir                             '");
            System.out.println("----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    eventView.displayMenu();
                    break;
                case 2:
                    staffManagement();
                    break;
                case 3:
                    storeInventoryManagement();
                    break;
                case 4:
                    TicketOfficeView ticketOfficeView = new TicketOfficeView(new TicketOfficeController());
                    ticketOfficeView.showMenu();
                    break;
                case 5:
                    return; // Salir de la gestión de eventos
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (option != 5);
    }

    private void activityManagement() {
        ActivityView activityView = new ActivityView();
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'       Gestión de Actividades           '");
            System.out.println("' 1. Crear nueva actividad              '");
            System.out.println("' 2. Gestionar concursos de cosplay     '");
            System.out.println("' 3. Gestionar concursos de trivia      '");
            System.out.println("' 4. Salir                             '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    activityView.displayMenu();
                    break;
                case 2:
                    CosplayContestView consplayContest = new CosplayContestView();
                    CosplayParticipantDao dao = new CosplayParticipantDao();
                    CosplayParticipantController controller = new CosplayParticipantController(dao);
                    CosplayParticipantView cosplayParticipant = new CosplayParticipantView(controller);
                    int option1;
                    do{
                        System.out.println("-----------------------------------------");
                        System.out.println("'      Gestión de Concurso Cosplay      '");
                        System.out.println("' 1. Crear Concurso Consplay            '");
                        System.out.println("' 2. Gestionar Concurso Cosplay         '");
                        System.out.println("' 3. Salir                             '");
                        System.out.println("-----------------------------------------");
                        System.out.print("Seleccione una opción: ");
                        option1 = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch(option1){
                            case 1:
                                consplayContest.showMenu();
                                break;
                            case 2:
                                cosplayParticipant.showMenu();
                                break;
                            case 3:
                                System.out.println("Saliendo.....");
                                return;
                        }
                    }while (option1 != 3);
                case 3:
                    triviaManagement();
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (option != 4);
    }

    private void ticketOfficeManagement() {
        TicketOfficeView ticketOfficeView = new TicketOfficeView(new TicketOfficeController());
        int option;
        do {
            System.out.println("-------------------------------------------");
            System.out.println("'       Gestión de Taquillas              '");
            System.out.println("' 1. Crear nuevo visitante                '");
            System.out.println("' 2. Gestionar tickets                    '");
            System.out.println("' 3. Gestionar participantes de actividad '");
            System.out.println("' 4. Salir                                '");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    VisitorView visitorView = new VisitorView();
                    visitorView.showVisitorMenu();
                    break;
                case 2:
                    TicketView ticketView = new TicketView();
                    ticketView.showTicketMenu();
                    break;
                case 3:
                    ActivityParticipationView activityParticipantView = new ActivityParticipationView();
                    activityParticipantView.showParticipationMenu();
                    break;
                case 4:
                    return; // Salir de la gestión de taquillas
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (option != 4);
    }

    private void commerceManagement() {
        BusinessView commerceView = new BusinessView();
        int option;
        do {
            System.out.println("-----------------------------------------");
            System.out.println("'       Gestión de Comercios              '");
            System.out.println("' 1. Gestión de Inventario               '");
            System.out.println("' 2. Gestión de Menús de Restaurantes    '");
            System.out.println("' 3. Gestión de Pedidos                 '");
            System.out.println("' 4. Caja Registradora                  '");
            System.out.println("' 5. Salir                             '");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    inventoryManagement();
                    break;
                case 2:
                    restaurantMenuManagement();
                    break;
                case 3:
                    orderManagement();
                    break;
                case 4:
                    cashierManagement();
                    break;
                case 5:
                    return; // Salir de la gestión de comercios
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (option != 5);
    }

    private void inventoryManagement() {
        StoreInventoryView inventoryView = new StoreInventoryView();
        inventoryView.displayMenu();
    }

    private void restaurantMenuManagement() {
        RestaurantMenuView restaurantMenuView = new RestaurantMenuView();
        restaurantMenuView.displayMenu();
    }

    private void orderManagement() {
        OrderView orderView = new OrderView();
        orderView.displayMenu();
    }

    private void cashierManagement() {
        CashRegisterView cashierView = new CashRegisterView();
        cashierView.displayMenu();
    }

    private void staffManagement() {
        StaffView staffView = new StaffView();
        staffView.displayMenu();
    }

    private void storeInventoryManagement() {
        StoreInventoryView inventoryView = new StoreInventoryView();
        inventoryView.displayMenu();
    }

    private void triviaManagement() {
        TriviaView triviaView = new TriviaView(new TriviaParticipantController(new TriviaParticipantDao()));
        triviaView.start();
    }

    public static void main(String[] args) {
        Main menu = new Main();
        menu.showMenu();
    }
}

