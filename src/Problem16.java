//This problem was asked by Twitter.
//
//You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:
//
//record(order_id): adds the order_id to the log
//get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
//You should be as efficient with time and space as possible.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem16 {

    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        System.out.println("What is the size of the order List: ");
        int N = scr.nextInt();

        scr.close();
        LogFile2 log = new LogFile2();
        log.setCapacity(N);

        for(int i = 0; i<N; i++) {
            log.record(Integer.toString(i));
        }

        for(int i=1 ; i<=N; i++){
            System.out.println("The "+i+"th item in the list from the last has a value of: "+log.getLast(i));
        }
        log.record("Nikhil");
        for(int i=1 ; i<=N; i++){
            System.out.println("The "+i+"th item in the list from the last has a value of: "+log.getLast(i));
        }
    }
}

//This impllementation is not so efficient with time it takes O(N) to insert into a full list.
class LogFile{

    private List<String> orderList = new ArrayList<>();
    private Integer capacity;

    public void setCapacity(int N){
        this.capacity = N ;
    }

    public void record(String orderId){
        orderList.add(orderId);
        //the major  performance bottleneck will be here when we remove an element at the bottom of the list all elements at top need to be shifted to the left by 1.
        //How to avoid this: Can use linked List to delete the start Node is constant time complexity. But accessing elements is not of constant time.
        if(orderList.size()>capacity )
        orderList.remove(0);
    }

    public String getLast(int i){
        if(i <= capacity)
            return orderList.get(capacity-i);
        return "";
    }
}

class LogFile2{

    private List<String> orderList = new ArrayList<>(10);
    private Integer capacity;
    private Integer currIndex= 0;

    public void setCapacity(int i){
        this.capacity = i ;
    }
    // We can solve the problem using a circular buffer.
    //No more need to move all the elements by one space when inserting a new order to a full list

    public void record(String orderId){
        //When ever a new insertion to a full list happens remove the current oldest item in the list.
        /* Algorithm is:
        1. CurrIndex points to the oldest element inserted into the list.
        2. CurrIndex will remain as 0 for all the insertions until size of list reaches max capacity
        3. New Insertions will happen at CurrIndex and CurrIndex will increment by 1, newer elements are added at back of list.
        * */

        if(orderList.size()<capacity){
            orderList.add(orderId);
        }else{
            orderList.set(currIndex, orderId);
            if(currIndex< capacity-1)
                currIndex+=1;
            else
                currIndex = currIndex+1-capacity;
        }
    }

    public String getLast(int i){
       //This method should return ith last element in the orderList.
        if(currIndex-i >=0)return orderList.get(currIndex-i);
        else
            return orderList.get(capacity+currIndex-i);

    }

}