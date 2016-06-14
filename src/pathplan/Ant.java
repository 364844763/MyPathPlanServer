package pathplan;

import bean.Node;
import bean.Path;
import bean.Relation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by wang on 2016/4/15.
 */
public class Ant {
    private HashSet<String> tabu;
    private ArrayList<Node> nodes;
    private HashMap<Relation, Path> edges;
    private HashMap<String,Node> map;
    private HashMap<Relation,Double> pheromone;
    private double alpha,beta;
    private ArrayList<String> tour;
    private double tourTime;
    private String start,end;

    HashMap<Integer,Double> v = new HashMap<>();
    HashMap<Integer,Double> fl = new HashMap<>();


    public Ant(ArrayList<Node> nodes,HashMap<Relation, Path> edges,HashMap<String,Node> map,HashMap<Relation,Double> pheromone,
               double alpha,double beta,String start,String end){
        tabu = new HashSet<>();
        this.nodes=nodes;
        this.edges=edges;
        this.map =map;
        this.pheromone=pheromone;
        this.alpha=alpha;
        this.beta=beta;
        tourTime=0;
        tour = new ArrayList<>();
        this.start=start;
        this.end=end;
        v.put(1,8.3);
        v.put(2,11.1);
        v.put(3,16.7);
        v.put(4,22.2);
        v.put(5,27.8);
        fl.put(1,1.0);
        fl.put(2,0.7);
        fl.put(3,0.3);
        fl.put(4,0.1);
        tour.add(start);
    }

    public ArrayList<String> search(){
        String currentPoint=start;
        while(!currentPoint.equals(end)){
            ArrayList<String> relations =  map.get(currentPoint).getRoads();
            for(int i=0;i<relations.size();i++){
                if(tabu.contains(relations.get(i)))
                    relations.remove(i);
            }
            if(relations.isEmpty()){
                return null;
            }else{
                double[] p = new double[relations.size()];
                double sum=0;
                for(int i=0;i<relations.size();i++){
//                    if(tabu.contains(relations.get(i)))
//                        continue;
                    Relation relation = new Relation(currentPoint,relations.get(i));
                    Path edge = edges.get(relation);
                    if (edge==null){
                        System.out.println("chucuo");
                        return null;
                    }

                    sum  +=Math.pow(pheromone.get(relation),alpha)*Math.pow((40*v.get(edges.get(relation).getLevel())*
                            fl.get(edges.get(relation).getFlow(((int)(tourTime/60))%61)))/edges.get(relation).getLength(),beta);
                }
                for (int i=0;i<relations.size();i++){
                    Relation relation = new Relation(currentPoint,relations.get(i));

                    p[i]=Math.pow(pheromone.get(relation),alpha)*Math.pow((40*v.get(edges.get(relation).getLevel())*
                            fl.get(edges.get(relation).getFlow(((int)(tourTime/60))%61)))/edges.get(relation).getLength(),beta)/sum;
                }

                double random = Math.random();
                String nextPoint = null;
                double sum1=0.0;
                for(int i=0;i<relations.size();i++){
                    sum1+=p[i];
                    if(sum1>=random){
                        nextPoint = relations.get(i);
                        break;
                    }
                }
                tabu.add(currentPoint);
                tour.add(nextPoint);
                Relation relation = new Relation(currentPoint,nextPoint);
                tourTime+=edges.get(relation).getLength()/(v.get(edges.get(relation).getLevel())*
                        fl.get(edges.get(relation).getFlow(((int)(tourTime/60))%61)));
                currentPoint=nextPoint;
            }
        }
        return tour;

    }

    public double getTourLength() {
        return tourTime;
    }
}
