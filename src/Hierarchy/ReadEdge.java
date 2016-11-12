package Hierarchy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Created by wang on 2016/5/5.
 */
public class ReadEdge {
    public  HashMap<Relation,Edge> readEdge(File file){
        HashMap<Relation,Edge> edges=new HashMap<Relation, Edge>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            line=br.readLine();
            while (line!=null){
//                System.out.println(line);
                String[] temp=line.split(",");
                Edge edge = new Edge();
                Relation relation=new Relation(temp[0],temp[1]);
                edge.setStart(temp[0]);
                edge.setEnd(temp[1]);
                edge.setDistance(Integer.valueOf(temp[2]));
                edge.setLevel(Integer.valueOf(temp[3]));
                edges.put(relation,edge);
                line=br.readLine();
//                String[] temp1=line.split(",");
//                edge.setFlow(temp1);
//                line=br.readLine();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return edges;
    }
}
