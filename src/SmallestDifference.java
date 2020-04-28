import java.util.Arrays;
import java.util.Scanner;

public class SmallestDifference {

    /*
         1. You are given two arrays.
         2. Required to find pair of integers from two arrays whose absolute difference is the smallest.
         3. Given that there will be at most 1 such pair and not more.
         4. The size of two arrays need not be same/ nothing related to this in the question.
         5. Another way of asking find the two closest numbers.
     */
    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        String[] line1 = s.nextLine().split(" ");
        String[] line2 = s.nextLine().split(" ");
        int[] arr1 = new int[line1.length];
        int[] arr2 = new int[line2.length];

        for(int i=0; i< line1.length; i++){
            arr1[i] = Integer.parseInt(line1[i]);
        }
        for(int i=0; i< line2.length; i++){
            arr2[i] = Integer.parseInt(line2[i]);
        }
        int[] result = smallestPair2(arr1, arr2);
        System.out.println(Arrays.toString(result));

    }

    /*
        Array manipulation without using BinarySearch type algo, but still making use of sorted array Properties.
     */
    public static int[] smallestPair2(int[] arr1, int[] arr2){

        int i =0; //Pointer to 1st sorted array
        int j = 0; //Pointer to the 2nd Sorted array
        int diff = Math.abs(arr1[i]-arr2[j]);
        int[] result = new int[2];
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        while(i< arr1.length && j < arr2.length){
            int num1 = arr1[i];
            int num2 = arr2[j];
            int curr_diff =Math.abs(arr1[i]-arr2[j]);
            if(arr1[i]<arr2[j]){
                i++;
            }else if(arr2[j]<arr1[i]){
                j++;
            }
            if(curr_diff<diff){
                diff = curr_diff;
                result[0] = num1;
                result[1] = num2;
            }
        }
        return result;
    }
    //Brute force method O(N*N) time complexity
    public static int[] smallestPair(int[] arr1, int[] arr2){
        int diff = Integer.MAX_VALUE;
        int i1 =-1;
        int i2 =-1;
        for(int i =0; i<  arr1.length; i++){
            for(int j=0; j< arr2.length; j++){
                if(Math.abs(arr2[j]-arr1[i])<diff){
                    diff= Math.abs(arr2[j]-arr1[i]);
                    i1= arr1[i];
                    i2 = arr2[j];
                }
            }
        }
        int[] result = {i1, i2};
        return result;
    }

    /*
        We can find distance between two points on number line and find which one is the closest.
        1. Sort both the arrays n*log(n) time
        2. For each element in first array find its difference between the same index elemnt in the 2nd array
        3. If element in 2nd array is greate than the element in 1st array also compute difference between 1st array element and index-1 element in 2nd array
     */
    public static int[] smallestDifference(int[] arr1, int[] arr2){
        int diff = Integer.MAX_VALUE;
        int[] result = new int[2];
        //Arrays.sort(arr1);
        //Sorting of second array is enough
        Arrays.sort(arr2);

        for(int i=0; i< arr1.length; i++){
            int[] binIndexes = binarySearch(arr1[i], arr2, 0, arr2.length-1);

            int temp1 = Math.abs(arr1[i]-arr2[binIndexes[0]]);
            int temp2 = Math.abs(arr1[i]-arr2[binIndexes[1]]);

                if(temp1<temp2 && temp1<diff ){
                    result[0]=arr1[i];
                    result[1]= arr2[binIndexes[0]];
                    diff = temp1;
                }else if(temp2<temp1 && temp2<diff){
                    result[0]=arr1[i];
                    result[1]= arr2[binIndexes[1]];
                    diff = temp2;
                }else if(temp1 == temp2 && temp2 < diff){
                    result[0] =arr1[i];
                    result[1]= arr2[binIndexes[1]];
                    diff = temp1;
                }
        }
        return result;
    }

    //This binary swarch is logN complexity so made N times it will be N*logN better than O(N*N)
    /*
        1. This function gives left and right indexes of arr between which number lies if 0<left<right<size
        2. This function also gives index,index if number is present in arr
        3. If the num < arr[0] return [0,0]
        4. if num > arr[length-1] return [length-1,length-1]
        5. This binary search mechanism is helping us finding nearest numbers to num in array on number line.
     */
    public static int[] binarySearch(int num, int[] arr, int left, int right){
        int[] result = {left, right};
        //This method will give the left index and the right index in arr between which the num lies
        if(num>arr[arr.length-1]){
            result[0] = arr.length-1;
            result[1] = arr.length-1;
            return result;
        }
        if(num < arr[0]){
            result[0] = 0;
            result[1] = 0;
            return result;
        }
        int mid = (left+right)/2;
        if(right-left==1 && arr[left]<=num && arr[right]>=num){
          return result;
        }
        if(left<=right){
            if(arr[mid]<num){
                return binarySearch(num, arr, mid+1,right);
            }
            if(arr[mid]>num){
                return binarySearch(num,arr, left, mid-1);
            }
            else{
                //This condition would mean that we have same number occuring in both arrays
                result = new int[2];
                result[0] = mid;
                result[1] = mid;
            }
        }
        return result;
    }
}

