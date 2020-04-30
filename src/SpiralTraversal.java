import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    1. Given an nXm 2D array
    2. Write down an function for spiral traversal.
 */
public class SpiralTraversal {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int m = Integer.parseInt(s.nextLine());
        int[][] arr = new int[n][m];
        String[] lines = new String[n];

        for(int i=0; i<n; i++){
            lines[i]= s.nextLine();
            String[] row = lines[i].split(" ");
            for(int j=0; j<m; j++){
                arr[i][j]= Integer.parseInt(row[j]);
            }
        }

        System.out.println(spiralTraversal(arr));
    }

    //Edge case: single element at the middle of the matrix.
    public static List<Integer> spiralTraversal(int[][] array){
        List<Integer> result = new ArrayList<>();
        int n =array.length;
        int m = array[0].length;

        int leftBorder = 0;
        int rightBorder = m-1;
        int topBorder = 0;
        int botBorder = n-1;

        //int i=0, j =0;

        while(leftBorder<=rightBorder && topBorder<=botBorder){

            //Move right
            for(int j=leftBorder; j<=rightBorder;j++){
                result.add(array[topBorder][j]);
            }

            for(int i= topBorder+1;i<=botBorder;i++){
                result.add(array[i][rightBorder]);
            }

            for(int j=rightBorder-1;j>=leftBorder;j--){
                if(botBorder==topBorder)
                    break;
                result.add(array[botBorder][j]);
            }

            for(int i=botBorder-1;i>topBorder;i-- ){
                if(leftBorder==rightBorder)
                    break;
                result.add(array[i][leftBorder]);
            }
            topBorder++;
            rightBorder--;
            botBorder--;
            leftBorder++;
        }
        return result;
    }
}
