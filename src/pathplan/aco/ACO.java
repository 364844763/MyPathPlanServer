package pathplan.aco;

import bean.Node;
import bean.Path;
import bean.Relation;

import java.util.ArrayList;
import java.util.HashMap;

/**	Jabbarpour M R, Noor R M, Anuar N B, et al. Bee Colony Optimisation for Vehicle Traffic Systems: Applications and Challenges[J].
 * International Journal of Bio-Inspired Computation, 2014, 6(1):32-56.
 * Created by jiajie on 2016/4/15.
 */
public class ACO {
    private String start;
    private String  end;
    private ArrayList<Node> nodes;
    private HashMap<Relation, Path> edges;
    private HashMap<String,Node> map;
    public double bestTime ;
    private HashMap<String, Path> paths;

    private int antNum;
    private int maxGen;
    private double alpha;
    private double beta;
    private double rho;
    private double Q;
    private HashMap<Relation,Double> pheromone;
    private double pheMax;
    private double pheMin;


    public ACO(String start, String end,  HashMap<String, Path> paths, ArrayList<Node> nodes) {
        this.start = start;
        this.end = end;
        this.paths =paths;
        this.nodes=nodes;
        map =new HashMap<>();
        for (Node node:nodes){
//            System.out.println(node.getNodeId());
            map.put(node.getNodeId(),node);
        }
    }
    public ACO(String start, String end, ArrayList<Node> nodes, HashMap<Relation, Path> edges, HashMap<String,Node> map) {
        this.start = start;
        this.end = end;
        this.nodes=nodes;
        this.edges=edges;
        this.map = map;
    }
    private void initializtion(){
        antNum=30;
        maxGen=200;
        alpha=2.0f;
        beta=2.0f;
        rho=0.5f;
        Q=400;
//        pheromone = new HashMap<>();
//        for(Node node:nodes){
//            ArrayList<String> relations = node.getRoads();
//            Relation relation = null;
//            for(String string : relations) {
//                relation = new Relation(node.getNodeId(), string);
//                pheromone.put(relation, 1.0);
//            }
//        }
        pheMax = 8.0;
        pheMin =0.5;

    }


    public ArrayList<String> constructor() {
        initializtion();

        ArrayList<String> bestTour = new ArrayList<>();
        ArrayList<ArrayList<String>> antsTours = new ArrayList<>();
        double[] lengthOfTours = new double[antNum];
        for(int i=0;i<antNum;i++){
            lengthOfTours[i]=0;
        }
        double bestLength =Double.MAX_VALUE;

        for(int i=0;i<maxGen;i++){
            /*
            * 将每只蚂蚁进行寻路，如果蚂蚁走进死路，就让这只蚂蚁重新寻路，将所有蚂蚁找到的路径和代价保存起来，记录最好的代价和路径
            * */
            for(int j=0;j<antNum;j++){
                alpha = Math.pow(beta,i-10);
                if(alpha>8) alpha=8;
//                Bee ant = new Bee(nodes,edges,map,pheromone,alpha,beta,start,end);
                Ant ant = new Ant(start,end,beta,alpha,paths,map);
                ArrayList<String> tour = ant.search2();
                System.out.println(bestTour);
                if(tour!=null){
                    antsTours.add(tour);
                    lengthOfTours[j]=ant.getTourLength();
                    if(lengthOfTours[j]<=bestLength){
                        bestLength =lengthOfTours[j];
                        bestTour = tour;
                        System.out.println(bestTour);
                    }
                }
                else{
                    j--;
                }
            }
            /*
            * 判断本代蚂蚁得到的解是否收敛
            * */
            double num=0;
            for(int j=0;j<antNum;j++){
                if(lengthOfTours[j]==bestLength)
                    num++;
            }
            if(num/antNum >= 0.9){
                System.out.println("在第"+i+"代收敛");
                antsTours.clear();
                return bestTour;
            }
            /*
            * 更新信息素
            * */
            for(Node node:nodes){
                ArrayList<String> relations = node.getRoads();
                Relation relation = null;
                for(String string : relations) {
                    relation = new Relation(node.getNodeId(), string);
                    double temp = rho * pheromone.get(relation);
                    pheromone.put(relation,temp);
                }
            }
            for(int j=0;j<antNum;j++){
                double delta = Q/lengthOfTours[j];
                //System.out.println(lengthOfTours[j]);
                ArrayList<String> tour = antsTours.get(j);
                if(lengthOfTours[j] <= bestLength){
                    for (int k=0;k<tour.size()-1;k++){
                        Relation relation = new Relation(tour.get(k),tour.get(k+1));
                        double temp = pheromone.get(relation);
                        pheromone.put(relation,temp+3*delta);

                    }
                }else{
                    for (int k=0;k<tour.size()-1;k++){
                        Relation relation = new Relation(tour.get(k),tour.get(k+1));
                        double temp = pheromone.get(relation);
                        pheromone.put(relation,temp+delta);

                    }
                }

            }
            antsTours.clear();
           // System.out.println("第"+i+"代");
           // System.out.println(bestLength);
        }
        bestTime = bestLength;
        return bestTour;

    }
}
