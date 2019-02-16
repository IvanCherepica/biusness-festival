package dto;

import models.Event;

import java.util.List;

public class EventListDto {
    private String eventPoinName;
    private String eventPoinDescription;
    private List<Event> eventList;
    private Long userId;

    public String getEventPoinName() {
        return eventPoinName;
    }

    public void setEventPoinName(String eventPoinName) {
        this.eventPoinName = eventPoinName;
    }

    public String getEventPoinDescription() {
        return eventPoinDescription;
    }

    public void setEventPoinDescription(String eventPoinDescription) {
        this.eventPoinDescription = eventPoinDescription;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
