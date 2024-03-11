package controller;

import entity.Config;
import javafx.fxml.Initializable;
import net.fortuna.ical4j.model.Calendar;
import service.CalendarRetriever;
import service.ConfigRetriever;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private Calendar calendar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Config config = ConfigRetriever.retrieve();
        try {
            this.calendar = CalendarRetriever.retrieve(new URL(config.getCalendarUrl()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
