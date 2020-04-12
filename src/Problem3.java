import java.util.*;

public class Problem3  {

    //Traversing the tree in pre order traversal to print it.
    public static void printPreOrder(Node root){
        System.out.println(Node.print(root));
        if(root!=null){
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    //Trying pre order traversal without any recursion.
    public static void preOrderIterative(Node root){
        /* The following steps need to be followed for pre order traversal without recursion
            1. Create a stack to maintain order to be printed
            2. Push root to the stack
            3. Complete the below steps iterating when stack not empty
            4. Post order always print Node first so pop the stack
            5. Push right child if present
            6. push left child if present [This will be popped next]
         */
        Stack<Node> s = new Stack();
        Node temp = root;
        s.push(temp);
        while(!s.empty()){
            Node n = s.pop();
            System.out.println(n.value);
            //Preorder is Node Left Right but we pushed right first because LIFO order of Stack
            if(n.right!=null)s.push(n.right);
            if(n.left!=null)s.push(n.left);
        }
    }

    //Logic for post order traversal is Left Root Right Printing
    // Traverse left then if left empty visit parent then Visit right
    public static void inOrderIterative(Node root){
        Stack<Node> s = new Stack();
        Node curr = root;
        while(curr!=null || !s.empty()){
            while(curr!=null){
                s.push(curr);
                curr = curr.left;
            }
            curr= s.pop();
            System.out.println(Node.print(curr));
            curr = curr.right;
        }
    }

    //Serializing the array into a List collection, you can do the same directly passing in a String instead of List
    public static void serializeToList(Node root, List<String> serializedList){
        serializedList.add(Node.print(root));
        if(root!=null){
            serializeToList(root.left, serializedList);
            serializeToList(root.right, serializedList);
        }
    }

    //This functions returns the root node reference to the new deserialized version of the tree
    public static Node deserialize(List<String> serializedList, int[] index){

        if( serializedList.get(index[0]).equals("#")){
            //index  = index +1;
            return null;
        }
        Node root = new Node(Integer.valueOf(serializedList.get(index[0])));
        index[0]+=1;
        root.left = deserialize(serializedList, index);
        index[0]+=1;
        root.right = deserialize(serializedList, index);
        return root;
    }

    //If you want to serialize just using the root variable you cant be using recursion because you need to maintain the queue
    //And thats why you need to use a while loop directly. if recursion a new Queue variable will be created for each recursive method call
    // not allowing us to maintain a single source of truth for the queue. [This is Inorder traversal]
    public static String serialize(Node root){
        if(root == null) return "#";
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        String serializedOutput = "";
        while(!q.isEmpty()){
            Node n =q.poll();
            //Since string class is immutable you need to reassign a=object reference each and every time there is a concat happening.
            serializedOutput = serializedOutput.concat(" "+Node.print(n));
            if(n!=null) {
                q.add(n.left);
                q.add(n.right);
            }
        }
        return serializedOutput;
    }


    public static void main(String[] args){
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        root.left = node1;
        root.right = node2;
        node1.left= node3;
        node1.right = node4;
        node2.left = null;
        node2.right = node5;
        node3.left = node6;
        node3.right= node7;

        printPreOrder(root);
        ArrayList<String> serializedList = new ArrayList<>();
        serializeToList(root, serializedList);
        //In java Arrays can be printed using Arrays.toString
        // but to print properly the Classes should have proper implementation of the toString() method
        System.out.println(serializedList);

        System.out.println(serialize(root));
        preOrderIterative(root);
        System.out.println("INORDERs");
        inOrderIterative(root);

        System.out.println("Serialized input: "+ serializedList);
        Node deserializedRoot = deserialize(serializedList, new int[]{0});
        System.out.println("Serializing deserialized tree: "+ serialize(deserializedRoot));
    }
}
