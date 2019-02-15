package util;

import com.google.gson.*;
import dto.UserServerDto;
import java.lang.reflect.Type;

public class UserJSONDataDeserializer implements JsonDeserializer<UserServerDto> {
    @Override
    public UserServerDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        UserServerDto userServerDto = new UserServerDto();
        userServerDto.setCoordinates(obj.get("coordinates").getAsString());
//        userServerDto.setUserName(obj.get("userName").getAsString());
        userServerDto.setId(obj.get("userID").getAsLong());

        return userServerDto;
    }
}
