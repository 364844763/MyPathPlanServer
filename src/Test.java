import bean.Path;
import pathplan.PathFinding;
import pathplan.RoadRead;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        RoadRead roadRead = new RoadRead();
        try {
            roadRead.readNodes("D:\\path.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Path> results =null;
        /*长路*/
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
        System.out.println(l);
//       ACO aco = new ACO(start,end,(HashMap)paths,roadRead.getNodes());
//        ArrayList<String> route1 = aco.constructor();
//        for (String s :route1)
//            System.out.println(s);
    }
}
