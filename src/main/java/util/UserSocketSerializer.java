package util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dto.UserSocketDto;
import models.User;

import java.lang.reflect.Type;

public class UserSocketSerializer implements JsonSerializer<UserSocketDto> {
    @Override
    public JsonElement serialize(UserSocketDto src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject userJson = new JsonObject();

        User user = src.getUser();

        userJson.addProperty("id", user.getId());
        userJson.addProperty("name", user.getName());
        userJson.addProperty("imagePath", user.getImagePath());
        //userJson.addProperty(user.g);


        JsonObject dtoJson = new JsonObject();

        dtoJson.addProperty("id", src.getId());
        dtoJson.addProperty("name", src.getName());
        dtoJson.addProperty("isInFestival", src.isInFestival());
        dtoJson.addProperty("message", src.getMessage());
        dtoJson.add("user", userJson);




        return dtoJson;
    }
}
