package GraphShortestPath;

public class Node {
    Edge[] edges;
    int edgesCount;
    int value;
    int position;

    double cost;
    boolean settled;

    Node(int val, int pos){
        position = pos;
        value = val;
        edges = new Edge[2];
        edgesCount = 0;
        cost = 0;
        settled = false;
    }
    public void addEdge(Edge newEdge){
        if(edgesCount == edges.length){
            edges = extend(edges);
        }
        edges[edgesCount++] = newEdge;
    }

    private Edge[] extend(Edge[] table){
        Edge[] temp = new Edge[table.length * 2];
        for(int i =0; i< table.length; i++) temp[i] = table[i];
        return temp;
    }
}
