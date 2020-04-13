public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public static String print(Node n){
        if(n != null) return Integer.toString(n.value);
        else return "#";
    }
}
