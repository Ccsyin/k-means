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

        //从文件中读取点集合
        ArrayList<Point> points = new ArrayList<Point>();
        ArrayList<Point> pointsCopy = new ArrayList<Point>();
        ReadData read = new ReadData();
        String fileName="/Users/chenshiyin/IdeaProjects/k-means/points.txt";
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
        ArrayList<ArrayList<Point>> cluster = new ArrayList<ArrayList<Point>>();
        ArrayList<ArrayList<Point>> kCluster = new ArrayList<ArrayList<Point>>();
        cluster = k.initialCluster(points,num);
//        System.out.println(cluster);

//        for (int i=0;i<kCluster.size();i++){
//            System.out.println("第"+(i+1)+"类为：");
//            System.out.println(kCluster.get(i));
//        }


        ArrayList<Point> updateCenter = new ArrayList<Point>();
        ArrayList<ArrayList<Point>> updateCluster = new ArrayList<ArrayList<Point>>();

        for (int i=1;i<maxClusterTimes;i++){

            updateCenter = k.updateCenter(cluster,num);
            System.out.println("中心种子："+updateCenter);
                updateCluster=k.updateCluster(cluster,points,updateCenter);
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
        WriteDate.toFile(cluster,"/Users/chenshiyin/IdeaProjects/k-means/ccc.txt");

//        double x,y;
//        ArrayList<Point> q= new ArrayList<Point>();
//        Random rd = new Random();
//        for (int i = 0; i <10000 ; i++) {
//            Point a = new Point(rd.nextInt(100),rd.nextInt(100));
//            q.add(a);
//        }
//        WriteDate.toFile2(q,"/Users/chenshiyin/IdeaProjects/k-means/random.txt");

    }
}
