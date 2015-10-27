package util;

/**
 * Created by jiajie on 15/8/22.
 */
public class NearestPoint {
    private Vector3f A = new Vector3f(0, 0, 0);
    private Vector3f B = new Vector3f(3, 0, 0);
    private Vector3f C = new Vector3f(3f, 3, 0);   //直线外一点

    public NearestPoint(Vector3f a, Vector3f b, Vector3f c) {
        A = a;
        B = b;
        C = c;
    }

    public NearestPoint() {
    }

    public double calculateDistance() {
        //计算点到直线的距离
        Vector3f abDir = A.subtract(B).normalize();   //AB
        Vector3f cb = C.subtract(B);
        float length = abDir.dot(cb);     //cb在ab上的投影
        Vector3f result = abDir.mult(length).add(B);  //计算出垂直交点

       // System.out.println("ths cross point :" + result);
        double distance_a = C.distance(A);
        double distance_b = C.distance(B);
        double distance_result = C.distance(result);

        double min = Math.min(distance_a, distance_b);
        double moreMin = Math.min(min, distance_result);


        Vector3f ar = A.subtractLocal(result);
        Vector3f br = B.subtractLocal(result);
        if (ar.dot(br) > 0) {      //小于零，则交点在AB内部
          //  System.out.println("交点在AB外部");
            return min;
        }
        //System.out.println("交点在AB内部");
        return moreMin;
    }

    /*public static void main(String[] args) {
        NearestPoint nearestPoint = new NearestPoint();
        double ok = nearestPoint.calculateDistance();
        System.out.println("the nearest distance is :" + ok);
    }*/


}
