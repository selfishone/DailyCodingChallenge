//Longest Continuous subArray Sum in an Array.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KadanesAlgorithm {

    public static Integer kadanesSum(List<Integer> input){
        int size = input.size();

        int largest_sum = Integer.MIN_VALUE;
        int current_sum = 0;
        int start= 0;
        int end = 0;

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
