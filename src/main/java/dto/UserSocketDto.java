package dto;

import models.Festival;
import models.User;

/**
 * Используется для отправки ответного сообщения пользователю через WebSocket
 */
public class UserSocketDto {

    private long id;

    private String name;

    private boolean isInFestival;

    private String message = "";

    private Festival festival;

    private User user;

    public UserSocketDto(long id, String name, boolean isInFestival) {
        this.id = id;
        this.name = name;
        this.isInFestival = isInFestival;
    }

    public UserSocketDto() {
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
}
