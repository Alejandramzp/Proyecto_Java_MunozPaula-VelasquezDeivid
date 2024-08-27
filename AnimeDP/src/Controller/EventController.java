/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.EventDao;
import Model.EventModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventController {
    private EventDao eventDao;

    public EventController() {
        this.eventDao = new EventDao();
    }

    public boolean addEvent(EventModel event) {
        return eventDao.addEvent(event);
    }

    public List<EventModel> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public boolean isEventNameExists(String name) {
        return eventDao.isEventNameExists(name);
    }

    // Método para actualizar el estado del evento basado en la fecha actual
    public void updateEventStatus() {
        List<EventModel> events = getAllEvents();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (EventModel event : events) {
            LocalDate eventDate = LocalDate.parse(event.getDate(), formatter);

            if (currentDate.equals(eventDate)) {
                event.setStatus("Activo");
            } else if (currentDate.isAfter(eventDate)) {
                event.setStatus("Finalizado");
            }
            eventDao.updateEventStatus(event);
        }
    }
}
