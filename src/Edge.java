public class Edge {
    private int vertex1;
    private int vertex2;
    private float weight;
    public Edge(int v1, int v2, float w){
        vertex1 = v1;
        vertex2 = v2;
        weight = w;
    }

    public int getVertex1(){
        return vertex1;
    }
    public int getVertex2(){
        return vertex2;
    }
    public float getWeight(){
        return weight;
    }
    public String toString(){
        return vertex1 + " " + vertex2 + " " + weight;
    }
}
