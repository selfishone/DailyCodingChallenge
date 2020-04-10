//  Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
//
//  For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
//
//  Follow-up: what if you can't use division?

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem2 {


    private static int computeProduct(List<Integer> numbers){
        int product = 1;
        for(Integer i : numbers){
            product = product*i;
        }
        return product;
    }

    //This method is O(N) complexity
    //This is the most optimal solution.
    private static List<Integer> newArray(List<Integer> numbers){
        List<Integer> result = new ArrayList<>();
        Integer product = computeProduct(numbers);
        for(Integer i : numbers){
            result.add(product/i);
        }
        return result;
    }

    //This method is for doing it without using divison.
    //This method is of O(N) time complexity and of O(N) space complexity.
    //Process: Without using divison we can multiply prefix and suffix at each location of the array to find the product for that position.
    private static List<Integer> newArray2(List<Integer> numbers){
        List<Integer> prefixList = new ArrayList<>();
        //Way to initialize an array of a certain size.
        List<Integer> suffixList = new ArrayList(Arrays.asList(new Integer[numbers.size()]));
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i<numbers.size(); i++){
            if(i==0)prefixList.add(1);
            else {
                prefixList.add(numbers.get(i-1)*prefixList.get(i-1));
            }
        }
        for(int j = numbers.size()-1 ; j>=0; j--){
            if(j==numbers.size()-1){
                suffixList.set(numbers.size()-1,1);
            }else{
                suffixList.set(j,numbers.get(j+1)*suffixList.get(j+1));
            }
        }

        for(int k =0; k<numbers.size(); k++){
            result.add(suffixList.get(k)*prefixList.get(k));
        }

        return result;
    }

    public static void main(String[] args){

        List<Integer> numbers = new ArrayList<>();
        Scanner scr = new Scanner(System.in);

        System.out.println("Please enter a list of Integers");
        String[] numbersStringArr =  scr.nextLine().split(",") ;
        for(String i : numbersStringArr){
            numbers.add(Integer.parseInt(i));
        }

        scr.close();

        System.out.println("The new array is: "+ newArray(numbers).toString());
        System.out.println("The new array is: "+ newArray2(numbers).toString());
    }
}



