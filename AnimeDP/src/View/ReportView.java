
package View;

import Controller.ReportController;
import Model.ReportModel;

import java.util.List;
import java.util.Scanner;

public class ReportView {
    private ReportController reportController;
    private Scanner scanner;

    public ReportView() {
        this.reportController = new ReportController();
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        
        int option;
        do {
            System.out.println("----------------------------------------------------------");
            System.out.println("'          Gestión de Reportes de Comercios:             '");
            System.out.println("'                                                        '");
            System.out.println("'  1. Generar reporte de balance general                 '");
            System.out.println("'  2. Generar reporte de balance individual por comercio '");
            System.out.println("'  3. Salir                                              '");
            System.out.println("----------------------------------------------------------");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            switch (option) {
                case 1 -> displayGeneralBalanceReport();
                case 2 -> displayIndividualBalanceReport();
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 3);
    }
    
    public void displayGeneralBalanceReport() {
        List<ReportModel> reportList = reportController.getGeneralBalanceReport();
        System.out.println("\n----------------------------------------------------------");
        System.out.println("   Balance General de Comercios (Tiendas y Restaurantes):   ");
        System.out.println("------------------------------------------------------------");
        for (ReportModel report : reportList) {
            System.out.println("-> Comercio: " + report.getBusinessName() +
                               " | Ventas Totales: $" + report.getTotalSales());
        }
        System.out.println("------------------------------------------------------------");
    }

    public void displayIndividualBalanceReport() {
        System.out.println("Ingrese el ID del comercio para el reporte individual: ");
        int businessID = scanner.nextInt();

        ReportModel report = reportController.getIndividualBalanceReport(businessID);
        if (report != null) {
            System.out.println("\n-----------------------------------------");
            System.out.println("          Balance del Comercio:            ");
            System.out.println("-------------------------------------------");
            System.out.println("Comercio: " + report.getBusinessName() +
                               " | Ventas Totales: $" + report.getTotalSales());
        } else {
            System.out.println("No se encontró información para el comercio con ID: " + businessID);
        }
    }

    public static void main(String[] args) {
        ReportView reportView = new ReportView();
        reportView.displayMenu();
    }
}
