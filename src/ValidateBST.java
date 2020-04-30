public class ValidateBST {

    static class BST{
        BST left;
        BST right;
        int value;

        public BST(int value){
            this.value = value;
        }
    }

    // This is in correct logic it doesnt work int the case where left subtrees right most  leaf node > root node
    public static boolean validateBST(BST tree){
        boolean current, left, right;
        if(tree == null )
            return true;

        left = validateBST(tree.left);

        right = validateBST(tree.right);

        //This half of the logic must be modified to check if the root node is greater then the greatest number in the left sub tree
        // and less than the least number in the right sub tree
        if(tree.left!=null && tree.right != null)
            current = (tree.left.value<tree.value)&&(tree.right.value>=tree.value);
        else if(tree.left == null && tree.right == null)
            current =true;
        else if(tree.left== null && tree.right!= null)
            current = (tree.right.value>=tree.value);
        else
            current = (tree.left.value<tree.value);
        return  current && left && right;
    }


    public static boolean validateBST1(BST tree){
        return validateBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean validateBST(BST tree, int minValue, int maxValue) {
        // min value and max value are the bounds between which current value of the node must lie
        if(tree.value<minValue || tree.value>=maxValue)
            return false;
        if(tree.left!=null && !validateBST(tree.left, minValue, tree.value))
            return false;
        if(tree.right != null && !validateBST(tree.right, tree.value, maxValue))
            return false;

        return  true;
    }

}
