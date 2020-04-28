import java.util.Scanner;

//Removal of Vertices Part2
public class JosephusProblem {

    public static void main(String[] args){
        //Scanner
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int k= Integer.parseInt(s.nextLine());

        s.close();

        System.out.println(josepheus(n,k));
    }

    //This method will return  index in a circle of n elements when k people are counted and last one slayed, starting position is index 0.
    public static int josepheus(int n, int k){
        if(n==1)return 0;
        //We count k positions and kill person at that location so new index for start would be k and
        else return (josepheus(n-1, k)+k)%n;
    }




}
