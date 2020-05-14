package LibBinaryTree;

public class LibBinaryTree {
    Node root;

    private Node addRec(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRec(current.left, value);
        } else if (value > current.value) {
            current.right = addRec(current.right, value);
        } else {
            return current;
        }
        return current;
    }
    public void add(int value){
        root = addRec(root, value);
    }
    public boolean is_valueRec(Node current, int value){
        if(current == null){
            return false;
        }
        return  true;
    }
}
