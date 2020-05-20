package LibBinaryTreeLoop;


public class LibBinaryTreeLoop {
    Node root;

    public void add(int value) {
        if(root == null){
            root = new Node(value);
            return;
        }
        Node pointer = root;
        while(true){
            if(value < pointer.value){
                if(pointer.left == null){
                    pointer.left = new Node(value);
                    return;
                };
                pointer = pointer.left;
            }
            else if(value > pointer.value){
                if(pointer.right == null){
                    pointer.right = new Node(value);
                    return;
                }
                pointer = pointer.right;
            }
            else return;
        }
    }
    public boolean checkValue(int value){
        Node pointer = root;
        while(pointer != null){
            if(value < pointer.value){
                pointer = pointer.left;
            }
            else if(value > pointer.value){
                pointer = pointer.right;
            }
            else return true;
        }
        return false;
    }
}
