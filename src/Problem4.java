//This problem was asked by Stripe.
//Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.
//For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Find lowest positive integer that doesnt exist in the Array.
public class Problem4 {

    // My first Idea find the smallest number and largest in the array and maintain a list of which one of them are present in the array
    // The space complexity would be higher for this since we are maintaing a whole separate array. Can we reuse the same array by modifying it ?

    public static Integer findMissing(List<Integer> input){
        int size = input.size();
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;
        int result  = -1;

        for(int i = 0 ; i< size; i++){
            if(input.get(i)< smallest && input.get(i)>0){
                smallest = input.get(i);
            }else if(input.get(i)>largest && input.get(i)>0){
                largest = input.get(i);
            }
        }

        int sizePositives = largest-smallest+1;
        int[] positivesArray = new int[sizePositives];
        for(int i = 0 ; i< size; i++){
            if(input.get(i)>0){
                positivesArray[input.get(i)-smallest]=1;
            }
        }

        for(int i = 0 ; i< positivesArray.length;i++){
            if(positivesArray[i]==0){
                result = smallest+i;
            }
        }
        if(result == -1 ){
            return largest+1;
        }
        return result;
    }

    public static void swap(List<Integer> input, int indexFrom, int indexTo){
        int temp = input.get(indexTo);
        input.set(indexTo,input.get(indexFrom));
        input.set(indexFrom, temp);
    }
    //2nd Method trying with linear space complexity.
    // We can instead use swap to reorder the elements of array in same array.
    //We need to find the first missing positive number so it will be between 1 and len(array) +1
    public static Integer findMissing2(List<Integer> input){
      for(int i=0; i< input.size(); i++){
          while(input.get(i)>0 && input.get(i)<=input.size() && !input.get(i).equals(i+1)){
              swap(input, i, input.get(i)-1);
          }
      }
      System.out.println(input);
      for(int i = 0; i < input.size(); i++){
          if(input.get(i)!=i+1){
              return i+1;
          }
      }
      return input.size()+1;
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

        System.out.println("The missing positive number is: "+findMissing2(numbers));
    }
}
