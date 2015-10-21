/**
 * Created by jiajie on 15/10/20.
 */
import pathplan.PathFinding;
import pathplan.RoadRead;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 扩展 HttpServlet 类
public class HelloWorld extends HttpServlet {
    RoadRead roadRead;
    private String message;

    @Override
    public void init() throws ServletException
    {
        // 执行必需的初始化
        super.init();
        roadRead=new RoadRead();
        try {
            roadRead.readNodes("/Users/jiajie/Downloads/path.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = "Hello World";
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
    {

        Map<String,String> data = getData();
        data.put("test", request.getParameter("username"));
        response.setContentType("text/html;charset=UTF-8");
        String start=request.getParameter("start");
        String end=request.getParameter("end");
        PrintWriter out = response.getWriter();
        try {
            if (start.isEmpty()||end.isEmpty())
                return;
            PathFinding pathFinding=new PathFinding(start,end,roadRead.getNodes(),roadRead.getPaths());
            List<String> results =pathFinding.pathFinder();
            // Write some content
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CalendarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Hello " + data.get("username") + ", " + data.get("message") + data.get("test")+"</h2>");
            out.println("<h2>The time right now is : " + new Date() + "</h2>");
            for (String str:results){
                out.println("<h2>The road is : " + str+ "</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
            response.getOutputStream().write(message.toString().getBytes("UTF-8"));
            response.setContentType("text/json; charset=UTF-8");
        }
    }

    //This method will access some external system as database to get user name, and his personalized message
    private Map<String, String> getData()
    {
        Map<String, String> data = new HashMap<String, String>();
        data.put("username", "Guest");
        data.put("message", "Welcome to my world !!");
        return data;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String> data = getData();
        data.put("test",req.getParameter("username"));
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            RoadRead roadRead=new RoadRead();
            roadRead.readNodes("/Users/jiajie/Downloads/path.csv");
            PathFinding pathFinding=new PathFinding("935547","948463",roadRead.getNodes(),roadRead.getPaths());
            List<String> results =pathFinding.pathFinder();

            // Write some content
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CalendarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Hello " + data.get("username") + ", " + data.get("message") + data.get("test")+"</h2>");
            out.println("<h2>The time right now is : " + new Date() + "</h2>");
            for (String str:results){
                out.println("<h2>The road is : " + str+ "</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();

        }
    }

    public void destroy()
    {
        // 什么也不做
    }
}