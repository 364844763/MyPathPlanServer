package pathplan;

import bean.Location;

/**
 * Created by jiajie on 15/11/19.
 */
public class DrectionDeterminer {
    private Location start;
    private Location intersection;
    private Location end;


    /**
     * 构造函数
     *
     * @param start        起点
     * @param intersection 交点
     * @param end          终点
     */
    public DrectionDeterminer(Location start, Location intersection, Location end) {
        this.start = start;
        this.intersection = intersection;
        this.end = end;
    }

    /**
     * 求直线斜率
     *
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 斜率
     */
    private double gradient(Location p1, Location p2) {
        return (p1.longitude - p2.longitude) / (p1.latitude - p2.latitude);
    }

    public int determineDrection() {
        int drection = 0;
        double k1 = gradient(start, intersection);
        double k2 = gradient(intersection, end);
        double angle = (k2 - k1) / (1 + k1 * k2);
        if (angle < -0.1) {
            drection = 1;
        } else if (angle > 0.1) {
            drection = 2;
        }
        return drection;
    }

}
