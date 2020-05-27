package Graph;

public class Node {
    Node[] parents;
    Node[] childs;
    int parentsCount, childsCount;
    int position;
    int value;

    Node(int val, int pos){
        position = pos;
        value = val;
        parentsCount = childsCount =0;
        parents = new Node[2];
        childs = new Node[2];
    }
    public void setChild(Node childNode){
        if(childsCount == childs.length){
            childs = extend(childs);
        }
        childs[childsCount++] = childNode;
    }

    public void setParent(Node parentNode){
        if(parentsCount == parents.length){
            parents = extend(parents);
        }
        parents[parentsCount++] = parentNode;
    }

    public static Node[] extend(Node[] table){
        Node[] temp = new Node[table.length * 2];
        for(int i =0; i<table.length; i++) temp[i] = table[i];
        return temp;
    }

}
