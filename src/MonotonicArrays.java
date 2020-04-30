import java.util.Scanner;

public class MonotonicArrays {
    /*
        1. Elements are entirely non increasing or non-decreasing in a monotoonic array
        e.g: [-1 -5 -10 -1100 -1100 -1101 -1102 -9001] true
     */
    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        String[] line1 = s.nextLine().split(" ");
        int[] arr = new int[line1.length];
        s.close();
        for(int i=0; i< arr.length; i++){
            arr[i]= Integer.parseInt(line1[i]);
        }
        System.out.println(isMonotonic(arr));
     }

     public static boolean isMonotonic(int[] arr){
        boolean result;
        int size = arr.length;

        if(size <=2){
            return true;
        }
        //We will find the trend of array if it is increasing or decreasing first.
        int i=0;
        int trend;

         while(i< size-2 && arr[i]==arr[i+1])i++;
         if(i >=size-2){
             return true;
         }else{
             trend = (arr[i+1]-arr[i]<0)?-1:1;
             i++;
             //1 for inceasing -1 for decreasing
         }

         for(; i< size-1; i++){
             if(arr[i]==arr[i+1])continue;
             if(arr[i]<arr[i+1] && trend<0 )
                 return false;
             if(arr[i]>arr[i+1]&& trend>0)
                 return false;
         }
         return true;
     }
}
