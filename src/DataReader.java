import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class DataReader {
    private File file;
    private ArrayList<Edge> edges = new ArrayList<Edge>(10);
    private int numOfEdges;
    public DataReader() throws FileNotFoundException {
        file = new File("src/graph");
        Scanner sc = new Scanner(file);

        numOfEdges = Integer.parseInt(sc.nextLine());
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] inputs = s.trim().split("\\s+");

            int v1 = Integer.parseInt(inputs[0]);
            int v2 = Integer.parseInt(inputs[1]);
            float weight = Float.parseFloat(inputs[2]);

            Edge e = new Edge(v1, v2, weight);
            edges.add(e);
        }
    }
    public ArrayList<Edge> getEdges(){
        return edges;
    }
    public int getNumOfEdges(){
        return numOfEdges;
    }

    public void Save(ArrayList<Edge> saveEdge){
        //TODO: SAVE FILE OF EDGES CHOSEN
    }
}
