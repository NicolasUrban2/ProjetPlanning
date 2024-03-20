package service;

import entity.CourseEvent;
import entity.Event;
import model.EventProperties;
import net.fortuna.ical4j.model.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComponentTransformer {
    public static Event componentToEvent(Component component) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

        Date dtstamp;
        try {
            dtstamp = dateFormat.parse(component.getProperty(EventProperties.DTSTAMP.name()).getValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date lastModified;
        try {
            lastModified = dateFormat.parse(component.getProperty(EventProperties.LAST_MODIFIED.name().replace('_', '-')).getValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date dtstart;
        try {
            dtstart = dateFormat.parse(component.getProperty(EventProperties.DTSTART.name()).getValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date dtend;
        try {
            dtend = dateFormat.parse(component.getProperty(EventProperties.DTEND.name()).getValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        dtstamp.setHours(dtstamp.getHours() + 1);
        lastModified.setHours(lastModified.getHours() + 1);
        dtstart.setHours(dtstart.getHours() + 1);
        dtend.setHours(dtend.getHours() + 1);

        String categories = component.getProperty(EventProperties.CATEGORIES.name()).getValue();
        String uid = component.getProperty(EventProperties.UID.name()).getValue();
        String summary = component.getProperty(EventProperties.SUMMARY.name()).getValue();
        String location = component.getProperty(EventProperties.LOCATION.name()).getValue();
        String description = component.getProperty(EventProperties.DESCRIPTION.name()).getValue();

        try {
            CourseEvent courseEvent = new CourseEvent(categories, dtstamp, lastModified, uid, dtstart, dtend, summary, location, description);
            System.out.println(courseEvent.toString());
            return courseEvent;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Event event = new Event(categories, dtstamp, lastModified, uid, dtstart, dtend, summary, location, description);
            System.out.println(event.toString());
            return event;
        }
    }
}
