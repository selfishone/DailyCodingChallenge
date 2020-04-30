import java.util.Scanner;

public class LongestPeak {
    /*
        1. Find longest subarray which has a peak
        2. initially the array will be strictly increasing reaches a max then strictly decreasing ?
     */

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        String[] line1 = s.nextLine().split(" ");
        int[] arr = new  int[line1.length];

        for(int i=0; i< line1.length; i++){
            arr[i] = Integer.parseInt(line1[i]);
        }
//        System.out.println("GG");
        System.out.println(longestPeak2(arr));
    }

    /*
        1. We will iterate through array finding a peak
        2. When we find a peak from peak we will count backwards and forwards until peak ends at both sides
        3. If this is greater than currentMax change the max Length.
     */
    public static int longestPeak2(int[] arr) {
        int result = 0;
        if(arr.length<3)return 0;

        for(int i =1; i< arr.length-1; i++){
            if(isPeak(i,arr)){
                //If peak Find the peak length from this point
                int currPeakLen = peakLength(i, arr);
                //System.out.println("Peak Length at: "+arr[i]+" is: "+currPeakLen);
                result = (result > currPeakLen)?result:currPeakLen;
            }
        }
        return result;
    }

    private static int peakLength(int i, int[] arr) {
        int left =i-1;
        int right =i+1;
        int result =3;
       // System.out.println("RIGHT");
        while(right+1<arr.length && arr[right]>=arr[right+1]){
           // System.out.println(arr[right]+ " "+ arr[right+1]);
            result+=1;
            right+=1;
        }
      //  System.out.println("LEFT");
        while(left-1>=0 && arr[left-1]<=arr[left]){
           // System.out.println(arr[left-1]+ " "+ arr[left]);
            result+=1;
            left-=1;
        }
        return result;
    }

    /*
        Traversing the array we are trying to find peaks
        1. Find the first bottom end of an ascent
        2. Find first botton end of descent from there
        3. For loop between the two ends
        4. Climb up the ascent <register this as  the start of the peak> and check whether the top of ascent is a peak or not
        5. Climb down the descent and continue until the peaks values are monotonically decreasing until next ascent begins.
        6. Register the last index as bottom of peak
        7. Length of peak = (descentEnd -AscentEnd)+1
        8. if length of curr Peak > peak maximum update the max length.
        9. Continue to the next index
     */
    //TODO: Working in local but getting TLE in AlgoExpert Why ?
    public static int longestPeak(int[] arr) {
        int size = arr.length;
        int maxPeakLength= 0;
        int i =0;

        while(i< size){
            i = (findNextAscent(arr,0)>0)?findNextAscent(arr,0):i;
            int peakStart=i;
            int peakEnd=-1;

            //Climb up the ascent
            while( i+1<size && arr[i+1]>= arr[i]){
                i++;
            }
            peakEnd=i;
            //See if peak is present at index i
            if(isPeak(i, arr)){
                //If peak not broken then see how long peak goes downhill
                while(peakEnd+1<size && arr[peakEnd+1]<=arr[peakEnd]){
                    if(arr[peakEnd+1]<arr[peakEnd]){
                        i=peakEnd+1;
                    }
                    peakEnd++;
                }

                int currPeakLength = peakEnd-peakStart+1;

                //Update basesd on previous peaks lengths and only if peak is present
                maxPeakLength = (currPeakLength>maxPeakLength)?currPeakLength:maxPeakLength;
//                System.out.println("maxLenth: "+maxPeakLength);
            }
        i++;
        }
        return maxPeakLength;
    }

    public static boolean isPeak(int i, int[] arr){
        if(i-1<0||i+1>arr.length-1)
            return false;
        if(arr[i-1]<arr[i] && arr[i]> arr[i+1])
            return true;
        return false;
    }

    /*
        1. This method will return us the next integer from index i such that we see monotonic increase in values
        2. Returns -1 if there is not descent at i
     */
    public static int findNextAscent(int[] arr, int i){

        int result=-1;

        while(i+1< arr.length && arr[i+1]<=arr[i]){
            if(arr[i+1]< arr[i]){
                result = i+1;
            }
            i++;
        }
        return result;
    }
}
