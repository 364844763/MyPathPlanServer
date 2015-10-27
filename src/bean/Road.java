package bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/5.
 */
public class Road {
    private double length;
    private String id;
    private Location start;
    private Location end;

    private ArrayList<Location> zheDian=new ArrayList<Location>();

    public ArrayList<Location> getZheDian() {
        return zheDian;
    }

    public void setZheDian(ArrayList<Location> zheDian) {
        this.zheDian = zheDian;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }
}
