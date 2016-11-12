package Hierarchy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by wang on 2016/5/5.
 */
public class Vcs {
    private ArrayList<Node> nodes;
    private HashMap<Relation,Edge> edges;
    HashMap<String,HashSet<String>> communities;
    HashMap<String,Node> map ;
    double m;
    HashMap<Integer,Double> v1 = new HashMap<>();


    public void doLouvain(File file1,File file2){
        ReadNode readNode = new ReadNode();
        nodes = readNode.readNode(file1);
        map = readNode.getMap();
        ReadEdge readEdge = new ReadEdge();
        edges = readEdge.readEdge(file2);

        initial();
        int count=0;
        int num=0;
        int temp=0;
        int sum=0 ;
        int n=0;
        while(n<3){
            count++;
            num=0;
            for(Node node:nodes){
                ArrayList<String> neighbor = node.getRelation();
                Node bestNeighbor =null;
                double maxDelta = 0;
                for(String string:neighbor){
                    if(!node.getParentCommunity().equals(map.get(string).getParentCommunity())){
                        double modularityGain = computeMdularityGain(node,map.get(string).getParentCommunity());
                        if (modularityGain>maxDelta){
                            bestNeighbor = map.get(string);
                            maxDelta = modularityGain;
                        }
                    }
                }

                if(bestNeighbor !=null) {
                    communities.get(node.getParentCommunity()).remove(node.getNodeId());
                    communities.get(bestNeighbor.getParentCommunity()).add(node.getNodeId());
                    node.setParentCommunity(bestNeighbor.getParentCommunity());
                }
                num++;
                System.out.println("第"+num+"个节点结束");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sum=0;
            for(int i=0;i<communities.size();i++){
                HashSet<String> community = communities.get(Integer.toString(i));
                if(!community.isEmpty()){
                    sum++;
                }
            }
            System.out.println(sum);
            if(sum == temp)
                n++;
            else{
                temp = sum;
                n=0;
            }

            System.out.println("第"+count+"轮结束");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter writer1 = null;
        try {
            writer1 = new BufferedWriter(new FileWriter(new File("D:\\work\\H1toH2.txt"), true));
            for(int i=0;i<communities.size();i++){
                HashSet<String> community = communities.get(Integer.toString(i));
                if(!community.isEmpty())
                    for (String string:community)
                        writer1.write(string+","+Integer.toString(i)+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter writer2 = null;
        try {
            writer2= new BufferedWriter(new FileWriter(new File("D:\\work\\H2toH1.txt"), true));
            for(int i=0;i<communities.size();i++){
                HashSet<String> community = communities.get(Integer.toString(i));
                if(!community.isEmpty()){
                    writer2.write(Integer.toString(i));
                    for (String string:community)
                        writer2.write(","+string);
                    writer2.write("\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        BufferedWriter writer3 = null;
        try {
            writer3 = new BufferedWriter(new FileWriter(new File("D:\\work\\H4Nodes.txt"), true));
            for(int i=0;i<communities.size();i++){
                HashSet<String> community = communities.get(Integer.toString(i));
                if(!community.isEmpty()){
                    double sum1 = 0;
                    double sum2 = 0;
                    HashSet<String> neighborCommunity = new HashSet<String>();
                    for(String string:community){
                        Node node = map.get(string);
                        sum1+=node.getX();
                        sum2+=node.getY();
                        ArrayList<String> relation = node.getRelation();
                        for(String string1:relation){
                            if(!map.get(string1).getParentCommunity().equals(Integer.toString(i)))
                                neighborCommunity.add(map.get(string1).getParentCommunity());
                        }
                    }
                    double longitude = sum1 / community.size();
                    double latitude = sum2 / community.size();
                    writer3.write(Integer.toString(i)+","+longitude+","+latitude);
                    for (String string:neighborCommunity){
                        writer3.write(","+string);
                    }
                    writer3.write("\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public void initial(){
        communities = new HashMap<>();
        v1.put(1,8.3);
        v1.put(2,11.1);
        v1.put(3,16.7);
        v1.put(4,22.2);
        v1.put(5,27.8);
        for(int i=0; i<nodes.size();i++){
            HashSet<String> community = new HashSet<>();
            community.add(nodes.get(i).getNodeId());
            communities.put(Integer.toString(i),community);
            nodes.get(i).setParentCommunity(Integer.toString(i));
        }
        for(Node node:nodes){
            ArrayList<String> nodesTemp = node.getRelation();
            for(String string:nodesTemp){
                Relation relation = new Relation(node.getNodeId(),string);
                m+=1.0/edges.get(relation).getDistance();
                m=m/2;
            }
        }
    }


    public double computeMdularityGain(Node node,String communityId){

        double u = 0; //node与neighbor所在的社区c中所有相连的路段的长度和
        double v = 0; //neighbor所在社区c以外的节点，c内节点的路段长度和
        double w = 0; //node为起点的路段长度和



        HashSet<String> commuity = communities.get(communityId);
        ArrayList<String> relation = node.getRelation();
        for(String string:relation){
            if(commuity.contains(string)){
                Relation relation1 = new Relation(node.getNodeId(),string);
                u+=1.0/edges.get(relation1).getDistance();
            }
        }
        u=2*u;


        for(String string:commuity){
            ArrayList<String> relation1  = map.get(string).getRelation();
            for(String string1:relation1){
                Relation relation2 = new Relation(string,string1);
                if(!commuity.contains(string1))
                    v+=1.0/edges.get(relation2).getDistance();
            }
        }


        ArrayList<String> relation1= node.getRelation();
        for(String string:relation1){
            Relation relation2 = new Relation(node.getNodeId(),string);
            w += 1.0/edges.get(relation2).getDistance();
        }

        return u/2*m - (v*w)/2*(m*m);
    }
}
