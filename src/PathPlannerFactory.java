import MapReader.FuzhoumapReader;
import MapReader.Mapreader;
import MapReader.RoadRead;
import bean.*;
import entity.Plan;
import entity.State;
import pathplan.*;
import pathplan.aco.ACO;
import pathplan.heuristics.ManhattanDistanceHeuristics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiajie on 2016/6/20.
 */
public class PathPlannerFactory {
    private PathPlannerFactory() {
    }
    public static void create (String start, String end,boolean is,int i){
        if (is){
            creat(start,end);
        }else {
            create(i,start,end);
        }
    }
    private static void create(int i, String start, String end){
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

//                System.out.println(plan.getCost());
//                System.out.println(plan.getLength());
                break;
            case 2:
                planner = new LPAStarPlanner(ManhattanDistanceHeuristics.instance(),10000);
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 3:
                planner = new MLPAStarPlanner(ManhattanDistanceHeuristics.instance());
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 4:
                planner = new AMOPlanner(ManhattanDistanceHeuristics.instance(),10000);
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;
            case 5:
                planner = new HyperstarPlanner(ManhattanDistanceHeuristics.instance());
                plan = planner.findPath(map,nodes.get(start),nodes.get(end));
                break;

        }
        System.out.println(plan);
    }
    private static void creat(String start, String end){
        HashMap<String, bean.Road> map;
        RoadRead  roadRead=new RoadRead();
        Map<String,bean.Path> paths;
        try {
            map= Mapreader.reader("D:\\zhedian_output.csv");
            roadRead.readNodes("D:\\path.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<bean.Path> results =null;
        paths=roadRead.getPaths();
        PathFinding pathFinding=new PathFinding(start,end,roadRead.getNodes(),(HashMap)paths);
        results=pathFinding.pathFinder();
        System.out.println("[START");
        int i = 0;
        for (bean.Path path:results){
            i++;
            System.out.print("Node{id='"+path.getId()+"},");
            if (i%5==0){
                System.out.println("Node{id='"+path.getId()+"},");
            }else {
                System.out.print("Node{id='"+path.getId()+"},");
            }
        }
        System.out.println("GOAL]");
//        RoadRead roadRead = new RoadRead();
//        try {
//            roadRead.readNodes("D:\\path.csv");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HashMap<String, Path> paths = roadRead.getPaths();
//        ACO aco = new ACO(start,end,(HashMap)paths,roadRead.getNodes());
//        ArrayList<String> route1 = aco.constructor();
//        for (String s :route1)
//            System.out.println(s);
    }

}
