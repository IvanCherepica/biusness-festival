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

        JsonObject festivalDataJson = new JsonObject(); //итоговый результат

        //JsonObject festivalJson = new JsonObject();
        festivalDataJson.addProperty("id",src.getFestival().getId());
        festivalDataJson.addProperty("name",src.getFestival().getName());
        festivalDataJson.addProperty("description",src.getFestival().getDescription());
        festivalDataJson.addProperty("coordinates",src.getFestival().getCenter());


        //festivalDataJson.add("festival",festivalJson);

        JsonArray jsonEventPointsList = new JsonArray(); //список ивентпоинтов

        //forming list of eventPoint
        for (EventPoint currentEventPoint : src.getEventPointList()) {

            JsonObject eventPointJson = new JsonObject();

            eventPointJson.addProperty("id", currentEventPoint.getId());
            eventPointJson.addProperty("name",currentEventPoint.getName());
            eventPointJson.addProperty("description",currentEventPoint.getDescription());


            JsonArray jsonEventsList = new JsonArray();

            Long currentEventPointID = currentEventPoint.getId();

            List<Event> eventList = eventService.getAllByEventPoint(currentEventPointID);

            //add to eventpoint event list
            for (Event currentEvent : eventList) {
                JsonObject currentJson = new JsonObject();

                currentJson.addProperty("id", currentEvent.getId());
                currentJson.addProperty("name", currentEvent.getName());
                currentJson.addProperty("description", currentEvent.getDescription());

                jsonEventsList.add(currentJson);
            }

            eventPointJson.add("eventsList", jsonEventsList);
            jsonEventPointsList.add(eventPointJson);
        }

        //jsonEventPointsList.

        festivalDataJson.add("eventPointsList", jsonEventPointsList);




        return festivalDataJson;
    }
}