//Longest Continuous subArray Sum in an Array.


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ALGORITHM:
// Initialize:
//        max_so_far = 0
//        max_ending_here = 0
//
//        Loop for each element of the array
//        (a) max_ending_here = max_ending_here + a[i]
//        (b) if(max_ending_here < 0)
//        max_ending_here = 0
//        (c) if(max_so_far < max_ending_here)
//        max_so_far = max_ending_here
//        return max_so_far
public class KadanesAlgorithm {

    // Positive contiguous Sum of the array is considered wont work with array with all negative numbers
    public static Integer kadanesSum(List<Integer> input){
        int size = input.size();
        int largest_sum = Integer.MIN_VALUE;
        int current_sum = 0;

        for(int i=0; i< size; i++){

            current_sum+=input.get(i);
            if(current_sum<0){
                current_sum = 0;
            }
            if(current_sum>largest_sum){
                largest_sum = current_sum;
            }
        }
        return largest_sum;
    }

    //This implementation will work with negative arrays as well.
    public static Integer kadanesSum2(List<Integer> input){
        int size = input.size();
        int largest_sum = input.get(0);
        int current_sum = input.get(0);

        for(int i=1; i< size; i++){
            current_sum = Math.max(input.get(i), input.get(i)+current_sum);
            largest_sum = Math.max(current_sum,largest_sum);
        }
        return largest_sum;
    }

    public static void main(String args[]){

        List<Integer> numbers = new ArrayList<>();
        Scanner scr = new Scanner(System.in);

        System.out.println("Please enter a list of Integers");
        String[] numbersStringArr =  scr.nextLine().split(",") ;
        for(String i : numbersStringArr){
            numbers.add(Integer.parseInt(i));
        }

        scr.close();

        System.out.println("Kadanes sum for the input array is: "+kadanesSum(numbers));

    }
}
