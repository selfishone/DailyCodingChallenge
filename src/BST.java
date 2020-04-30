import javax.lang.model.util.Elements;

public class BST{
    Node root;

    public BST(Node n){
        this.root = n;
    }

    class Node{
        Node left;
        Node right;
        int value;

        public Node(int value){
            this.value= value;
            this.left = null;
            this.right = null;
        }
    }

    /*
        1. Start from root Node
        2. Compare value to be inserted with the current node value during traversal
        3. go left if value <= curr root value
        4. Move right if value> curr root value
        5. Insert when you wanna further move to left or right but you get a null
     */
    public void insert(int value){
        Node current = root;
        //Move to the left Subtree
        if(value < current.value){
            if(current.left == null){
                Node n = new Node(value);
                current.left = n;
            }else{
                BST b = new BST(current.left);
                b.insert(value);
            }
        }
        else{
        //If value of node being inserted >= current node move to right sub tree;
            if(current.right == null){
                Node n = new Node(value);
                current.right = n;
            }else{
                BST b = new BST(current.right);
                b.insert(value);
            }
        }
    }

    public boolean contains(int value){
        Node current = root;
        if(current == null)
            return false;
        else if(current.value == value)
            return true;
        else if(current.value<=value){
            //Value is probable in right sub tree
            if(current.right==null)
                return false;
            else{
                BST b = new BST(current.right);
                return b.contains(value);
            }
        }else{
            if(current.left == null)
                return  false;
            else{
                BST b = new BST(current.left);
                return  b.contains(value);
            }
        }
    }

    /*
        1. If there are duplicate values how should removal work
        2. Remove the first occurrences of the value ?
        3. Remove all the occurrences of the value ?
     */
    public BST remove(int value){
        remove(value, null);
        return this;
    }

    //Instead of swapping the nodes, it is better to swap the values and delete the unecessary nodes after the values swapped.
    private void remove(int value, BST parent) {
        if(value < root.value){
            if(root.left != null){
                BST leftTree = new BST(root.left);
                leftTree.remove(value, this);//this is child og parent BST
            }
        }else if( value > root.value){
            if(root.right != null){
                BST roghtTree = new BST(root.right);
                roghtTree.remove(value, this);
            }
        }else{
            //In case " this " root contains the value to be removed
            if(root.left!=null && root.right != null ){
                //In case it has both subtress left and right
                BST rightTree =new BST(root.right);
                this.root.value = rightTree.getMinimum();
                rightTree.remove(root.value, this);
            }else if( parent == null ){
                //In this case either of subtree is null as well
                //In case parent is null, we cant search using below else ifs so
                if(root.left != null ){
                    //replacing root with left trees valu <its lower>
                    this.root.value = root.left.value;
                    root.right = root.left.right;
                    root.left = root.left.left;
                }else if(root.right != null){
                    this.root.value = root.right.value;
                    root.left = root.right.left;
                    root.right = root.right.right;
                }
            }else if(parent.root.left == this.root){
            //Case: One of the subtrees are null <right sub tree null in this else if
                //If node to be removed is left child of its parent
                //If the node to be removed "this" has only one subtree
                //Find if its left or right and replace it as the child of the parent
                parent.root.left= root.left != null?root.left:root.right;
            }else if(parent.root.right == this.root){
                parent.root.right = root.left!= null?root.left:root.right;
            }
        }

    }

    private int getMinimum() {
        if(root.left==null){
           return root.value;
        }else{
            BST temp = new BST(root.left);
            return temp.getMinimum();
        }
    }

    /*
        Inorder Successor of a node is normally used for deletion of a BST
        1. It is next node of the given one in inorder traversal of the tree.
        2. Inorder Successor of an input node can also be defined as the node with the smallest key greater than the key of input node.
        3. We will replacce the node to be deleted with the inorder successor of the node.
        4. What happens if right subtree of the node is null?
        5. What happens if the left node of right subtree is null ?
     */
    public Node inorderSuccessor(Node n){
        Node temp;
        if(n.right != null){
            // smallest key>= input node
            temp = n.right;
            if(temp.left == null )
                return null;
            while(temp.left!=null && temp.left.value > n.value){
                temp=temp.left;
            }
            return temp;
        }
        else{
            temp =root;
            Node result = null;
            /*
                1. If right sbtree of node is NULL, then start from root and us search like technique.
                2. Travel down the tree, if a node’s data is greater than root’s data then go right side, otherwise go to left side
                3. Samllest key can be found by either going through the left sub tree of the BST
                4. Or if left is absent you can find the smallest of its parent nodes, to which this belongs to left sub tree
             */
            while(temp !=null){
                if(temp.value > n.value){
                    result = temp;
                    temp=temp.left;
                }else if(temp.value< n.value){
                    temp=temp.right;
                }else{
                    break;
                }
            }
            return result;
        }
    }

}
