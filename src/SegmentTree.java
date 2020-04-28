import java.util.Arrays;

//Time complexity to find maximum of a given subArray

public class SegmentTree {
   int[] tree;
   //What does i-th element of this segment tree tell us: Nothing, we dont know what is the left and right based on index in the array
    // The only way to know is traversing from root of the tree, we kknow that i=0 is the root of segment tree.
    // TODO: instead of tree what willl happen if we try out a custom data structure ?

    public SegmentTree(int[] arr){
        int arr_size = arr.length;
        int height = (int)Math.ceil(Math.log(arr_size)/Math.log(2));
        int numNodes = 2*(int)Math.pow(2,height)-1;
        tree = new int[numNodes];
        constructHelper(arr, 0, arr_size-1, 0);
        //The last few nodes are dummy nodes, nodes from (2*numNodes-arr_size) to 2*numNodes are dummy nodes.

    }

    private void constructHelper(int[] arr, int left, int right, int curr){
        // left and right are the bounds of the node and curr is the current index in the tree array.
        // left and right are subaarray indexes in input arreay
        if(left == right){
            tree[curr] = arr[left];
            return;
        }else{
            int mid = (left+right)/2;
            constructHelper(arr, left, mid, 2*curr+1);
            constructHelper(arr, mid+1, right, 2*curr+2);
        }
        tree[curr]= Math.max(tree[2*curr+1],tree[2*curr+2]);

    }

    public int getMaximum(int left, int right, int arr_size){
        // 0<=l<=r<=size(arr)
        return maxHelper(left, right, 0, 0, arr_size-1);
    }

    private int maxHelper(int left, int right, int current, int left_curr, int right_curr){
        int mid = (left_curr + right_curr)/2;
        int left_Max = Integer.MIN_VALUE;
        int right_Max = Integer.MIN_VALUE;

        if(left <= left_curr && right >= right_curr)
            return tree[current];

        if(isIntersected(left, right, left_curr, mid))
            if(left_curr< right_curr)
            left_Max = maxHelper(left, right,(2*current)+1, left_curr, mid);
        if(isIntersected(left, right, mid, right_curr))
            if(left_curr< right_curr)
            right_Max = maxHelper(left, right,(2*current)+2, mid+1, right_curr);

        return Math.max(left_Max,right_Max);
    }

    private boolean isIntersected(int left, int right, int tree_left, int tree_right){
        if(left<tree_left && right<tree_left)
            return false;
        if(right> tree_right && left> tree_right)
            return false;
        return true;
    }
    public static void main(String args[]){
        int[] arr = {1,3,5,7,9,11};
        SegmentTree st = new SegmentTree(arr);

        System.out.println(Arrays.toString(st.tree));
        System.out.println(st.getMaximum(1,4,6));
    }

}
