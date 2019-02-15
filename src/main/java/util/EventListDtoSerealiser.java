package util;

import com.google.gson.*;
import dto.EventListDto;
import models.Event;

import java.lang.reflect.Type;


public class EventListDtoSerealiser implements JsonSerializer<EventListDto> {
    @Override
    public JsonElement serialize(EventListDto src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject eventJson = new JsonObject();

        eventJson.addProperty("eventPoinName",src.getEventPoinName());
        eventJson.addProperty("eventPoinDescription",src.getEventPoinDescription());


        JsonArray jsonEventsList = new JsonArray();


        for (Event currentEvent : src.getEventList()) {
            JsonObject currentJson = new JsonObject();

            currentJson.addProperty("id",currentEvent.getId());
            currentJson.addProperty("name",currentEvent.getName());
            currentJson.addProperty("description",currentEvent.getDescription());

            jsonEventsList.add(currentJson);
        }

        eventJson.add("eventList",jsonEventsList);
        return eventJson;
    }
}


//    @Expose
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "festival_id")
//    private Festival festival;
//
//    @Expose
//    @Column(name = "date_begin")
//    @SerializedName("date_begin")
//    private LocalDateTime dateBegin = LocalDateTime.now();
//
//    @Expose
//    @Column(name = "date_end")
//    @SerializedName("date_end")
//    private LocalDateTime dateEnd = LocalDateTime.now();
//
//    @Expose
//    @ManyToMany(fetch= FetchType.EAGER, targetEntity = User.class)
//    @JoinTable(name ="users_on_event",
//            joinColumns = {@JoinColumn(name="events_id")},
//            inverseJoinColumns = {@JoinColumn(name="users_id")})
//    private Set<User> users;