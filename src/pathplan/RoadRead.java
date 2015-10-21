package pathplan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/8/5.
 */
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
                startNode.setNodeId(temp[10]);
                endNode.setNodeId(temp[11]);
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


//                while (iterator.hasNext()){
//                    RoadRelation relation=(RoadRelation)iterator.next();
//                    String content =roads.get(relation).getStart()+","+roads.get(relation).getEnds()+","+roads.get(relation).getFlow()+"\n";
//                    HashMap<Integer, Integer> temp=roads.get(relation).getSchedule();
//                    for (int i = 0; i <240; i++) {
//                        content=content+temp.get(i)+",";
//                        // System.out.println(i);
//                    }
//                    content=content+"\n";
//                    // System.out.println(roads.get(iterator.next()).getStart());
//                    bw.write(content);
//                    bw.flush();
//                }
//                bw.close();



//                Road road=new Road();
//                RoadRelation relation=new RoadRelation(Integer.parseInt(temp1[0]),Integer.parseInt(temp1[1]));
//                road.setFlow(Integer.parseInt(temp1[2]));
//                road.setStart(Integer.parseInt(temp1[0]));
//                road.setEnds(Integer.parseInt(temp1[1]));
//                line=bw.readLine();
//                //System.out.println(line);
//                String[] temp2=line.split(",");
//                for (int i = 0; i <240 ; i++) {
//                    road.setSchedule(i,Integer.parseInt(temp2[i]));
//                }
//                line=bw.readLine();
//                roads.put(relation,road);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        File file=new File("E:\\RoadToNode.csv");
//        file.createNewFile();
//        FileWriter fw = new FileWriter(file.getAbsoluteFile());
//        BufferedWriter bw = new BufferedWriter(fw);
//        for (Node node:nodes){
//            String content=node.getNodeId()+",";
//            List<String> roads=node.getRoads();
//            if (roads!=null)
//            for (String road:roads){
//                content+=road+",";
//            }
//            content=content+"\n";
//            bw.write(content);
//            bw.flush();
//        }
//        bw.close();

    }


}
