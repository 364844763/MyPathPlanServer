import Hierarchy.Vcs;
import Hierarchy.NodeToEdge;
import java.io.File;
import java.time.chrono.IsoChronology;

/**
 * Created by jiajie on 15/11/16.
 */
public class Test {
    public static void main(String[] args){
        String start="1584698";
        String end="1376388";
//        String start="47288043";
//        String end="909810";
     start = "11951442";
        long time = System.currentTimeMillis();
//        hierarch();
        PathPlannerFactory.create(start, end, false, 1);


    }

    private static void  hierarch(){
        Vcs vcs = new Vcs();
        vcs.doLouvain(new File("D:\\work\\H1Nodes.txt"),new File("D:\\work\\H1Edges.txt"));
        NodeToEdge nodeToEdge = new NodeToEdge();
        nodeToEdge.writeEdge();
    }

}
