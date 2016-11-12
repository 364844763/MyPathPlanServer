package Hierarchy;

import java.awt.*;
import java.util.List;

/**
 * Created by wang on 2016/5/5.
 */
public class Edge {
    String start;
    String end;
    int distance;
    int level;
    int[] flow;



    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) { this.level = level; }

    public int getFlow(int i){
        return flow[i];
    }
    public void setFlow(String[] strings){
        flow = new int[61];
        flow[0] = 1;
        for(int i=0;i<strings.length;i++){
            flow[i+1] = Integer.parseInt(strings[i]);
        }
    }

}
