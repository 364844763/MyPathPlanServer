package pathmatch;

import bean.Location;
import bean.Road;
import util.NearestPoint;
import util.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/22.
 */
public class RoadMatcher {
    public static  ArrayList<String> match(Location point,HashMap<String, Road> map){
        ArrayList<String> results = new ArrayList<>();
        Location point2=new Location(point.latitude,point.longitude);
        Location xyP3=new  GPSzhuanXY(point).zhuanhuan();
        Location xyP33=new  GPSzhuanXY(point2).zhuanhuan();
        Vector3f c = new Vector3f((float)xyP3.latitude, (float)xyP3.longitude, 0);
        Vector3f c2 = new Vector3f((float)xyP33.latitude, (float)xyP33.longitude, 0);

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            Road val = (Road)entry.getValue();
            ArrayList<Location>  points=val.getZheDian();
            int i=points.size();
            for (int j = 1; j <i ; j++) {
                Location p1=new Location(points.get(j-1).latitude,points.get(j-1).longitude);
                Location p2=new Location(points.get(j).latitude,points.get(j).longitude);
                Location p11=new Location(points.get(j-1).longitude,points.get(j-1).latitude);
                Location p22=new Location(points.get(j).longitude,points.get(j).latitude);
                Location xyP1=new GPSzhuanXY(p1).zhuanhuan();
                Location xyP2=new  GPSzhuanXY(p2).zhuanhuan();
                Location xyP11=new GPSzhuanXY(p11).zhuanhuan();
                Location xyP22=new  GPSzhuanXY(p22).zhuanhuan();
                Vector3f a = new Vector3f((float)xyP1.latitude, (float)xyP1.longitude, 0);
                Vector3f b = new Vector3f((float)xyP2.latitude, (float)xyP2.longitude, 0);
                Vector3f a1 = new Vector3f((float)xyP11.latitude, (float)xyP11.longitude, 0);
                Vector3f b1 = new Vector3f((float)xyP22.latitude, (float)xyP22.longitude, 0);
                NearestPoint nearestPoint = new NearestPoint(a,b,c);
                NearestPoint nearestPoint2 = new NearestPoint(a1,b1,c2);
                double ok = nearestPoint.calculateDistance();
                double ok2 = nearestPoint2.calculateDistance();
//                System.out.println(ok2);
                if (ok2<150&&ok<150){
                    results.add(key);
                    break;
                }
            }

            }


        return results;
    }
}
