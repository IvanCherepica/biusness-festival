package util;

import com.google.gson.*;
import dto.FestivalDataDto;
import models.Event;
import models.EventPoint;
import services.implementation.EventServiceImpl;

import java.lang.reflect.Type;
import java.util.List;

public class FestivalDataToMapSerealializer implements JsonSerializer<FestivalDataDto> {
    private EventServiceImpl eventService = EventServiceImpl.getInstance();

    @Override
    public JsonElement serialize(FestivalDataDto src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject festivalDataJson = new JsonObject();

        JsonObject festivalJson = new JsonObject();
        festivalJson.addProperty("id",src.getFestival().getId());
        festivalJson.addProperty("name",src.getFestival().getName());
        festivalJson.addProperty("description",src.getFestival().getDescription());

        festivalDataJson.add("festival",festivalJson);

        JsonArray jsonEventPointsList = new JsonArray();

        for (EventPoint currentEventPoint : src.getEventPointList()) {

            JsonArray jsonEventsList = new JsonArray();

            Long currentEventPointID = currentEventPoint.getId();

            List<Event> eventList = eventService.getAllByEventPoint(currentEventPointID);

            for (Event currentEvent : eventList) {
                JsonObject currentJson = new JsonObject();

                currentJson.addProperty("id", currentEvent.getId());
                currentJson.addProperty("name", currentEvent.getName());
                currentJson.addProperty("description", currentEvent.getDescription());

                jsonEventsList.add(currentJson);
            }

            jsonEventPointsList.add(jsonEventsList);
        }
        festivalDataJson.add("eventPointList",jsonEventPointsList);
        return festivalDataJson;
    }
}
