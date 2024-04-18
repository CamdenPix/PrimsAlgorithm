public class Edge {
    private int edge1;
    private int edge2;
    private float weight;
    public Edge(int e1, int e2, float w){
        edge1 = e1;
        edge2 = e2;
        weight = w;
    }

    public int getEdge1(){
        return edge1;
    }
    public int getEdge2(){
        return edge2;
    }
    public float getWeight(){
        return weight;
    }
    public String toString(){
        return edge1 + " " + edge2 + " " + weight;
    }
}
