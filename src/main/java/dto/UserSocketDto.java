package dto;

import models.*;

import java.util.List;

/**
 * Используется для отправки ответного сообщения пользователю через WebSocket
 */
public class UserSocketDto {

    private long id;

    private String name;

    private long fesivalId;

    private boolean isInFestival;

    private String message = "";

    private Festival festival;

    private EventPoint eventPoint;

    private User user;

//    private List<Event> eventList;

    private List<EventPoint> eventPointList;

    private String eventPointsGson;


    public UserSocketDto(long id, String name, boolean isInFestival) {
        this.id = id;
        this.name = name;
        this.isInFestival = isInFestival;
    }

    public UserSocketDto() {
    }

    public long getFesivalId() {
        return fesivalId;
    }

    public void setFesivalId(long fesivalId) {
        this.fesivalId = fesivalId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInFestival() {
        return isInFestival;
    }

    public void setInFestival(boolean inFestival) {
        isInFestival = inFestival;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<EventPoint> getEventPointList() {

        return eventPointList;
    }

    public void setEventPointList(List<EventPoint> eventPointList) {
        this.eventPointList = eventPointList;
    }

    public String getEventPointsGson() {
        return eventPointsGson;
    }

    public void setEventPointsGson(String eventPointsGson) {
        this.eventPointsGson = eventPointsGson;
    }

    public EventPoint getEventPoint() {
        return eventPoint;
    }

    public void setEventPoint(EventPoint eventPoint) {
        this.eventPoint = eventPoint;
    }


}
