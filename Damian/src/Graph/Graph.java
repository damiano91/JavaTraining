package Graph;

import java.util.List;

public class Graph {
    Node[] nodes;
    int nodesCount;

    Graph(){
        nodes = new Node[2];
        nodesCount = 0;
    }

    public void addEdge(int parent, int child){
        Node n1 = findNode(parent);
        Node n2 = findNode(child);
        if(n1 == null) n1 = addNode(parent);
        if(n2 == null) n2 = addNode(child);
        n2.setParent(n1);
        n1.setChild(n2);
    }
    private Node addNode(int nodeVal){
        if(nodesCount == nodes.length){
            nodes = Node.extend(nodes);
        }
        nodes[nodesCount] = new Node(nodeVal, nodesCount);
        return nodes[nodesCount++];
    }

    private Node findNode(int val){
        for(int i =0; i<nodesCount; i++){
            if(nodes[i].value == val) return nodes[i];
        }
        return null;
    }

    private int[] getIslands(Node startNode){
        boolean[] visted = new boolean[nodesCount];
        int qIterator = 0, qSize=0;
        Node[] queue = new Node[nodesCount];
        queue[qSize++] = startNode;
        visted[startNode.position] = true;
        while(qIterator < qSize){
            qSize = searchUpwards(queue,qIterator, qSize, visted);
            qSize = searchBackwards(queue, qIterator, qSize, visted);
            qIterator++;
        }
        int count=0;
        for(int i=0; i < visted.length; i++){
            if(!visted[i]) count++;
        }
        int[] nodeValues = new int[count];
        for(int i=0; i < visted.length; i++){
            if(!visted[i]) nodeValues[--count] = nodes[i].value;
        }
        return nodeValues;
    }

    public void printNodesNotConnectedWith(int startNodeVal){
        Node startNode = findNode(startNodeVal);
        if(startNode == null) {
            System.out.println("No node in Graph");
            return;
        }
        int[] nodeValues = getIslands(startNode);
        for(int i = 0; i<nodeValues.length; i++) System.out.print(nodeValues[i] + " ");
        System.out.println();
    }

    private int searchUpwards(Node[] queue,int qIterator, int qSize, boolean[] visited){
        Node current = queue[qIterator];
        if(current.parents == null) return qSize;
        for (int i = 0; i < current.parentsCount; i++) {
            if (visited[current.parents[i].position]) continue;
            else {
                visited[current.parents[i].position] = true;
                queue[qSize] = current.parents[i];
                qSize++;
            }
        }
        return qSize;
    }

    private int searchBackwards(Node[] queue, int qIterator, int qSize, boolean[] visited){
        Node current = queue[qIterator];
        if(current.childs == null) return qSize;
        for(int i = 0; i<current.childsCount; i++){
            if(visited[current.childs[i].position])continue;
            else{
                visited[current.childs[i].position] = true;
                queue[qSize] = current.childs[i];
                qSize++;
            }
        }
        return qSize;

    }
}
