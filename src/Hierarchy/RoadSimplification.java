package Hierarchy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by wang on 2016/5/5.
 */
public class RoadSimplification {
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private boolean isNewNode;
    private Node node;

    public void roadSimplify(File file){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            br.readLine();
            line = br.readLine();
            while(line!=null){
                String[] temp = line.split(",");
                isNewNode = true;
                Node node = null;
                for (Node n:nodes){
                    if(n.getNodeId().equals(temp[10])){
                        isNewNode = false;
                        node = n;
                        break;
                    }
                }
                if(isNewNode){
                    node = new Node();
                    nodes.add(node);
                    node.setNodeId(temp[10]);
                    node.setX(Double.valueOf(temp[43]));
                    node.setY(Double.valueOf(temp[44]));
                }
                node.addRelation(temp[11]);

                isNewNode = true;
                node = null;
                for (Node n:nodes){
                    if(n.getNodeId().equals(temp[11])){
                        isNewNode = false;
                        node = n;
                        break;
                    }
                }
                if(isNewNode){
                    node = new Node();
                    nodes.add(node);
                    node.setNodeId(temp[11]);
                    node.setX(Double.valueOf(temp[45]));
                    node.setY(Double.valueOf(temp[46]));
                }
                node.addRelation(temp[10]);

                Edge edge = new Edge();
                edges.add(edge);
                edge.setStart(temp[10]);
                edge.setEnd(temp[11]);
                edge.setDistance(Integer.valueOf(temp[13]));
                edge.setLevel(Integer.valueOf(temp[3]));

                line = br.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(nodes.size());
        System.out.println(edges.size());
        BufferedWriter writer1 = null;
        try {
            writer1 = new BufferedWriter(new FileWriter(new File("C:\\Users\\wang\\Desktop\\biyesheji\\分层表\\Nodes.txt"), true));
            for(Node node:nodes){
                writer1.write(node.getNodeId()+","+node.getX()+","+node.getY());
                ArrayList<String> relation =node.getRelation();
                for(int i=0; i<relation.size();i++){
                    writer1.write(","+relation.get(i));
                }
                writer1.write("\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter writer2 = null;
        try {
            writer2= new BufferedWriter(new FileWriter(new File("C:\\Users\\wang\\Desktop\\biyesheji\\分层表\\Edges.txt"), true));
            for(Edge edge:edges){
                writer2.write(edge.getStart()+","+edge.getEnd()+","+edge.getDistance()+","+edge.getLevel()+"\r\n");
                writer2.write(edge.getEnd()+","+edge.getStart()+","+edge.getDistance()+","+edge.getLevel()+"\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

