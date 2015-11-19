package pathplan;

import bean.Location;
import bean.Node;
import bean.Path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class RoadRead {
   private Node startNode;
   private Node endNode;
   private boolean isNewStartNode;
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public HashMap<String, Path> getPaths() {
        return paths;
    }

    public void setPaths(HashMap<String, Path> paths) {
        this.paths = paths;
    }

    private boolean isNewEndNode;
   private ArrayList<Node> nodes=new ArrayList<Node>();
   private HashMap<String,Path> paths=new HashMap<String, Path>();
    public RoadRead() {

    }

    public void readNodes(String path) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line=null;
            line=br.readLine();
            line=br.readLine();
            while (line!=null){

                // System.out.println(line);
                String[] temp=line.split(",");
                isNewStartNode=true;
                isNewEndNode=true;
                startNode=new Node();
                endNode=new Node();
               // System.out.println(temp[43]);
                //location=new Location(Double.valueOf(temp[43]),Double.valueOf(temp[44]))
                startNode.setNodeId(temp[10]);
                startNode.setLocation(new Location(Double.valueOf(temp[43]), Double.valueOf(temp[44])));
                endNode.setNodeId(temp[11]);
                endNode.setLocation(new Location(Double.valueOf(temp[45]),Double.valueOf(temp[46])));
                startNode.setLatitude(Double.valueOf(temp[44]));
                startNode.setLongitude(Double.valueOf(temp[43]));
                endNode.setLatitude(Double.valueOf(temp[46]));
                endNode.setLongitude(Double.valueOf(temp[45]));
                for (Node node:nodes){
                    if (node.getNodeId().equals(temp[10])){
                        startNode=node;
                        isNewStartNode=false;
                    }
                    if (node.getNodeId().equals(temp[11])){
                        endNode=node;
                        isNewEndNode=false;
                    }
                }
                if (Integer.parseInt(temp[6])==2){
                    startNode.setRoads(temp[2]);
                }
                else if (Integer.parseInt(temp[6])==3){
                    endNode.setRoads(temp[2]);
                }else {
                    startNode.setRoads(temp[2]);
                    endNode.setRoads(temp[2]);
                }
                if (isNewStartNode){
                    nodes.add(startNode);
                }
                if (isNewEndNode){
                    nodes.add(endNode);
                }
                Path path1=new Path();
                path1.setId(temp[2]);
                path1.setLength(Double.valueOf(temp[13]));
                path1.setStart(temp[10]);
                path1.setEnd(temp[11]);
                paths.put(temp[2],path1);
                line=br.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
