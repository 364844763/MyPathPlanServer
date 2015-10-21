package pathplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/8/5.
 */
public class PathFinding {
    private String start;
    private String ends;
    private HashMap<String,Path> roads;
    private HashMap<String,Node> maps=new HashMap<String, Node>();
    private ArrayList<Node> nodes;
    private Node startNode;
    private Node endNode;
    public PathFinding( String start, String ends,ArrayList<Node> nodes, HashMap<String, Path> roads) {
        this.nodes = nodes;
        this.start = start;
        this.ends = ends;
        this.roads = roads;
    }

    public List<String> pathFinder(){
        List<String> paths=new ArrayList<String>();
        for (Node node:nodes){
            if (node.getNodeId().equals(start)){
                startNode=node;
            }
            else if (node.getNodeId().equals(ends)){
                endNode=node;
            }
            maps.put(node.getNodeId(),node);
        }
        for (Node node:nodes){
            double distance=Distance(node.getLatitude(),node.getLongitude(),startNode.getLatitude(),startNode.getLongitude());
            node.setDistance(distance);
            //System.out.println(distance);
            node.setHx(distance);

        }
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closeList = new ArrayList<Node>();
        Node currentPoint=startNode;
        openList.add(currentPoint);

        while (openList != null) {
          // System.out.println("1111111111111");
            if (currentPoint.getNodeId().equals(ends)) break;
            else {
                openList.remove(currentPoint);
                closeList.add(currentPoint);
                //System.out.println(currentPoint.getNodeId());
                ArrayList<String> consecutivePoints = currentPoint.getRoads();
                if (consecutivePoints!=null)
                for (String i : consecutivePoints) {
                    Path path=roads.get(i);
                    Node temp;

                    if (currentPoint.getNodeId().equals(path.getEnd())){
                        temp = maps.get(path.getStart());
                    }else {
                        temp = maps.get(path.getEnd());
                    }

                    if (!closeList.contains(temp) && !openList.contains(temp)) {
                        temp.setParent(currentPoint.getNodeId());
                        temp.hx = temp.getDistance();
                        temp.setGx(maps.get(temp.getParent()).gx+path.getLength());
                        temp.fx = temp.hx + temp.gx;
                        openList.add(temp);
                    }
                    else if (openList.contains(temp)){
                        //System.out.println(11111);
                        if (maps.get(temp.getParent()).gx>currentPoint.gx){
                            //System.out.println(222);
                            temp.setParent(currentPoint.getNodeId());
                            temp.hx = temp.getDistance();
                            temp.setGx(maps.get(temp.getParent()).gx+path.getLength());
                            temp.fx = temp.hx + temp.gx;
                        }
                    }

                }
                if (openList.size()==0){
                    System.out.println("wulu");
                    break;

                }
                Node minFxPoint = openList.get(0);
                int i = 0;
                for (Node temp : openList) {

                    if (temp.fx < minFxPoint.fx) {
                        minFxPoint = openList.get(i);
                    }
                    i++;
                }
                currentPoint = minFxPoint;
            }
        }
        paths.add(currentPoint.getNodeId());
        paths.add(currentPoint.getParent());
//        System.out.println(1111111111);
//        System.out.println(currentPoint.getNodeId());
        while (!currentPoint.getParent() .equals(start)) {
            System.out.println(currentPoint.getParent());
            currentPoint = maps.get(currentPoint.getParent());
            paths.add(currentPoint.getParent());

        }
        return  paths;
    }
    public double Distance(double long1, double lat1, double long2,
                           double lat2)
    {

        double a, b, R;
        R = 6378137; // 地球半径
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
