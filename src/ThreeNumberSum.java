import java.util.*;

public class ThreeNumberSum {
    /*
        1. Function that takes in non empty array of distinct integers, integer representing a target sum.
        2. Function must find all the triplets in array that sum up to the target sum.
        3. The triplets must be ordered in ascending order.
        4. The 2D output array must have all the triplets in sorted order.
     */

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        String line1[] = s.nextLine().split(" ");
        int[] arr = new int[line1.length];

        for(int  i=0; i < line1.length; i++){
            arr[i] = Integer.parseInt(line1[i]);
        }
        int sum = Integer.parseInt(s.nextLine());
        List<Integer[]> result = threeNumberSumTriplets3(arr, sum);
        System.out.println("The triplets are as follows: ");

        for(Integer[] triplet : result){
            System.out.println(Arrays.toString(triplet));
        }

    }

    // O(N*N) time complexity and O(N) space complexity.
    public static List<Integer[]> threeNumberSumTriplets3(int[] arr, int targetSum){
        int size = arr.length;

        //NlogN time for log, Java uses Quick sort for  Arrays.sort() method.
        Arrays.sort(arr);
        List<Integer[]> result = new ArrayList<Integer[]>();

        for(int i =0; i < size; i++){
            int left =i +1;
            int right = size-1;

            while(left<right){
                int sum = arr[left]+arr[right]+arr[i];
                if(sum==targetSum){
                    Integer[] temp = {arr[i],arr[left],arr[right]};
                    result.add(temp);
                    left++;
                    right--;
                }else if( sum>targetSum ){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }

    //O(N*N) time complexity
    public static List<List<Integer>> threeNumberSumTriplets2(int[] arr, int targetSum){

            Map<Integer, Integer> map = new HashMap();
            List<List<Integer>> result = new ArrayList<>();
            for(int i=0; i < arr.length; i++ ){
                map.put(arr[i],i);
            }

            Arrays.sort(arr);

            for(int i=0; i< arr.length; i++){
                for(int j=i+1; j< arr.length; j++){
                    int k = targetSum - arr[i]-arr[j];
                    if( k>arr[j] && map.containsKey(k)){
                        Integer[] temp = {arr[i],arr[j],k};
//                        Arrays.sort(temp);
                        List<Integer> triplet = Arrays.asList(temp);
                        result.add(triplet);
                    }
                }
            }
            return  result;
    }

    //We are iterating across the array by brute force time complexity will be O(N*N*N)
    public static List<List<Integer>> threeNumberSumTriplets1(int[] arr, int targetSum){
        Arrays.sort(arr);
        int size = arr.length;
        List<List<Integer>> result = new ArrayList<>();

        for(int i =0; i< size; i++ ){
            for(int j=i+1; j<size; j++){
                for(int k=j+1; k<size; k++){
                    if(arr[i]+arr[j]+arr[k]==targetSum){
                        Integer[] temp = {arr[i],arr[j],arr[k]};
//                        Arrays.sort(temp);
                        List<Integer> triplet = Arrays.asList(temp);
                        result.add(triplet);
                    }
                }
            }
        }
//        Collections.sort(result,);
        return result;
    }
}
