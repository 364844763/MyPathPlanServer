import bean.Location;
import bean.Path;
import bean.Road;
import com.alibaba.fastjson.JSON;
import MapReader.Mapreader;
import pathmatch.RoadMatcher;
import pathplan.PathFinding;
import MapReader.RoadRead;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by �ֽ� on 2015/10/28.
 */
public class PathFindServer  extends HttpServlet {
    HashMap<String, Road> map;
    private RoadRead roadRead;
    private Map<String,Path> paths;
    @Override
    public  void init() throws ServletException
    {
        super.init();
        roadRead=new RoadRead();
        try {
            map= Mapreader.reader("/Users/jiajie/Downloads/zhedian_output.csv");
            roadRead.readNodes("/Users/jiajie/Downloads/path.csv");
            paths=roadRead.getPaths();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/json; charset=UTF-8");
        List<Path> results =null;
        Location start=new Location(Double.parseDouble(request.getParameter("start_x")),Double.parseDouble(request.getParameter("start_y")));
         Location end=new Location(Double.parseDouble(request.getParameter("end_x")),Double.parseDouble(request.getParameter("end_y")));
        String sno=location2No(start);
        String eno=location2No(end);
        PathFinding pathFinding=new PathFinding(sno,eno,roadRead.getNodes(),(HashMap)paths);
        results=pathFinding.pathFinder();
        if (results==null){
            pathFinding=new PathFinding(eno,sno,roadRead.getNodes(),(HashMap)paths);
            results=pathFinding.pathFinder();
        }
        if(results==null){
            response.getOutputStream().write(JSON.toJSONString("").getBytes("UTF-8"));
        }
        //List<Path> data=getData(results);

        response.getOutputStream().write(JSON.toJSONString(results).getBytes("UTF-8"));

    }
    private String location2No(Location start){
        ArrayList<String> startRoads=RoadMatcher.match(start, map);
        double min=10000000;
        String sno="";
        double i=0;
        for (String string:startRoads){
            paths.get(string);
            Road temp= map.get(string);
            i =Math.sqrt((temp.getStart().latitude-start.longitude)*(temp.getStart().latitude-start.longitude)+(temp.getStart().longitude-start.latitude)*(temp.getStart().longitude-start.latitude));
            if (min>i){
                min=i;
                sno=paths.get(string).getStart();
            }
            i =Math.sqrt((temp.getEnd().latitude-start.longitude)*(temp.getEnd().latitude-start.longitude)+(temp.getEnd().longitude-start.latitude)*(temp.getEnd().longitude-start.latitude));
            if (min>i){
                min=i;
                sno=paths.get(string).getEnd();
            }
        }
        return sno;
    }
    private List<Path> getData(List<String> result)
    {
        List<Path> data = new ArrayList<>();
        for (String str:result){
            if (paths.containsKey(str)){
                Path path=paths.get(str);
                data.add(path);}
        }
        return data;
    }
}
