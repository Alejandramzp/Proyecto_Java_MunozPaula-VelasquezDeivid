
package Controller;

import Dao.EventAccountingDao;

public class EventAccountingController {
    private EventAccountingDao accountingDao;

    public EventAccountingController() {
        this.accountingDao = new EventAccountingDao();
    }
    
    public void generateAvailableTicketsReport(int eventID) {
        int maxCapacity = accountingDao.getMaxPersonCapacity(eventID);
        if (maxCapacity > 0) {
            int availableTickets = accountingDao.getAvailableTickets(eventID, maxCapacity);
            System.out.println("\n----------------------------------------------------------------------------");
            System.out.println(" Boletos disponibles para el evento con ID " + eventID + ": " + availableTickets);
            System.out.println("----------------------------------------------------------------------------");
        } else {
            System.out.println("Error: No se pudo obtener el aforo máximo para el evento con ID " + eventID);
        }
    }
    
    // Generar informe de boletos vendidos
    public void generateTotalTicketsSoldReport() {
        int totalTickets = accountingDao.getTotalTicketsSold();
        System.out.println("\n-----------------------------------------");
        System.out.println("  Total de boletos vendidos: " + totalTickets);
        System.out.println("------------------------------------------");
    }

    // Generar informe de monto total recaudado por categoría
    public void generateTotalAmountByCategoryReport(int categoryID) {
        if (accountingDao.categoryExists(categoryID)) {
            double totalAmount = accountingDao.getTotalAmountByCategory(categoryID);
            System.out.println("\n------------------------------------------------------------------------");
            System.out.println("  Monto total recaudado por la categoría " + categoryID + ": $" + totalAmount);
            System.out.println("--------------------------------------------------------------------------");
        } else {
            System.out.println("Error: La categoría con ID " + categoryID + " no existe. Por favor, ingrese un ID válido.");
        }
    }

    // Generar informe de boletos disponibles para un evento
    public void generateAvailableTicketsReport(int eventID, int maxCapacity) {
        int availableTickets = accountingDao.getAvailableTickets(eventID, maxCapacity);
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("  Boletos disponibles para el evento " + eventID + ": " + availableTickets);
        System.out.println("-------------------------------------------------------------------------");
    }

    // Generar informe de participantes por actividad
    public void generateParticipantsByActivityReport(int activityID) {
        int participants = accountingDao.getParticipantsByActivity(activityID);
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Participantes en la actividad " + activityID + ": " + participants);
        System.out.println("-----------------------------------------------------------------");
    }
}


