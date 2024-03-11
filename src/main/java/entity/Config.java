package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    private String calendarUrl;

    @JsonCreator
    public Config(
            @JsonProperty("calendarUrl") String calendarUrl
    ) {
        this.calendarUrl = calendarUrl;
    }

    public String getCalendarUrl() {
        return calendarUrl;
    }

    public void setCalendarUrl(String calendarUrl) {
        this.calendarUrl = calendarUrl;
    }
}
