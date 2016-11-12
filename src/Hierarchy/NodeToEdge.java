package Hierarchy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wang on 2016/5/5.
 */
public class NodeToEdge {

    public void writeEdge(){
        ReadNode readNode = new ReadNode();
        ArrayList<Node> nodes = readNode.readNode(new File("D:\\work\\H2Nodes.txt"));
        HashMap<String,Node> map = readNode.getMap();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("D:\\work\\H2Edges.txt"), true));
            for(Node node:nodes){
                ArrayList<String> relation = node.getRelation();
                for(String string:relation){
                    Node node1 = map.get(string);
                    double distance = node.getDistance(node1);
                    writer.write(node.getNodeId()+","+node1.getNodeId()+","+Math.round(distance*357)+","+3+"\r\n");
                    for(int i=0;i<59;i++){
                        writer.write(1+",");
                    }
                    writer.write(1+"\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
