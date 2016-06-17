package bean;

import entity.Edge;
import entity.State;

/**
 * Created by jiajie on 2016/6/17.
 */
public class NewRoad extends Edge{
    private NewNode start;
    private NewNode end;
    private float length;

    public NewRoad(NewNode start, NewNode end, float length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public void setStart(NewNode start) {
        this.start = start;
    }

    public void setEnd(NewNode end) {
        this.end = end;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public State getStart() {
        return start;
    }

    @Override
    public State getEnd() {
        return end;
    }

    @Override
    public float cost() {
        return length;
    }

    @Override
    public float oldCost() {
        return length;
    }
}
