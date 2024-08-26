/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

// folder import
import Model.EventModel;
import Dao.EventDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EventController {

    private EventDao eventDao;

    public EventController() {
        this.eventDao = new EventDao();
    }

    public boolean addEvent(EventModel event) {
        if (isEventNameExists(event.getName())) {
            System.out.println("Error: El nombre ya está en uso.");
            return false;
        }
        
        // Validaciones de fecha
        if (!isValidDate(event.getDate())) {
            System.out.println("Error: Fecha inválida.");
            return false;
        }

        eventDao.InsertEvent(event);
        return true;
    }

    public boolean isEventNameExists(String name) {
        List<EventModel> events = eventDao.ViewEvent();
        for (EventModel event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate = LocalDate.parse(date, formatter);
        LocalDate currentDate = LocalDate.now();
        return !eventDate.isBefore(currentDate);
    }

    public EventModel getEventById(int id) {
        return eventDao.ViewEventID(id);
    }

    public List<EventModel> getAllEvents() {
        return eventDao.ViewEvent();
    }
}

