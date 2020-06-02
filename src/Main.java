import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        //集合数
        int num;
        //最大迭代数
        int maxClusterTimes = 1000;
        int sum;
        //生成1000个随机点
//        double x,y;
//        ArrayList<Point> q= new ArrayList<Point>();
//        Random rd = new Random();
//        for (int i = 0; i <1000 ; i++) {
//            Point a = new Point(rd.nextInt(100),rd.nextInt(100));
//            q.add(a);
//        }
//        WriteData.toFile2(q,"/Users/chenshiyin/IdeaProjects/k-means/random.txt");

        //从文件中读取点集合
        ArrayList<Point> points = new ArrayList<Point>();
        ArrayList<Point> pointsCopy = new ArrayList<Point>();
        ReadData read = new ReadData();
        String fileName="C:/Users/Administrator/IdeaProjects/k-means/140-2.txt";
        points = read.read(fileName);
        pointsCopy = read.read(fileName);
        sum=read.sum(fileName);

        //输入种子数
        System.out.println("请输入您需要将这些点分为几类？");
        num=str.nextInt();
        if (num>points.size()){
            System.out.println("您输入的数量小于点集合中总数"+sum+",请重新输入您需要将这些点分为几类？");
            num=str.nextInt();
        }

        Kmeans k = new Kmeans(num);
        k.setPoints(points);

        //初始化
//        System.out.println("-----------初始化分类-----------");
        ArrayList<Point> initialCenter = new ArrayList<Point>();
        ArrayList<ArrayList<Point>> cluster = new ArrayList<ArrayList<Point>>();
        ArrayList<ArrayList<Point>> initialKcluster = new ArrayList<ArrayList<Point>>();
        //初始中心点
        initialCenter=k.initialCenter(points,num);
        System.out.println("初始中心种子："+initialCenter);
        //初始空簇
        cluster = k.initialCluster(points,initialCenter);
        //初始分类集合
        initialKcluster = k.initialKcluster(cluster,points,num);

//        System.out.println(cluster);

//        for (int i=0;i<kCluster.size();i++){
//            System.out.println("第"+(i+1)+"类为：");
//            System.out.println(kCluster.get(i));
//        }

        ArrayList<Point> preCenter = initialCenter;
        ArrayList<Point> updateKcenter = new ArrayList<Point>();
        ArrayList<ArrayList<Point>> updateKcluster = new ArrayList<ArrayList<Point>>();
        updateKcenter = k.updateKcenter(initialKcluster,num);
//        updateKcluster=k.updateKcluster(initialKcluster,points,initialCenter);


        //迭代
        for (int i=1;i<maxClusterTimes;i++){
            while (k.isKpointChange(preCenter,updateKcenter)){

                System.out.println("中心种子："+updateKcenter);
                updateKcluster=k.updateKcluster(cluster,points,updateKcenter);
                preCenter = updateKcenter;
                System.out.println("之前中心种子："+preCenter);
                updateKcenter = k.updateKcenter(updateKcluster,num);
                System.out.println("变化后中心种子："+updateKcenter);
            }

//                System.out.println("--------------第"+(i+1)+"次归类--------------");
//                for (int j=0;j<updateCluster.size();j++){
//                    System.out.println("第"+(j+1)+"类为：");
//                    System.out.println(updateCluster.get(j));
//                }

        }

        //输出最后分类结果
        System.out.println("-----------最后归类结果-----------");
        for (int i=0;i<num;i++){
            System.out.println("第"+(i+1)+"类为：");
            System.out.println(cluster.get(i));
        }
        WriteData.toFile(cluster,"C:/Users/Administrator/IdeaProjects/k-means/k-means.txt");



    }
}
