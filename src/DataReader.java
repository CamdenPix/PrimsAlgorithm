import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class DataReader {
    private File file;
    private ArrayList<Edge> edges = new ArrayList<Edge>(10);
    private int numOfEdges;
    private int numOfVertices;
    public DataReader() throws FileNotFoundException {
        file = new File("src/graph");
        Scanner sc = new Scanner(file);

        numOfVertices = Integer.parseInt(sc.nextLine());
        numOfEdges = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] inputs = s.trim().split("\\s+");

            int v1 = Integer.parseInt(inputs[0]);
            int v2 = Integer.parseInt(inputs[1]);
            float weight = Float.parseFloat(inputs[2]);

            Edge e = new Edge(v1, v2, weight);
            edges.add(e);
            numOfEdges++;
        }
    }
    public ArrayList<Edge> getEdges(){
        return edges;
    }
    public int getNumOfEdges(){
        return numOfEdges;
    }
    public int getNumOfVertices(){
        return numOfVertices;
    }

    public static void Save(ArrayList<Edge> saveEdge){
        //TODO: SAVE FILE OF EDGES CHOSEN
    }
}
