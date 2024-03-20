package controller;

import entity.Config;
import entity.CourseEvent;
import entity.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.ViewAndController;
import model.ViewModes;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.predicate.PeriodRule;
import net.fortuna.ical4j.model.*;
import service.*;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private Pane calendarPane;
    @FXML
    private GridPane calendarGridPane;
    private Calendar calendar = null;
    private List<Event> events;
    private ViewModes viewMode = ViewModes.WEEKLY;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Config config = ConfigRetriever.retrieve();
        if(config == null) {
            return;
        }
        try {
            this.calendar = CalendarRetriever.retrieve(new URL(config.getCalendarUrl()));

            this.events = CalendarFilterer.getCurrentWeekEvents(this.calendar);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        this.viewMode = ViewModes.WEEKLY;
        try {
            this.displayEvents();
        } catch (IOException e) {
            System.err.println(e.getCause());
            System.err.println(e.getMessage());
        }
    }

    private void displayEvents() throws IOException {
        for (Event event : events) {
            int dayOfWeek = event.getStart().getDay();
            if(dayOfWeek == 0) {
                dayOfWeek = 7;
            }
            dayOfWeek -= 1;

            int startHour = event.getStart().getHours();
            // Only display events within calendar range
            if(startHour < 8 || startHour > 20) {
                continue;
            }

            ViewAndController viewAndController = ViewLoader.getViewAndController("eventComponent");
            EventComponentController eventComponentController = (EventComponentController) viewAndController.controller;
            eventComponentController.setType(((CourseEvent) event).getCourseType().toString());
            eventComponentController.setSubject(((CourseEvent) event).getName());
            eventComponentController.setRoom(event.getLocation());

            int yStartCoordinates = (startHour - 8)*2+1+(event.getStart().getMinutes()/30);
            int yEndCoordinates = (event.getEnd().getHours() - 8)*2+1+(event.getEnd().getMinutes()/30);
            calendarGridPane.add(viewAndController.node, dayOfWeek, yStartCoordinates, 1, yEndCoordinates-yStartCoordinates);
        }
    }
}
