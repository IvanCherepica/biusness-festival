package dto;

public class GetGlobalCoordinatsDto {

    //x - latitude, y - longitude


    private long userCurrentFestivalId;
    private double latitudeX;
    private double longitudeY;


    public double getLatitudeX() {
        return latitudeX;
    }

    public void setLatitudeX(double latitudeX) {
        this.latitudeX = latitudeX;
    }


    public double getLongitudeY() {
        return longitudeY;
    }

    public void setLongitudeY(double longitudeY) {
        this.longitudeY = longitudeY;
    }

    public long getUserCurrentFestivalId() {
        return userCurrentFestivalId;
    }

    public void setUserCurrentFestivalId(long festivalId) {
        this.userCurrentFestivalId = festivalId;
    }

}