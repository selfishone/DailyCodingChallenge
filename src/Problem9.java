import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//This problem was asked by Airbnb.
//
//Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.
//
//For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.
public class Problem9 {

    //This method is O(2pow(N)) complexity because each recursive call in turn calls 2 calls.
    public static Integer findLargestNonAdjacentSum1(List<Integer> numbers){

        if(numbers==null || numbers.size()<=0)
            return 0;
        if(numbers.size() > 2)
            return Math.max(findLargestNonAdjacentSum1(numbers.subList(2,numbers.size()))+numbers.get(0),findLargestNonAdjacentSum1(numbers.subList(1,numbers.size())));
        else if(numbers.size()==2)
            return Math.max(numbers.get(0),numbers.get(1));
        else
            return numbers.get(0);
    }

    //We will try using Dynamic Programming for this to reduce time complexity.
    //This O(N) time complexity and O(N) space complexity we used tabulation for Dynamic programming here we can further reduce the space complexity is we use Memorization.
    public static Integer findLargestNonAdjacentSum2(List<Integer> numbers){

        if(numbers.size()<=2){
            return Math.max(0, Collections.max(numbers));
        }
        int[] cache = new int[numbers.size()];
        //cache[i] gives the value of the max non adjacent sum in the sub array from <i to numbers.size()> i, length included
        cache[numbers.size()-1]= numbers.get(numbers.size()-1);
        cache[numbers.size()-2]= Math.max(cache[numbers.size()-1],numbers.get(numbers.size()-2));
        for(int i = numbers.size()-3 ; i>=0; i--){
            cache[i]= Math.max(cache[i+1],cache[i+2]+numbers.get(i));
        }
        return cache[0];
    }

    //This is Solution with O(N) time complexity and O(K) space complexity achieved using Dynamic Programming
    public static Integer findLargestNonAdjacentSum3(List<Integer> numbers){

        if(numbers.size()<=2){
            return Math.max(0, Collections.max(numbers));
        }
        int[] cache = new int[2];
        cache[1]= numbers.get(numbers.size()-1);
        cache[0]= Math.max(cache[1],numbers.get(numbers.size()-2));
        for(int i = numbers.size()-3 ; i>=0; i--){
            int temp = cache[0];
            cache[0]= Math.max(cache[0],cache[1]+numbers.get(i));
            cache[1]= temp;
        }
        return cache[0];
    }

    public static void main(String args[]){
        List<Integer> numbers = new ArrayList<>();
        Scanner scr = new Scanner(System.in);

        System.out.println("Please enter a list of Integers");
        String[] numbersStringArr =  scr.nextLine().split(",") ;
        for(String i : numbersStringArr){
            numbers.add(Integer.parseInt(i));
        }
        System.out.println("The size of the input array is: "+numbers.size());
        System.out.println("The largest sum of non adjacent elements is: "+ findLargestNonAdjacentSum3(numbers));
    }
}
