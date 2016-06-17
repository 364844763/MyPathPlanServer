package MapReader;


import bean.Location;
import bean.Road;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/8/18.
 */
public class Mapreader {

    public static HashMap<String, Road> reader(String fileName){
        HashMap<String,Road> roads=new HashMap<String, Road>();
        Road road;
    try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line=null;
        line=br.readLine();
        line=br.readLine();
       // String temp[]=line.split(",");
        while (line!=null){
            String temp[]=line.split(",");

            if (roads.containsKey(temp[2])){
                road=roads.get(temp[2]);
                ArrayList<Location> points= (ArrayList<Location>) road.getZheDian();
                points.add(new Location(Double.valueOf(temp[51]),Double.valueOf(temp[52])));
                road.setZheDian(points);
            }else {
                road=new Road();
                road.setId(temp[2]);
                road.setStart(new Location(Double.valueOf(temp[43]),Double.valueOf(temp[44])));
                road.setEnd(new Location(Double.valueOf(temp[45]),Double.valueOf(temp[46])));
                ArrayList<Location> points=road.getZheDian();
                points.add(new Location(Double.valueOf(temp[51]),Double.valueOf(temp[52])));
                road.setZheDian(points);
                roads.put(temp[2],road);
            }
            line=br.readLine();
        }
//        System.out.println(temp.length);
        System.out.println("Succeed!");

    } catch (IOException e) {
        e.printStackTrace();
    }
        return roads;

}

}
