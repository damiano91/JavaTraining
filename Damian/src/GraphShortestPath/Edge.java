package GraphShortestPath;

public class Edge {
    Node source;
    Node destination;
    double cost;

    Edge(Node source, Node dest, double cost){
        this.source = source;
        this.destination = dest;
        this.cost = cost;
    }

    Node getNeighbourOf(Node node){
        if(node == source) return destination;
        else if(node == destination) return source;
        else return null;
    }
}
