package GraphShortestPath;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Queue {
    Node[] nodes;
    int qSize;

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
            for(int i = qIndex+1; i<qSize; i++){
                if(nodes[i] == node) removeNode(i);
            }
        }
        qSize++;
    }

    Node getFirst(){
        Node temp = nodes[0];
        removeNode(0);
        return temp;
    }
    void removeNode(int index){
        if(index == qSize) nodes[index] = null;
        else{
            System.arraycopy(nodes,index+1,nodes,index,qSize-index);
            nodes[qSize] = null;
            qSize--;
        }

    }

    int findHigherCostNode(double nodeCost){
        for(int i =0; i<qSize; i++){
            if(nodeCost < nodes[i].cost) return i;
        }
        return qSize;
    }
}
