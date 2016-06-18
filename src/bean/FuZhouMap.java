package bean;

import entity.Edge;
import entity.SearchDomain;
import entity.State;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jiajie on 2016/6/17.
 */
public class FuZhouMap extends SearchDomain {

    private HashMap<String, ArrayList<State>> successors;
    private HashMap<String, ArrayList<State>> predecessors;
    private HashMap<Relation,NewRoad> roads;

    public FuZhouMap(HashMap<String, ArrayList<State>> successors, HashMap<String, ArrayList<State>> predecessors, HashMap<Relation, NewRoad> roads) {
        this.successors = successors;
        this.predecessors = predecessors;
        this.roads = roads;
    }

    @Override
    public <T extends State> ArrayList<State> getSuccessors(State cNode) {
        return successors.get(((NewNode) cNode).getId());
    }


    @Override
    public <T extends State> ArrayList<State> getPredecessors(State cNode) {
        return predecessors.get(((NewNode) cNode).getId());
    }

    @Override
    public <T extends Edge> ArrayList<T> getChangedEdges() {
        return null;
    }

    @Override
    public float cost(State sNode, State tNode) {
        Relation relation = new Relation(((NewNode)sNode).getId(),((NewNode)tNode).getId());
        NewRoad road = roads.get(relation);
        if (road==null)
            return Float.MAX_VALUE;
        return road.getLength();
    }

    @Override
    public float hCost(State sNode, State tNode) {
        NewNode newNode1 = (NewNode) sNode;
        NewNode newNode2 = (NewNode) tNode;
        return (float) distance(newNode1.getLongitude(), newNode1.getLatitude(), newNode2.getLongitude(), newNode2.getLatitude());
    }

    @Override
    public boolean isBlocked(State cNode) {
        return false;
    }

    private double distance(double long1, double lat1, double long2,
                            double lat2) {

        double a, b, R;
        R = 6378137; // µØÇò°ë¾¶
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }
}
