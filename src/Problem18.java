//This problem was asked by Google.
//Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.
//For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8],

import java.util.*;

//We use a dequeue to solve this problem: Burning candles at both ends problem.
// TODO: https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
//Every element is added and removed atmost once in this method so time complexity is O(N)
public class Problem18 {

    public static void findMax(int[] arr, int k){
        //No need to store the max of each subarray in list and then output it, you can directly print out the value, return type will be void
        // here k is the window size. [TODO: Question to ask is should i store the vakues or can i print them out ? for space complexity]
        //We need to have all the k-1 elements other than first in the last subarray if its largest is its root if we want to find maximum of next sub array
        Deque<Integer> deq = new LinkedList<>();
        //Dequeue stores indexes of elements in Array
        for(int i =0; i < arr.length; i++){
            if(deq.isEmpty()){
                deq.addFirst(i);
            }
            else{
                //Always use iterations while traversing a collection and modifying it. Safe way to do it.
//               Iterator<Integer> deqIter = deq.iterator();
//                while(deqIter.hasNext() && arr[deqIter.next()] < arr[i] )
//                    deqIter.remove();
                while(!deq.isEmpty() && arr[deq.getLast()] < arr[i] )
                    deq.removeLast();
                //Why not insert ar front directly instead of removing ftom back ? cant we just empty and insert ar front if new element > front?
                deq.addLast(i);
                if(deq.getFirst()< i-k+1)
                    deq.removeFirst();
            }
            if(i>=k-1){
                //print front of queue
                System.out.println(arr[deq.getFirst()]);
            }
        }

    }
    public static void main(String args[]){
        int[] arr = {11,10,9,8,7,6};
        findMax(arr, 5);
    }
}
