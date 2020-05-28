package GraphShortestPath;

public class GraphShortestPath {
    Node[] nodes;
    int nodesCount;

    GraphShortestPath(){
        nodes = new Node[2];
        nodesCount = 0;
    }


    public void addEdge(int sourceVal, int destVal, double cost){
        Node n1 = findNode(sourceVal);
        Node n2 = findNode(destVal);
        if(n1 == null) n1 = addNode(sourceVal);
        if(n2 == null) n2 = addNode(destVal);
        Edge edge = new Edge(n1, n2, cost);
        n2.addEdge(edge);
        n1.addEdge(edge);
    }
    private Node addNode(int nodeVal){
        if(nodesCount == nodes.length){
            nodes = extend(nodes);
        }
        nodes[nodesCount] = new Node(nodeVal);
        return nodes[nodesCount++];
    }
    public static Node[] extend(Node[] table){
        Node[] temp = new Node[table.length * 2];
        for(int i =0; i<table.length; i++) temp[i] = table[i];
        return temp;

    }

    private Node findNode(int val){
        for(int i =0; i<nodesCount; i++){
            if(nodes[i].value == val) return nodes[i];
        }
        return null;
    }

    public double get_cost(int sourceNodeVal, int destNodeVal){
        Queue queue = new Queue();
        Node sourceNode = findNode(sourceNodeVal);
        Node destNode = findNode(destNodeVal);
        if(sourceNode == null || destNode == null){
            System.out.println("No node in Graph");
            return -1;
        }
        setUpNodes(sourceNode);
        queue.put(sourceNode);
        while (queue.qSize !=0){
            sourceNode = queue.getFirst();
            if(sourceNode == destNode) break; //when destination node reached, no need to evaluate cost for other nodes
            checkNeighbours(sourceNode, queue);
            sourceNode.settled = true;
        }


        return destNode.cost;
    }
    private void setUpNodes(Node sourceNode){
        for(int i =0; i<nodesCount; i++){
            nodes[i].cost = Integer.MAX_VALUE;
            nodes[i].settled = false;
        }
        sourceNode.cost = 0;
    }

    private void checkNeighbours(Node node, Queue queue){
        Node temp;
        for(int i =0; i< node.edgesCount; i++){
            temp = node.edges[i].getNeighbourOf(node);
            //if(temp.settled) continue;            //queue modified, settled nodes should not exist in queue
            if(temp.cost > node.cost + node.edges[i].cost){
                temp.cost = node.cost + node.edges[i].cost;
                queue.put(temp);
            }
        }
    }
}
