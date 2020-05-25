package Graph;

public class Graph {
    Node[] nodes;

    public void addEdge(int parent, int child){
        addNodeIfNotExists(parent);
        addNodeIfNotExists(child);
        Node pointerChild = findNode(child);
        Node pointerParent = findNode(parent);
        pointerChild.setParent(pointerParent);
        pointerParent.setChild(pointerChild);
    }

    private void addNodeIfNotExists(int val){
        if(nodes == null){
            nodes = new Node[1];
            nodes[0] = new Node(val, 0);
        }
        if(findNode(val) == null){
            Node[] temp = new Node[nodes.length+1];
            for(int i =0; i<nodes.length; i++) temp[i] = nodes[i];
            temp[nodes.length] = new Node(val, nodes.length);
            nodes = temp;
        }
    }

    private Node findNode(int val){
        for(int i =0; i<nodes.length; i++){
            if(nodes[i].value == val) return nodes[i];
        }
        return null;
    }

    public int[] getIslands(Node startNode){
        boolean[] visted = new boolean[nodes.length];
        int qIterator = 0, qSize=0;
        Node[] queue = new Node[nodes.length];
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
        for (int i = 0; i < current.parents.length; i++) {
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
        for(int i = 0; i<current.childs.length; i++){
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
