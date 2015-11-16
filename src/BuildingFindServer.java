import bean.Buliding;
import com.alibaba.fastjson.JSON;
import pathmatch.Buildmatch;
import pathmatch.BulidReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiajie on 15/11/16.
 */
public class BuildingFindServer extends HttpServlet {
    Map<Set, Buliding> bulidings;

    @Override
    public  void init() throws ServletException
    {
        // 执行必需的初始化
        super.init();
        bulidings= BulidReader.read("/Users/jiajie/Desktop/test.csv");

    }@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json; charset=UTF-8");
        String name=request.getParameter("name");
        if (name!=null){
            Buildmatch buildmatch=new Buildmatch(bulidings);
            List<Buliding> result=buildmatch.fuzzyMatch(name);
            response.getOutputStream().write(JSON.toJSONString(result).getBytes("UTF-8"));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json; charset=UTF-8");
        String name=request.getParameter("name");
        if (name!=null){
            Buildmatch buildmatch=new Buildmatch(bulidings);
            List<Buliding> result=buildmatch.fuzzyMatch(name);
            response.getOutputStream().write(JSON.toJSONString(result).getBytes("UTF-8"));
        }
    }

}
