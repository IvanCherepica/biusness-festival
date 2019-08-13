package dto;

import models.Event;
import models.EventPoint;
import models.Festival;

import java.util.List;
import java.util.Map;

public class FestivalDataDto {
    private Festival festival;
    //private Map<Long, Event> eventMap;
    private List<EventPoint> eventPointList;

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

//    public Map<Long, Event> getEventMap() {
//        return eventMap;
//    }
//
//    public void setEventMap(Map<Long, Event> eventMap) {
//        this.eventMap = eventMap;
//    }

    public List<EventPoint> getEventPointList() {
        return eventPointList;
    }

    public void setPointList(List<EventPoint> eventList) {
        this.eventPointList = eventList;
    }
}
