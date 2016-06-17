import MapReader.FuzhoumapReader;
import bean.FuZhouMap;
import bean.NewRoad;
import bean.Relation;
import entity.Plan;
import entity.State;
import pathplan.AStarPlanner;
import pathplan.heuristics.ManhattanDistanceHeuristics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jiajie on 15/11/16.
 */
public class Test {
    public static void main(String[] args){
//        Map<Set, Buliding> bulidings;
//        bulidings= BulidReader.read("D:\\test.csv");
//        String name="公园";
//        Buildmatch buildmatch=new Buildmatch(bulidings);
//        List<Buliding> result=buildmatch.fuzzyMatch(name);
//        for (Buliding b:result){
//            System.out.println(b);
//        }
   /*     RoadRead roadRead = new RoadRead();
        try {
            roadRead.readNodes("D:\\path.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Path> results =null;
        *//*长路*//*
//        String start="47288043";
//        String end="909810";
        String start="1584698";
        String end="1376388";
        HashMap<String, Path> paths = roadRead.getPaths();
        PathFinding pathFinding=new PathFinding(start,end,roadRead.getNodes(),(HashMap)paths);
        results=pathFinding.pathFinder();
        if (results==null)
            return;
        double l = 0;
        for (Path p :results){
            l+=p.getLength();
            System.out.println(p);
        }
        System.out.println(l);*/
//       ACO aco = new ACO(start,end,(HashMap)paths,roadRead.getNodes());
//        ArrayList<String> route1 = aco.constructor();
//        for (String s :route1)
//            System.out.println(s);
        FuzhoumapReader reader = new FuzhoumapReader("D:\\path.csv");
        reader.read();
        HashMap<String, ArrayList<State>> successors = reader.getSuccessors();
        HashMap<String, ArrayList<State>> predecessors = reader.getPredecessors();
        HashMap< Relation, NewRoad > roads = reader.getRoads();
        HashMap<String,State> nodes = reader.getMap();
        FuZhouMap map = new FuZhouMap(successors,predecessors,roads);
        AStarPlanner planner = new AStarPlanner(ManhattanDistanceHeuristics.instance());
        String start="1584698";
        String end="1376388";
        Plan plan = planner.findPath(map,nodes.get(start),nodes.get(end));
        System.out.println(plan);
    }
}
