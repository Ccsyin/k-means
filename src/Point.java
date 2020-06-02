import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Point extends ArrayList<Point> implements Serializable {
    private double x;
    private double y;

    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    //计算两点之间的距离
    public static double distance(Point point1, Point point2){
        double distance;
        distance = Math.sqrt((point1.x-point2.x)*(point1.x-point2.x)+(point1.y-point2.y)*(point1.y-point2.y));
        return distance;
    }

    //判断两点是否相同
    public static  boolean equal(Point point1,Point point2){
        boolean a =true;
        if (point1.x == point2.x && point1.y == point2.y){
            a = true;
        }
        else{
            a = false;
        }
        return a;
    }

    @Override
    public String toString(){
        return "("+x+ ","+y+")";
    }

}

