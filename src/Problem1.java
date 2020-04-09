import java.util.*;

public class Problem1 {

//    This problem was recently asked by Google.
//
//    Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
//
//    For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
//
//    Bonus: Can you do this in one pass?

    //This method is O(N*N) complexity
    public static boolean isSumKProcess1(int k, List<Integer> numbers){

        //This is a brute force method, for each integer check if k-i is there in the list or not
        for(Integer i : numbers){
            if(numbers.contains(k-i))
                return true;
        }
        return false;
    }

    //This method is O(N) time complexity but space complexity is higher.
    public static boolean isSumKProcess2(int k, List<Integer> numbers){

        //This method is implementation using Sets to store already seen value.
        // We are using Hash Set which provides constant time performance to add remove and contains
        HashSet<Integer> h = new HashSet<>();

        for(Integer i : numbers){
            if(h.contains(k-i)){
                return true;
            }else{
                h.add(i);
            }
        }
        return false;
    }

    //This method is O(NlogN) time complexity.
    public static boolean isSumKProcess3(int k, List<Integer> numbers){

        //This method is implementation using Sorted list and applying binary search on it.
        Collections.sort(numbers);

        int index = 0;
        for(Integer i : numbers){
            int matchedIndex = Collections.binarySearch(numbers, k-i);
            // Checking if the matched index is not the same as the original index
            if(matchedIndex<0){
                return false;
            }else if(matchedIndex >=0 && matchedIndex != index){
                return true;
            }else if(matchedIndex +1< numbers.size() && numbers.get(matchedIndex+1).equals(numbers.get(matchedIndex))){
                //Last 2 if else statements are for checking suplicate values in the list.
                return true;
            }else if(matchedIndex -1 >= 0 && numbers.get(matchedIndex-1).equals(numbers.get(matchedIndex))){
                return true;
            }
            index++;
        }
        return false;
    }

    public static void main(String[] args){

        List<Integer> numbers = new ArrayList<>();
        Scanner scr = new Scanner(System.in);

        System.out.println("Please enter a list of Integers");
        String[] numbersStringArr =  scr.nextLine().split(",") ;
        for(String i : numbersStringArr){
            numbers.add(Integer.parseInt(i));
        }

        System.out.println("Please enter the value of K:");
        int k = scr.nextInt();

        scr.close();

        System.out.println("Statement value K can be obtained as sum is: "+ isSumKProcess1(k, numbers));
        System.out.println("Statement value K can be obtained as sum is: "+ isSumKProcess2(k, numbers));
        System.out.println("Statement value K can be obtained as sum is: "+ isSumKProcess3(k, numbers));

    }

}
