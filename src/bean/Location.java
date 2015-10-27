package bean;

/**
 * Created by Administrator on 2015/8/18.
 */
public class Location {
    public double latitude;
    public  double longitude;

    public Location(double longitude, double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
