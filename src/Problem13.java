//This problem was asked by Amazon.
//Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
//For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".

import java.util.*;

public class Problem13 {

//    This takes O(n * k) time and O(k) space.
    public static String longestSubstring(String str, int k){
        int startIndex = 0;
        int endIndex = 0;

        int solStartIndex = startIndex;
        int solEndIndex = endIndex;

        //We are using Map instead of Set just in case we want an implementaion that is concerned with frequencyo of certain character ?
        Map<Character, Integer> statsMap = new HashMap<>();

        for(int i =0; i< str.length(); i++){
            statsMap.put(str.charAt(i),i);

            if(statsMap.size() > k){
                //Start Index will be initialized to least value in the Map+1.
                startIndex = helper(statsMap);
                //After this we have to remove the key of the above value how to do it ?
            }
            endIndex = i;

            if(solEndIndex-solStartIndex < endIndex-startIndex){
                solEndIndex =endIndex;
                solStartIndex = startIndex;
            }
        }
        return str.substring(solStartIndex, solEndIndex+1);
    }

    //Removing element from Map is becoming complicated any way around that ?
    public static int helper(Map<Character, Integer> statsMap){
        //This will iterate over the map and remove the pair with smallest value. and return value+1

        //Map doesnt implement a collections interface so you dont directly get an iterator over Map. so we get an entry set and iterate over it
        //https://javarevisited.blogspot.com/2017/08/how-to-remove-key-value-pair-from-map-iteration-java-example.html
        Set<Map.Entry<Character, Integer>> setOfEntries =  statsMap.entrySet();
        Iterator<Map.Entry<Character, Integer>> iterator = setOfEntries.iterator();

        int value_to_remove = Collections.min(statsMap.values());

        while(iterator.hasNext()){
           if(iterator.next().getValue().equals(value_to_remove)){
               iterator.remove();
           }
        }
        return value_to_remove+1;
    }
    public static void main(String args[]){

        Scanner scr = new Scanner(System.in);
        System.out.println("Please enter the string that has to be analyzed: ");
        String str = scr.nextLine();
        System.out.println("Enter the value of k: ");
        int k = scr.nextInt();

        System.out.println("The longest substring with max of k unique values is: "+ longestSubstring(str,k));
    }
}
