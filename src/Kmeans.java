import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {
    //k个簇中心
    private int k;
    //点集合列表
    private ArrayList<Point> points;
    //中心点列表
    private ArrayList<Point> center;
    //簇集合列表
    private ArrayList<ArrayList<Point>> cluster;

    public Kmeans(int k){
        if (k<0){
            k=0;
        }
        else this.k=k;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setCenter(ArrayList<Point> center) {
        this.center = center;
    }

    public ArrayList<Point> getCenter() {
        return center;
    }

    public void setCluster(ArrayList<ArrayList<Point>> cluster) {
        this.cluster = cluster;
    }

    public ArrayList<ArrayList<Point>> getCluster() {
        return cluster;
    }

    //初始化中心点（随机在已有点中选取K个不重复的点作为中心种子）
//    public  ArrayList<Point> initialCenter(int k,ArrayList<Point> points){
//        Random rd = new Random();
//        ArrayList<Point> center = new ArrayList<Point>();
//        ArrayList<Point> pointsCopy = new ArrayList<Point>(points);
//
//        double x,y;
//        for (int i=0;i<points.size();i++){
//            x=points.get(i).getX();
//            y=points.get(i).getY();
//            pointsCopy.add(new Point(x, y));
//        }
//
//        int a;
//        for (int i = 0; i<k;i++){
//            a=rd.nextInt(pointsCopy.size());
//            center.add(pointsCopy.get(a));
//            points.remove(pointsCopy.get(a));
//        }
//        return center;
//    }
    //初始化中心点（在已有点中选取前K个不重复的点作为中心种子）
    public  ArrayList<Point> initialCenter(ArrayList<Point> points,int k){
        ArrayList<Point> center = new ArrayList<Point>();
        for (int i=0;i<k;i++){
            center.add(points.get(i));
        }
        return center;
    }

    //初始化K个中心点的k个簇
    public ArrayList<ArrayList<Point>> initialCluster(ArrayList<Point> points,ArrayList<Point> center){
        ArrayList<ArrayList<Point>> cluster = new ArrayList<ArrayList<Point>>();
//        for (int i=0;i<k;i++){
//            ArrayList<Point> a= new ArrayList<Point>();
//            a.add(points.get(i));
//            cluster.add(a);
//        }
        //取前K个点当做中心种子
        for (int i=0;i<center.size();i++){
            ArrayList<Point> a= new ArrayList<Point>();
            a.add(center.get(i));
            cluster.add(a);
        }
        return cluster;
    }

    //将点集合进行初始分类
    public ArrayList<ArrayList<Point>> initialKcluster(ArrayList<ArrayList<Point>> cluster,ArrayList<Point> points,int k){
//        cluster=initialcluster(k,points);
        double[] mins = new double[k];
        int min=0;
        for (int i=0;i<points.size();i++){

            for (int j=0;j<k;j++){
                mins[j] = Point.distance(points.get(i),cluster.get(j).get(0));
            }
            //求该点到哪个中心点的距离最短
            for (int j=1;j<k;j++){
                if (mins[min]>mins[j]){
                    min = j;
                }
            }
            cluster.get(min).add(points.get(i));
        }
        for (int i= 0;i<cluster.size();i++){
            cluster.get(i).remove(0);
        }
        return cluster;
    }

    //更新中心种子（求K个簇集合的重心）
    public ArrayList<Point> updateKcenter(ArrayList<ArrayList<Point>> cluster,int k){
        ArrayList<Point> center = new ArrayList<Point>();
        double x;
        double y;
//        Point a = new Point(x,y);
        double sumx=0;
        double sumy=0;
        for (int i=0;i<k;i++){
            for (int j=0;j<cluster.get(i).size();j++){
                sumx=cluster.get(i).get(j).getX()+sumx;
                sumy=cluster.get(i).get(j).getY()+sumy;
            }
            x=(double) Math.round((sumx/cluster.get(i).size())*100)/100;
            y=(double) Math.round((sumy/cluster.get(i).size())*100)/100;
//            a.setX(x);
//            a.setY(y);
            center.add(new Point(x,y));
            sumx=0;
            sumy=0;
        }
        return center;
    }

    //更新簇集合
    public ArrayList<ArrayList<Point>> updateKcluster(ArrayList<ArrayList<Point>> cluster,ArrayList<Point> points,ArrayList<Point> center){
        for (int i=0;i<cluster.size();i++){
            cluster.get(i).clear();
        }
        for (int i=0;i<points.size();i++){
            double[] mins = new double[center.size()];
            int min=0;
            for (int j=0;j<center.size();j++){
                mins[j] = Point.distance(points.get(i),center.get(j));
            }
            //求该点到哪个中心点的距离最短
            for (int j=1;j<center.size();j++){
                if (mins[min]>mins[j]){
                    min = j;
                }
            }
            cluster.get(min).add(points.get(i));
        }
        return cluster;
    }

    //中心种子是否发生改变
    public boolean isKpointChange(ArrayList<Point> preCenter,ArrayList<Point> nowCenter){
        boolean is = true;
        int a = 0;
        for (int i=0;i<nowCenter.size();i++){

                Point point1 =nowCenter.get(i);
                Point point2 =preCenter.get(i);
                if (Point.equals(point1,point2)){
                    a = a+1;
                }
        }
        if (nowCenter.size() == a){
            is = false;
        }
        return  is;
    }




}
