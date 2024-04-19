

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class PrimAlgorithm {
    private ArrayList<Edge> mstEdges;
    private boolean[] visited;
    private MinHeap minHeap;

    public PrimAlgorithm() throws FileNotFoundException {
        DataReader dataReader = new DataReader();
        ArrayList<Edge> edges = dataReader.getEdges();

        int numOfEdges = dataReader.getNumOfEdges();


        this.mstEdges = new ArrayList<>();
        this.visited = new boolean[numOfEdges + 1]; // Assuming vertices are numbered from 1 to numOfEdges
        this.minHeap = new MinHeap(numOfEdges);

        // Initialize visited array
        for (int i = 1; i <= numOfEdges; i++) {
            visited[i] = false;
        }

        // Add all edges to the minHeap
        minHeap.heap_ini(edges.toArray(new Edge[0]), numOfEdges);
        System.out.println(minHeap.min_key());
        // Perform Prim's algorithm
        prim();
    }

    private boolean isEmpty(MinHeap minHeap){
        try{
            minHeap.min_key();
            System.out.println("TRY PASSED");
        }
        catch(Exception e)
        {
            return true;
        }
        return false;
    }
    private void prim() {
        while (!isEmpty(minHeap)) {
            System.out.println("INWHILELOOPP");
            Edge minEdge = minHeap.delete_min();
            int v1 = minEdge.getVertex1();
            int v2 = minEdge.getVertex2();

            if (!visited[v1] || !visited[v2]) {
                mstEdges.add(minEdge);
                visited[v1] = true;
                visited[v2] = true;
                processNeighbours(v1);
                processNeighbours(v2);
            }
        }
    }

    private void processNeighbours(int vertex) {
        // Iterate through all edges adjacent to the given vertex
        // and decrease their keys in the heap if necessary
        // (only if the adjacent vertex is not visited)
        /*for (Edge edge : mstEdges) {
            int otherVertex;
            if (edge.getVertex1() == vertex) {
                otherVertex = edge.getVertex2();
            } else if (edge.getVertex2() == vertex) {
                otherVertex = edge.getVertex1();
            } else {
                continue; // Not adjacent
            }

            if (!visited[otherVertex]) {
                float newKey = edge.getWeight();

                    if (minHeap.key(otherVertex) > newKey) {
                        minHeap.decrease_key(otherVertex, newKey);
                    }

            }
        }*/

        for (Edge edge : minHeap.getHeapArray()) {
            int v1 = edge.getVertex1();
            int v2 = edge.getVertex2();

            if ((v1 == vertex && !visited[v2]) || (v2 == vertex && !visited[v1])) {
                int otherVertex = (v1 == vertex) ? v2 : v1;
                if (minHeap.in_heap(otherVertex)) {
                    float newKey = edge.getWeight();
                    if (minHeap.key(otherVertex) > newKey) {
                        minHeap.decrease_key(otherVertex, newKey);
                    }
                }
            }
        }


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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
