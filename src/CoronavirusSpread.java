import java.util.Scanner;

public class CoronavirusSpread {

    /*
        1. Exactly one person in the group is infected in the beggining
        2. The virus can only spread to people who are atmost 2 distance apart.
        3. Need to find maximum and minimum infected people <best and worst cases>
     */
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        //Number of test cases
        int T = Integer.parseInt(s.nextLine());
        int[][] groups = new int[T][];
        for(int i=0; i< T; i++){
            int N= Integer.parseInt(s.nextLine());
            groups[i] = new int[N];
            String[] line = s.nextLine().split(" ");
            for(int j=0; j<line.length; j++){
                groups[i][j]= Integer.parseInt(line[j]);
            }
        }

        for(int i=0; i< T; i++){
            int[] result = findMinMax(groups[i]);
            System.out.println(result[0] +" "+result[1]);
        }
    }

    public static int[] findMinMax(int[] arr){
        int[] result = new int[2];
        //The 0th element will store the minimun and 1st will store the maximum
        result[0] = arr.length;
        result[1] = 1;
        int i = 0 ;
        if(arr.length==1){
            result[0]=1;
            result[1]=1;
            return result;
        }
        while(i<arr.length){
            int temmpInfectedGroupSize =1;
            while(i+1<arr.length && Math.abs(arr[i+1]-arr[i])<=2){
                temmpInfectedGroupSize++;
                if(temmpInfectedGroupSize>result[1])
                    result[1]=temmpInfectedGroupSize;
                i++;
            }
            result[0]=  (result[0]<temmpInfectedGroupSize)?result[0]:temmpInfectedGroupSize;
            i++;
        }

        return result;
    }
}
