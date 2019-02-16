package util;

import com.google.gson.*;
import dto.FestivalDataDto;
import dto.UserScheduleDto;
import models.Event;

import java.lang.reflect.Type;

public class UserScheduleDtoSerealializer implements JsonSerializer<UserScheduleDto> {
    @Override
    public JsonElement serialize(UserScheduleDto src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject festivalDataJson = new JsonObject(); //итоговый результат

        JsonArray jsonEventsList = new JsonArray();

        for (Event currentEvent : src.getEventsList()) {
            JsonObject currentJson = new JsonObject();

            currentJson.addProperty("id",currentEvent.getId());
            currentJson.addProperty("name",currentEvent.getName());
            currentJson.addProperty("description",currentEvent.getDescription());

            jsonEventsList.add(currentJson);
        }

        festivalDataJson.add("eventsList", jsonEventsList);

        return festivalDataJson;
    }
}
