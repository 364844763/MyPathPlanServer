package Hierarchy;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wang on 2016/5/5.
 */
public class ReadNode {
    private HashMap<String ,Node> map = new HashMap<>();
    public  ArrayList<Node> readNode(File file){
        ArrayList<Node> nodes=new ArrayList<Node>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            line=br.readLine();
//            line=br.readLine();
            while (line!=null){
//                System.out.println(line);
                String[] temp=line.split(",");
                Node node = new Node();
                node.setNodeId(temp[0]);
                node.setX(Double.valueOf(temp[1]));
                node.setY(Double.valueOf(temp[2]));
                for (int i = 3; i <temp.length ; i++) {
                    node.addRelation(temp[i]);
                }
                nodes.add(node);
                map.put(temp[0],node);
                line=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    public HashMap<String,Node> getMap(){
        return map;
    }
}
