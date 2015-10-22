package pathplan;

/**
 * Created by Administrator on 2015/8/5.
 */
public class Path {
    private String id;
    private double length;
    private String Start;
    private String end;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id='" + id + '\'' +
                ", length=" + length +
                ", Start='" + Start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
