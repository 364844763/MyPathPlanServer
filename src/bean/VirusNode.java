package bean;

import java.util.ArrayList;

/**
 * ²¡¶¾½Úµã
 * Created by jiajie on 2016/6/16.
 */
public class VirusNode {
    private String id;
    private ArrayList<String> roads;
    private double distance;
    public double fx;
    public double gx;
    public double hx;
    private String parent;
    private Location location;
    private double longitude;
    private double latitude;
    private int time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoads(ArrayList<String> roads) {
        this.roads = roads;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public ArrayList<String> getRoads() {
        return roads;
    }

    public void setRoads(String road) {
        if (roads==null){
            roads=new ArrayList<String>();
        }
        roads.add(road);
    }



    public double getFx() {
        return fx;
    }

    public void setFx(double fx) {
        this.fx = fx;
    }

    public double getGx() {
        return gx;
    }

    public void setGx(double gx) {
        this.gx= gx;
    }

    public double getHx() {
        return hx;
    }

    public void setHx(double hx) {
        this.hx = hx;
    }
}
