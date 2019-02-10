package dto;

public class UserSocketDto {

    private long id;

    private String name;

    private boolean isInFestival;

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
}
