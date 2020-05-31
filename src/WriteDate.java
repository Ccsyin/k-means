import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class WriteDate {

    public static void toFile(ArrayList<ArrayList<Point>> cluster, String file) {
        try{
            File file1 = new File(file);
            FileOutputStream fos = new FileOutputStream(file1);
            OutputStreamWriter dos = new OutputStreamWriter(fos);
            StringBuffer write = new StringBuffer();

            for (ArrayList<Point> points : cluster) {
                for (Point point : points) {
                    write.append(point.getX())
                            .append(",")
                            .append(point.getY()+" ");

                }
                write.append("\r\n");
            }
            dos.write(write.toString());
            dos.close();
        }catch (Exception e){

        }
    }


    public static void toFile2(ArrayList<Point> q, String file) {
        try{
            File file1 = new File(file);
            FileOutputStream fos = new FileOutputStream(file1);
            OutputStreamWriter dos = new OutputStreamWriter(fos);
            StringBuffer write = new StringBuffer();

            for (int i = 0; i <q.size() ; i++) {
                write.append(q.get(i).getX())
                        .append(",")
                        .append(q.get(i).getY()+" ");
            }
                write.append("\n");
            dos.write(write.toString());
            dos.close();
        }catch (Exception e){

        }
    }

}
