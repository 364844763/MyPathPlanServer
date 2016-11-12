package Hierarchy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by wang on 2016/5/5.
 */
public class Node {
    private String nodeId;
    private double x;
    private double y;
    private ArrayList<String> relation = new ArrayList<>();
    private String parentCommunity;
    public double fx;
    public double gx;
    public double hx;
    public String parent;

    public String getNodeId() { return nodeId; }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ArrayList<String> getRelation() {
        return relation;
    }

    public void addRelation(String i) {
        relation.add(i);
    }

    public void removeRelation(String i){ relation.remove(i);}

    public String getParentCommunity(){return parentCommunity;}

    public void setParentCommunity(String parentCommunity){this.parentCommunity = parentCommunity;}

    public double getDistance(Node node)
    {

       return Math.sqrt(Math.pow(node.x -x,2)+Math.pow(node.y -y,2) );
    }
}
