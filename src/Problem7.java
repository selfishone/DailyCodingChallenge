//This problem was asked by Facebook.
//Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
//For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
//You can assume that the messages are decodable. For example, '001' is not allowed.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem7 {

    public static Integer numDecodableMessages(String encodedMessage){
        if(encodedMessage.substring(0).startsWith("0")){
            return 0;
        }else if(encodedMessage.length()<=1){
            return 1;
        }
        int total=0;

        if(Integer.parseInt(encodedMessage.substring(0,2))<=26){
            total+=numDecodableMessages(encodedMessage.substring(2));
        }

        total += numDecodableMessages(encodedMessage.substring(1));
        return total;
    }

     public static void main(String args[]){

        List<Integer> numbers = new ArrayList<>();
        Scanner scr = new Scanner(System.in);

        System.out.println("Please enter a list of Integers");
        String encodedMessage =  scr.nextLine();

        scr.close();

        System.out.println("The number of decodings possible is: "+numDecodableMessages(encodedMessage));
    }
}
