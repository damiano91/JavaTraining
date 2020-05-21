package DictionaryTree;

public class Node {
    public String eng;
    public Node[] nextChar;

    Node(int size){
        this.eng = null;
        nextChar = new Node[size];
    }
}
