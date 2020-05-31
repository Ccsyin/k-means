import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadData {
    //从文件中读取数据
    public ArrayList<Point> read(String fileName){
        ArrayList<Point> points = new ArrayList<Point>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line=null;
            double x,y;
            while ((line=reader.readLine()) != null){
                x=Double.valueOf(line.split(",")[0]);
                y=Double.valueOf(line.split(",")[1]);
                Point point = new Point(x,y);
                points.add(point);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public int sum(String fileName){
        int temp = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line=null;
            while ((line=reader.readLine()) != null){
                temp++;
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
