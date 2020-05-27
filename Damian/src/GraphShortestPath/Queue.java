package GraphShortestPath;

public class Queue {
    Node[] nodes;
    int qSize;
    int qIterator;

    Queue(){
        nodes = new Node[2];
        qSize = 0;
    }
    void put(Node node){
        if(qSize == nodes.length){
            nodes = GraphShortestPath.extend(nodes);
        }
        if(nodes[0] == null) nodes[0] = node;
        else{
            int qIndex = findHigherCostNode(node.cost);
            System.arraycopy(nodes,qIndex,nodes,qIndex+1,qSize-qIndex);
            nodes[qIndex] = node;
        }
        qSize++;
    }

    Node getFirst(){
        Node temp = nodes[0];
        removeFirstElement();
        return temp;
    }

    void removeFirstElement(){
        if(nodes[0] == null)return;
        System.arraycopy(nodes,1,nodes,0,--qSize);
    }

    int findHigherCostNode(double nodeCost){
        for(int i =0; i<qSize; i++){
            if(nodeCost < nodes[i].cost) return i;
        }
        return qSize;
    }
}
