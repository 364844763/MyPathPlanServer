import MapReader.FuzhoumapReader;
import bean.FuZhouMap;
import bean.NewRoad;
import bean.Relation;
import entity.Plan;
import entity.State;
import pathplan.*;
import pathplan.heuristics.ManhattanDistanceHeuristics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jiajie on 2016/6/20.
 */
public class PathPlannerFactory {
    private PathPlannerFactory() {
    }

    public static Plan create(int i, String start, String end){
        FuzhoumapReader reader = new FuzhoumapReader("D:\\path.csv");
        reader.read();
        HashMap<String, ArrayList<State>> successors = reader.getSuccessors();
        HashMap<String, ArrayList<State>> predecessors = reader.getPredecessors();
        HashMap<Relation, NewRoad> roads = reader.getRoads();
        HashMap<String,State> nodes = reader.getMap();
        FuZhouMap map = new FuZhouMap(successors,predecessors,roads);
        PathPlanner planner = null;
        Plan plan =null;
        switch (i){
            case 1:
                planner = new VirusAStarPlanner(ManhattanDistanceHeuristics.instance());
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 2:
                planner = new LSSLRTAStarPlanner(ManhattanDistanceHeuristics.instance(),10000);
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 3:
                planner = new MLPAStarPlanner(ManhattanDistanceHeuristics.instance());
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 4:
                planner = new ABCPlanner(ManhattanDistanceHeuristics.instance(),10000);
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 5:
                planner = new MovingTargetDStarLitePlanner(ManhattanDistanceHeuristics.instance());
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;

        }
        return plan;
    }

}
