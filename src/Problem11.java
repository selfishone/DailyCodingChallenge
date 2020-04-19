//This problem was asked by Twitter.
//Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.
//For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
//Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.
// https://www.dailycodingproblem.com/solution/11?token=a62775baba48731da4cd3ee8d4f8a8f376fb6c6208e6eee1057b0c75c787ff412f960771
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem11 {

    //For whatever prefix recommendations we are searching we should get the list of autocomplete recommendations.
    //List is just a collections doesnt have any proper order so we cant use list.
    private static List<String> recommendedContainQueries(List<String> queries, String str){
        //This is not an efficient way of Doing things.This is taking us O(N*N2) time complexity there should be a better way to do this
        //N is number of queies, n2 is average length of queries in list
        List<String> recommendations  = new ArrayList<String>();

        for(String s : queries ){
            if (s.contains(str))recommendations.add(s);
        }
        return recommendations;
    }

    //TODO: For more efficient algorithm look at prefixTree Implementation
    private static List<String> recommendedStartsWithQuries(List<String> queries, String str){
        //The question actually asked about queries that start with a particular string not <contains>
        List<String> recommendations  = new ArrayList<String>();
        for(String s : queries ){
            if (s.startsWith(str))recommendations.add(s);
        }
        // Best way is to resturcture the Dictionary into a Prefix Tree(trie) and count number of paths to leaves from the total match point
        return recommendations;
    }

    //TODO: Try using BinarySearch Algorithm for this.

    private static boolean hasSubstring(String parent, String str){
       char[] parentArray = parent.toCharArray();
       char[] subArray = str.toCharArray();

        int indexInSubString = 0;
        //Finding Sunstring or not is a O(N) time complexity in the worst case scenario
       for( int i = 0; i< parentArray.length; i++ ){

           if(indexInSubString == subArray.length-1 && subArray[indexInSubString]==parentArray[i]){
               return true;
           }
           if(parentArray[i]==subArray[indexInSubString]){
               indexInSubString +=1;
           }else {
               indexInSubString = 0;
           }
       }
       return false;
    }

    public static void main(String[] args){

        List<String> queries  = new ArrayList<>();
        Scanner scr = new Scanner(System.in);

        System.out.println("Please enter a list of Queries");
        String[] queryArray =  scr.nextLine().split(",") ;
        for(String str : queryArray){
            queries.add(str);
        }

        System.out.println("Please enter the search string: ");
        String searchString = scr.nextLine();
        scr.close();

        System.out.println("The list of recommendations are : " +recommendedStartsWithQuries(queries, searchString));
        System.out.println("Is dam a substring of damsel: "  + hasSubstring("damsel", "dam") );
    }
}
