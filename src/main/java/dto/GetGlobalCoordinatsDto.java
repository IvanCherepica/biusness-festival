package dto;

public class GetGlobalCoordinatsDto {

    //x - latitude, y - longitude

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


}