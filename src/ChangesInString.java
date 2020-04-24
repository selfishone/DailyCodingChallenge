import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChangesInString {

    public static void main(String args[] ) throws Exception {

        Scanner s = new Scanner(System.in);
        int numTests = Integer.parseInt(s.nextLine());                 // Reading input from STDIN
        int[] sizeOfStrings = new int[numTests];
        String[] abStrings = new String[numTests];

        for(int i =0 ; i< numTests; i++){
            sizeOfStrings[i] = Integer.parseInt(s.nextLine());
            abStrings[i] = s.nextLine();
        }
        s.close();

        for(int i=0; i< numTests; i++){
            System.out.println(minFlips(abStrings[i],sizeOfStrings[i]));
        }

    }

    private static int minFlips(String str, int size){

        if(size == 1 )
            return 0;
        if(size == 2 && str.charAt(0)== 'A'){
            return 0;
        }
        if(size == 2 && str.charAt(1)=='B'){
            return 0;
        }

        // When I used Hsh Map insted of array test cases didnt pass time taken was long why ??
        //https://stackoverflow.com/questions/6462055/hashmap-vs-array-performance
        int[] B = new int[size];
        int[] A = new int[size];

        int result = Integer.MAX_VALUE;
        // In hashmap B a key i would give us number of B's from 0 to index i
        if(str.charAt(0)=='B')
            B[0]=1;
        else B[0]=0;

        for(int i = 1; i< size; i++){
            if(str.charAt(i) == 'B'){
                B[i]= B[i-1]+1;
            }else{
                B[i]= B[i-1];
            }
        }

        if(str.charAt(size-1)=='A')
            A[size-1]=1;
        else A[size-1]=0;

        for(int i = size-2; i>=0; i--){
            if(str.charAt(i) == 'A'){
                A[i]=A[i+1]+1;
            }else{
                A[i]=A[i+1];
            }
        }

        for(int i=0; i<size-1; i++){
            result = Math.min(result, A[i+1]+B[i]);
        }
        result =Math.min(Math.min(result, B[size-1]), A[0]);

        return result;
    }
}
