package MapReader;

import bean.NewNode;
import bean.NewRoad;
import bean.Relation;
import entity.State;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jiajie on 2016/6/17.
 */
public class FuzhoumapReader {
    private String name;
    private HashMap<String, ArrayList<State>> successors;
    private HashMap<String, ArrayList<State>> predecessors;
    private HashMap<Relation, NewRoad> roads;
    private HashMap<String,State> map;
    public FuzhoumapReader(String name) {
        this.name = name;
        successors = new HashMap<>();
        predecessors = new HashMap<>();
        roads = new HashMap<>();
        map = new HashMap<>();
    }

    public void read() {
        try {
            FileInputStream is = new FileInputStream(name);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(",");
                String id1 = temp[10];
                String id2 = temp[11];
                ArrayList<State> nodes;
                NewNode node1 = new NewNode();
                node1.setId(id1);
                node1.setLatitude(Double.parseDouble(temp[43]));
                node1.setLongitude(Double.parseDouble(temp[44]));
                NewNode node2 = new NewNode();
                node2.setId(id2);
                node2.setLatitude(Double.parseDouble(temp[45]));
                node2.setLongitude(Double.parseDouble(temp[46]));
                if (successors.containsKey(id1)) {
                    nodes = successors.get(id1);
                    nodes.add(node2);
                    successors.replace(id1,nodes);
                }else {
                    nodes = new ArrayList<>();
                    nodes.add(node2);
                    successors.put(id1,nodes);
                }
                if (predecessors.containsKey(id1)) {
                    nodes = predecessors.get(id1);
                    nodes.add(node1);
                    predecessors.replace(id1,nodes);
                }else {
                    nodes = new ArrayList<>();
                    nodes.add(node1);
                    predecessors.put(id1,nodes);
                }
                if (successors.containsKey(id2)) {
                    nodes = successors.get(id2);
                    nodes.add(node1);
                    successors.replace(id2,nodes);
                }else {
                    nodes = new ArrayList<>();
                    nodes.add(node1);
                    successors.put(id2,nodes);
                }
                if (predecessors.containsKey(id2)) {
                    nodes = predecessors.get(id2);
                    nodes.add(node2);
                    predecessors.replace(id2,nodes);
                }else {
                    nodes = new ArrayList<>();
                    nodes.add(node2);
                    predecessors.put(id2,nodes);
                }
                Relation relation = new Relation(id1,id2);
                float f = Float.parseFloat(temp[13]);
                NewRoad road = new NewRoad(node1,node2,f);
                roads.put(relation,road);
                relation = new Relation(id2,id1);
                road = new NewRoad(node2,node1,f);
                roads.put(relation,road);
                map.put(id1,node1);
                map.put(id2,node2);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, ArrayList<State>> getSuccessors() {
        return successors;
    }

    public HashMap<String, ArrayList<State>> getPredecessors() {
        return predecessors;
    }

    public HashMap<Relation, NewRoad> getRoads() {
        return roads;
    }

    public HashMap<String, State> getMap() {
        return map;
    }
}
