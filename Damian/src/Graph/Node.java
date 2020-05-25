package Graph;

public class Node {
    Node[] parents;
    Node[] childs;
    int position;
    int value;

    Node(int val, int pos){
        position = pos;
        value = val;
    }
    public void setChild(Node childNode){
        if(childs == null){
            childs = new Node[1];
            childs[0] = childNode;
        }
        else{
            Node[] temp = new Node[childs.length+1];
            for(int i =0; i<childs.length; i++) temp[i] = childs[i];
            temp[childs.length] = childNode;
            childs = temp;
        }
    }
    public void setParent(Node parentNode){
        if(parents == null){
            parents = new Node[1];
            parents[0] = parentNode;
        }
        else{
            Node[] temp = new Node[parents.length+1];
            for(int i =0; i<parents.length; i++) temp[i] = parents[i];
            temp[parents.length] = parentNode;
            parents = temp;
        }

    }

}
