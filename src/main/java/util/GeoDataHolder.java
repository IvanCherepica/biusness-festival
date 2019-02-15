package util;

import dao.SessionFactoryHolder;
import org.hibernate.SessionFactory;

public class GeoDataHolder {

    double latitude;

    double longitude;

    private static volatile GeoDataHolder instance;

    private GeoDataHolder() {

    }

    public static GeoDataHolder getGeoDataHolder() {
        if (instance == null) {
            synchronized (SessionFactoryHolder.class) {
                if (instance == null) {
                    instance = new GeoDataHolder();
                }
            }
        }
        return instance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
