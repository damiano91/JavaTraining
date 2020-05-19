package LibBinaryTreeRec;

public class LibBinaryTreeRec {
    Node root;
    boolean found;

    private Node addRec(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        else if (value < current.value) {
            current.left = addRec(current.left, value);
        }
        else if (value > current.value) {
            current.right = addRec(current.right, value);
        }
        return current;
    }
    public void add(int value){
        root = addRec(root, value);
    }
    private boolean is_valueRec(Node current, int value){
        if(current == null){
            return false;
        }
        else if(value == current.value){
            return true;
        }
        else if(value < current.value && is_valueRec(current.left,value)){
            return true;
        }
        else if(value > current.value && is_valueRec(current.right, value)){
            return true;
        }
        return  false;
    }
    public boolean checkValue(int value){
        return is_valueRec(root, value);
    }
}
