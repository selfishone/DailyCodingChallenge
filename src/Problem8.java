//This problem was asked by Google.
//A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
//Given the root to a binary tree, count the number of unival subtrees.

class Unival{

    public boolean isUnival;
    public Integer numUnivalSubtrees;

    public Unival(boolean b, int i) {
        this.isUnival=b;
        this.numUnivalSubtrees = i;
    }
}

public class Problem8 {
    //Even Integer is pass by Value not Reference same as primitive int so better use array Notation if you want to pass the referencce.
    static int total = 0;
    //This method is O(N*N) Complexity not optimized.
    public static Integer numUnivalSubtrees(Node root){
        if(isUnivalTree(root, root.value)){
            total+=1;
        }
        if(root.left!=null)
            numUnivalSubtrees(root.left);
        if(root.right!=null)
            numUnivalSubtrees(root.right);

        return total;
    }

    //This is an attempt to optimize the solution by traversing from leaves to top.
    public static Unival numberUnivalSubtrees2(Node root){
        //To solve this in a lower time complexity we iterate to the lowest leaf node and find our way back top.
        if(root== null){
            return new Unival(true,0);
        }
        Unival left = numberUnivalSubtrees2(root.left);
        Unival right = numberUnivalSubtrees2(root.right);
        Integer total = left.numUnivalSubtrees + right.numUnivalSubtrees;

        if(left.isUnival && right.isUnival){
            if(root.left!= null && root.value !=root.left.value){
                return new Unival(false, total);
            }
            if(root.right!= null && root.value !=root.right.value){
                return new Unival(false, total);
            }
            return new Unival(true, total+1);
        }
        return new Unival(false,total);
    }

    public static boolean isUnivalTree(Node root, Integer value){
        //Use Pair class to return multiple<2> values at the same time in java.
        return false;
    }

    public static void main(String args[]){

        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(0);
        root.left = node1;
        root.right = node2;
        Node node3 = new Node(1);
        Node node4 = new Node(0);
        node2.left = node3;
        node2.right = node4;
        Node node5 = new Node(1);
        Node node6 = new Node(1);
        node3.left = node5;
        node3.right = node6;

        System.out.println("Number of Universal Subtrees are "+ numberUnivalSubtrees2(root).numUnivalSubtrees);
    }
}
