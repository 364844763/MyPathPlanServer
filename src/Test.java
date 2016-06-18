import MapReader.FuzhoumapReader;
import bean.FuZhouMap;
import bean.NewRoad;
import bean.Relation;
import entity.Plan;
import entity.State;
import pathplan.VirusAStarPlanner;
import pathplan.LSSLRTAStarPlanner;
import pathplan.MLPAStarPlanner;
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
    /*    RoadRead roadRead = new RoadRead();
        try {
            roadRead.readNodes("D:\\path.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Path> results =null;

//        String start="47288043";
//        String end="909810";
        String start="1584698";
        String end="1376388";
        long time = System.currentTimeMillis();
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
        System.out.println(l);
        System.out.println(System.currentTimeMillis()-time);*/
//       ACO aco = new ACO(start,end,(HashMap)paths,roadRead.getNodes());
//        ArrayList<String> route1 = aco.constructor();
//        for (String s :route1)
//            System.out.println(s);
        long time = System.currentTimeMillis();
        FuzhoumapReader reader = new FuzhoumapReader("D:\\path.csv");
        reader.read();
        HashMap<String, ArrayList<State>> successors = reader.getSuccessors();
        HashMap<String, ArrayList<State>> predecessors = reader.getPredecessors();
        HashMap<Relation, NewRoad> roads = reader.getRoads();
        time = System.currentTimeMillis();
        HashMap<String,State> nodes = reader.getMap();
        FuZhouMap map = new FuZhouMap(successors,predecessors,roads);
        VirusAStarPlanner planner = new VirusAStarPlanner(ManhattanDistanceHeuristics.instance());
        String start="1584698";
        String end="1376388";
        Plan plan = planner.findPath(map,nodes.get(start),nodes.get(end));

        System.out.println(plan);
        System.out.println(plan.getCost());
        System.out.println(System.currentTimeMillis()-time);
        time = System.currentTimeMillis();
        LSSLRTAStarPlanner planner2 = new LSSLRTAStarPlanner(ManhattanDistanceHeuristics.instance(),1);
        plan = planner2.findPath(map,nodes.get(start),nodes.get(end));
        Plan plan2 = planner2.findPath(map,nodes.get(start),nodes.get(end),10000);
        MLPAStarPlanner planner1 = new MLPAStarPlanner(ManhattanDistanceHeuristics.instance());
        System.out.println(plan);
        System.out.println(plan.getCost());
        System.out.println(plan2);
        System.out.println(plan2.getCost());
        System.out.println(System.currentTimeMillis()-time);
        time = System.currentTimeMillis();
        Plan plan1 = planner1.findPath(map,nodes.get(start),nodes.get(end));
        System.out.println(plan1);
        System.out.println(System.currentTimeMillis()-time);
        time = System.currentTimeMillis();
        planner1.resetSearch();
        plan1 = planner1.findPath(map,nodes.get(start),nodes.get(end));
        System.out.println(plan1);
        System.out.println(System.currentTimeMillis()-time);

    }
}
