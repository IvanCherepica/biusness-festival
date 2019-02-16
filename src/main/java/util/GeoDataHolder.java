package util;

import dao.SessionFactoryHolder;
import org.hibernate.SessionFactory;

public class GeoDataHolder {

    private double latitude;

    private double longitude;

    private String userGeoposition;

    //private int userNotificationCount;

    private long currentFestivalId;



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

    public String getUserGeoposition() {
        return userGeoposition;
    }

    public void setUserGeoposition(String userGeoposition) {
        this.userGeoposition = userGeoposition;
    }


//    public int getUserNotificationCount() {
//        return userNotificationCount;
//    }
//
//    public void setUserCurrentFestivalId(int userNotificationCount) {
//        this.userNotificationCount = userNotificationCount;
//    }


    public long getCurrentFestivalId() {
        return currentFestivalId;
    }

    public void setCurrentFestivalId(long currentFestivalId) {
        this.currentFestivalId = currentFestivalId;
    }
}
