/**
 * Created by jiajie on 15/10/20.
 */
import com.alibaba.fastjson.JSON;
import pathplan.Path;
import pathplan.PathFinding;
import pathplan.RoadRead;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 扩展 HttpServlet 类
public class HelloWorld extends HttpServlet {
    private RoadRead roadRead;
    private Map<String,Path> paths;
    @Override
    public void init() throws ServletException
    {
        // 执行必需的初始化
        super.init();
        roadRead=new RoadRead();
        try {
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
        List<String> results =null;
        response.setContentType("text/json; charset=UTF-8");
        String start=request.getParameter("start");
        String end=request.getParameter("end");
        if (start.isEmpty()||end.isEmpty())
             return;
        PathFinding pathFinding=new PathFinding(start,end,roadRead.getNodes(),(HashMap)paths);
        results=pathFinding.pathFinder();
        List<Path> data=getData(results);
        response.getOutputStream().write(JSON.toJSONString(data).getBytes("UTF-8"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy()
    {
        // 什么也不做
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