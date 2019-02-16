package dto;

import models.Event;

import java.util.List;

public class UserScheduleDto {
    private List<Event> eventsList;

    public List<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }
}
