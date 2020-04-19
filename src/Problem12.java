//This problem was asked by Amazon.
//
//There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N,
//write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.
//
//For example, if N is 4, then there are 5 unique ways:
//
//1, 1, 1, 1
//2, 1, 1
//1, 2, 1
//1, 1, 2
//2, 2
//What if, instead of being able to climb 1 or 2 steps at a time,
//you could climb any number from a set of positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.

import java.util.Scanner;

public class Problem12 {

    //We have done it for step sizes =1,2 similarly we can do it for stepsixes of any ninteger for instance
    //
    public  static int numWaysToClimb(int N){
        int[] result = new int[N];
        //ith element of the result array will give us the number of ways to reach a i+1 th step of a staircase, taking 1 or 2 steps at a time
        result[0] = 1;
        result[1] = 2;

        for(int i = 2; i < N; i++){
            //ith step can be reached from either (i-2)th step or (i-1)th step
            result[i]= result[i-1]+result[i-2];
        }

        return result[N-1];
    }

    public static void main(String[] args){

        Scanner scr = new Scanner(System.in);
        System.out.println("Please enter the number of stairs: ");
        int n = scr.nextInt();
        System.out.println("Number of ways is: "+ numWaysToClimb(n));

    }
}
