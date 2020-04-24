import java.util.*;

public class HappySegments {

    //Given a subarray of a we need to compute if it is happy or not
     public static void main(String[] args){
        //Scanner
        Scanner s = new Scanner(System.in);
        String[] line1 = s.nextLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);

        String[] line2 = s.nextLine().split(" ");
        int[] a = new int[n];
        for(int i =0; i< n; i++){
            a[i]=Integer.parseInt(line2[i]);
        }

        String[] line3 = s.nextLine().split(" ");
        int[] h = new int[m];
        for(int i =0; i< m; i++){
            h[i]=Integer.parseInt(line3[i]);
        }

        int numQueries = Integer.parseInt(s.nextLine());
        String[] queries = new String[numQueries];

        for(int i=0; i< numQueries; i++){
            queries[i] = s.nextLine();
        }

        for(int i=0; i< numQueries; i++){
            String[] values= queries[i].split(" ");
            int l = Integer.parseInt(values[0]);
            int r = Integer.parseInt(values[1]);
            System.out.println(isHappy(l-1,r-1,a,h,m));
        }
        s.close();
    }

    public static int isHappy(int l, int r, int[] a, int[] h, int m){
        int[] statsArray = new int[m];
        //The above array stores the number of times an element i occurs in subarray at index i-1

        for(int i=l; i <=r; i++){
            statsArray[a[i]-1]++;
            if(statsArray[a[i]-1]>h[a[i]-1])
                return 0;
        }

        for(int i=0; i<m; i++){
            if(statsArray[i]!=0 && statsArray[i]!= h[i])
                return 0;
        }
        return 1;
    }

}
