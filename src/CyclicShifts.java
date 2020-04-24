
import java.util.*;

class CyclicShifts {
    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int numQueries = Integer.parseInt(s.nextLine());
        // Reading input from STDIN
        List<String> queries = new ArrayList<>(numQueries);

        for(int i=0; i< numQueries; i++){
            String str = s.nextLine();
            queries.add(str);
        }
        s.close();
        for(int i=0; i< numQueries; i++){

            String[] values = queries.get(i).split(" ");
            int N = Integer.parseInt(values[0]);
            int m = Integer.parseInt(values[1]);
            char c = values[2].charAt(0);
            CircularBuffer cBuff = new CircularBuffer(numToBinary(N));
            cBuff.rotate(c, m);
            System.out.println(cBuff.compute());
        }

    }

    private static int[] numToBinary(int N){
        int[] result = new int[16];
        int x = N;
        for(int i =15; i >= 0; i--){
            result[i] = x%2;
            x= x/2;
            if(x ==0)
                break;
        }
        return result;
    }

}

class CircularBuffer{

    int[] buffer = new int[16];
    int currIndex =  15;

    public CircularBuffer(int[] arr){
        currIndex = arr.length-1;
        buffer = arr;
    }

    public void rotate(char c, int m){
        //Pointer must be rotated m times is equal to rotating m%16 times
        int shitValue = m%16;
        if(c=='L'){
            currIndex += m;
            currIndex = currIndex%16;
        }else if(c=='R'){
            currIndex -= m;
            currIndex = currIndex%16;
        }
    }

    public int compute(){
        int result = 0;
        int index = currIndex;

        for(int i=0; i<16; i++){
            if(index < 0) {
                index = index+16;
            }
            result+= Math.pow(2,i)*buffer[index];
            index--;
        }
        return result;
    }
}