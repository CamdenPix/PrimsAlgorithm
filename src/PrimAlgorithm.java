

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class PrimAlgorithm {
    private ArrayList<Edge> mstEdges;
    private boolean[] visited;
    private ArrayList<Integer> chosenVertices;
    private MinHeap minHeap;

    public PrimAlgorithm() throws IOException {
        DataReader dataReader = new DataReader();
        ArrayList<Edge> edges = dataReader.getEdges();
        int numOfEdges = dataReader.getNumOfEdges();

        this.mstEdges = new ArrayList<>();
        this.chosenVertices = new ArrayList<Integer>();
        this.visited = new boolean[dataReader.getNumOfVertices()]; // Assuming vertices are numbered from 1 to numOfEdges
        this.minHeap = new MinHeap(numOfEdges);

        // Initialize visited array
        for (int i = 0; i < dataReader.getNumOfVertices(); i++) {
            visited[i] = false;
        }

        // Add all edges to the minHeap and vertexToEdgesMap
        for (Edge edge : edges) {
            minHeap.insert(edge);
        }

        // Perform Prim's algorithm
        prim();
        DataReader.Save(mstEdges);
    }

    private boolean isEmpty(MinHeap minHeap) {
        return minHeap.getCurrentSize() == 0;
    }

    private void prim() {
        Edge minEdge = minHeap.delete_min();
        mstEdges.add(minEdge);
        visited[minEdge.getVertex1()-1] = true;
        chosenVertices.add(minEdge.getVertex1());
        visited[minEdge.getVertex2()-1] = true;
        chosenVertices.add(minEdge.getVertex2());
        int c = 0;
        int vertex = minEdge.getVertex2();
        while (!allVerticesVisited() && minHeap.getCurrentSize() != 0) {
            System.out.println("Iteration: " + c);
            //System.out.println(minEdge.toString());

            minEdge = minHeap.findLowestEdge(chosenVertices);
            if(visited[minEdge.getVertex1()-1] && visited[minEdge.getVertex2()-1]){
                System.out.println("Loop occurred at: " +minEdge.getVertex1() + " and " + minEdge.getVertex2());
            } else if (visited[minEdge.getVertex1()-1] && !visited[minEdge.getVertex2()-1]) {
                mstEdges.add(minEdge);
                visited[minEdge.getVertex2()-1] = true;
                vertex = minEdge.getVertex2();
                chosenVertices.add(minEdge.getVertex2());
            } else if (!visited[minEdge.getVertex1()-1] && visited[minEdge.getVertex2()-1]) {
                mstEdges.add(minEdge);
                visited[minEdge.getVertex1()-1] = true;
                vertex = minEdge.getVertex1();
                chosenVertices.add(minEdge.getVertex1());
            }
            else {
                System.out.println("ERROR");
                System.exit(-1);
            }
            System.out.println(chosenVertices.toString());
            c++;
        }
    }
    private boolean allVerticesVisited() {
        for (boolean vertexVisited : visited) {
            if (!vertexVisited) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Edge> getMSTEdges() {
        return mstEdges;
    }

    public static void main(String[] args) {
        try {
            PrimAlgorithm primMST = new PrimAlgorithm();
            ArrayList<Edge> mstEdges = primMST.getMSTEdges();
            System.out.println(mstEdges);
            System.out.println("Minimum Spanning Tree Edges:");
            for (Edge edge : mstEdges) {
                System.out.println(edge);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
