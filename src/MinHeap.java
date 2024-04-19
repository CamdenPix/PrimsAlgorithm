import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MinHeap {
    private Edge[] heapArray;
    private HashMap<Integer, Integer> idToIndexMap; // Map to store id to index mapping
    private int maxSize;
    private int currentSize;

    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        heapArray = new Edge[maxSize+1];
        idToIndexMap = new HashMap<>();
        currentSize = 0;
    }

    public void heap_ini(Edge[] keys, int n) {
        if (n > maxSize) {
            throw new IllegalArgumentException("Number of elements exceeds the maximum heap size.");
        }
        for (int i = 0; i < n; i++) {
            insert(keys[i]);
        }
    }

    public boolean in_heap(int id) {
        return idToIndexMap.containsKey(id);
    }

    public float min_key() {
        if (currentSize == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        return heapArray[1].getWeight();
    }

    public int min_id() {
        if (currentSize == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        return heapArray[1].getVertex1();
    }

    public ArrayList<Edge> findAllEdges(int vertex){
        ArrayList<Edge> connected = new ArrayList<>(3);
        for(Edge edge : heapArray){
            if(edge != null){
                if(edge.getVertex1() == vertex+1 || edge.getVertex2() == vertex+1){
                    connected.add(edge);
                }
            }
        }
        return connected;
    }

    public Edge findLowestEdge(ArrayList<Integer> vertices) {
        Edge minimum = null;
        int index = 0;
        for (int i = 1; i < heapArray.length; i++) {
            Edge edge = heapArray[i];
            if (edge != null && (vertices.contains(edge.getVertex1()) || vertices.contains(edge.getVertex2()))) {
                if (minimum == null || edge.getWeight() < minimum.getWeight()) {
                    minimum = edge;
                    index = i;
                }
            }
        }
        if (minimum != null) {
            delete(index); // Delete the found edge if it's not null
        }
        return minimum;
    }

    public float key(int id) {
        if (!in_heap(id)) {
            throw new IllegalArgumentException("Element with id " + id + " not found in heap.");
        }
        int index = idToIndexMap.get(id);
        return heapArray[index].getWeight();
    }

    public Edge delete_min() {
        if (currentSize == 0) {
            throw new IllegalStateException("Heap is empty.");
        }
        Edge minEdge = heapArray[1];
        delete(1);
        return minEdge;
    }


    public void decrease_key(int id, float newKey) {
        if (!in_heap(id)) {
            throw new IllegalArgumentException("Element with id " + id + " not found in heap.");
        }
        int index = idToIndexMap.get(id);
        float currentKey = heapArray[index].getWeight();
        if (newKey > currentKey) {
            throw new IllegalArgumentException("New key is greater than the current key.");
        }
        heapArray[index] = new Edge(heapArray[index].getVertex1(), heapArray[index].getVertex2(), newKey);
        bubble_up(index);
    }

    public Edge[] getHeapArray() {
        return Arrays.copyOfRange(heapArray, 1, currentSize + 1);
    }

    public int getCurrentSize(){
        return currentSize;
    }

    public void insert(Edge edge) {
        if (currentSize == maxSize) {
            throw new IllegalStateException("Heap is full, cannot insert more elements.");
        }
        currentSize++;
        int index = currentSize;
        heapArray[index] = edge;
        idToIndexMap.put(edge.getVertex1(), index);
        bubble_up(index);
    }

    public void delete(int index) {
        System.out.println(currentSize);
        Edge lastEdge = heapArray[currentSize];
        heapArray[index] = lastEdge;
        idToIndexMap.put(lastEdge.getVertex1(), index);
        idToIndexMap.remove(heapArray[currentSize].getVertex1());
        currentSize--;
        bubble_down(index);
    }

    private void bubble_up(int index) {
        while (index > 1 && heapArray[index].getWeight() < heapArray[index / 2].getWeight()) {
            swap(index, index / 2);
            index = index / 2;
        }
    }

    private void bubble_down(int index) {
        int smallestChild;
        while (index * 2 <= currentSize) {
            smallestChild = minChild(index);
            if (heapArray[index].getWeight() > heapArray[smallestChild].getWeight()) {
                swap(index, smallestChild);
                index = smallestChild;
            } else {
                break;
            }
        }
    }

    private int minChild(int index) {
        int leftChild = index * 2;
        int rightChild = leftChild + 1;
        if (rightChild > currentSize || heapArray[leftChild].getWeight() < heapArray[rightChild].getWeight()) {
            return leftChild;
        } else {
            return rightChild;
        }
    }

    private void swap(int i, int j) {
        Edge temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
        idToIndexMap.put(heapArray[i].getVertex1(), i);
        idToIndexMap.put(heapArray[j].getVertex1(), j);
    }
}