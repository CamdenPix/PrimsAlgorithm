import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class DataReader {
    File file;
    ArrayList<Edge> edges = new ArrayList<Edge>(10);
    int numOfEdges;
    public DataReader() throws FileNotFoundException {
        file = new File("file:src/graph");
        Scanner sc = new Scanner(file);

        numOfEdges = sc.nextInt();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] inputs = s.split(" ");

            int e1 = Integer.parseInt(inputs[0]);
            int e2 = Integer.parseInt(inputs[1]);
            float weight = Float.parseFloat(inputs[2]);

            Edge e = new Edge(e1, e2, weight);
            edges.add(e);
        }
    }
}
