package pathplan.VirusAStar;

import bean.Path;
import bean.VirusNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jiajie on 2016/6/16.
 */
public class VirusAStar {
    private int speed;
    private int time;
    private String start;
    private String end;
    private HashMap<String, Path> roads;
    private HashMap<String, VirusNode> maps= new HashMap<>();
    private ArrayList<VirusNode> nodes;
    private VirusNode startNode;
    private VirusNode endNode;
    public VirusAStar(int speed, int time, String start, String end, HashMap<String, Path> roads, ArrayList<VirusNode> nodes) {
        this.speed = speed;
        this.time = time;
        this.start = start;
        this.end = end;
        this.roads = roads;
        this.nodes = nodes;
        for (VirusNode node:nodes){
//            System.out.println(node.getNodeId());
            if (node.getId().equals(start)){
                startNode=node;
            }
            else if (node.getId().equals(end)){
                endNode=node;
            }
            maps.put(node.getId(),node);
        }
    }

    public List<Path> findPath(){
        List<Path> paths = new ArrayList<>();
        return paths;
    }
}
