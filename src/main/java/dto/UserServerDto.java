package dto;

/**
 * Используется парсинга сообщения от клиента, полученного через WebSocket
 */
public class UserServerDto {
    private String coordinates;

    //private boolean isInFestival;

//    private String userName;
//
    private Long id;

    public UserServerDto(String coordinates, String userName, Long id) {
        this.coordinates = coordinates;
//        this.userName = userName;
//        this.id = id;
    }

    public UserServerDto() {

    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
