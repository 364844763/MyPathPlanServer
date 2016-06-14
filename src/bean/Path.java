package bean;

/**
 * Created by Administrator on 2015/8/5.
 */
public class Path {
    private String id;
    private double length;
    private String Start;
    private String end;
    private int nextDirection;//0-直行，1-左行，2-右行
    private int level;
    private int[] flow;
    public int getNextDirection() {
        return nextDirection;
    }

    public int[] getFlow() {
        return flow;
    }
    public int getFlow(int i){
        return flow[i];
    }
    public void setFlow(int[] flow) {
        this.flow = flow;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNextDirection(int nextDirection) {
        this.nextDirection = nextDirection;
    }

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
                ", nextDirection=" + nextDirection +
                '}';
    }
    public void setFlow(String[] strings){
        flow = new int[61];
        flow[0] = 1;
        for(int i=0;i<strings.length;i++){
            flow[i+1] = Integer.parseInt(strings[i]);
        }
    }
}
