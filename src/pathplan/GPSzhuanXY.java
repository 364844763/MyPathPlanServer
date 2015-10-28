package pathplan;

import bean.Location;

/**
 * Created by jiajie on 15/8/22.
 */
public class GPSzhuanXY {
    Location xyLocation=new Location(0,0);
    Location gpsLocation;
    static final double A=6378137;
    static final double B=6356752.3142;

    /**
     * 构造函数，传入GPS原始信息
     * @param gpsLocation
     */
    public GPSzhuanXY(Location gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    /**
     * GPS转直角坐标
     * @return 直角坐标点
     */
    public Location zhuanhuan(){
        xyLocation.latitude=A*A/Math.sqrt(Math.tan(Math.toRadians(gpsLocation.latitude))*(Math.tan(Math.toRadians(gpsLocation.latitude)))*B*B+A*A);
       // System.out.print((Math.tan(gpsLocation.x)));
        xyLocation.longitude=B*B/Math.sqrt((1/Math.tan(Math.toRadians(gpsLocation.latitude)))*(1/Math.tan(Math.toRadians(gpsLocation.latitude)))*A*A+B*B);
        return xyLocation;
    }

}
