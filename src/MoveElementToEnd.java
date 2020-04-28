import java.util.Arrays;
import java.util.Scanner;

public class MoveElementToEnd {
    /*
        1. Input contains an Array, items may repeat.
        2. Element in the array K
        3. Move all the instances of K to end of the array
        4. Modify the array in place
        5. How to do it when order of other integers has to be maintained and when order is not important?
     */
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] line1 = s.nextLine().split(" ");
        String line2 = s.nextLine();
        s.close();

        int k = Integer.parseInt(line2);
        int[] arr = new int[line1.length];

        for(int i=0; i<line1.length; i++){
            arr[i]= Integer.parseInt(line1[i]);
        }
        moveToEnd2(arr, k);
        System.out.println(Arrays.toString(arr));
    }

    //This is for when order is not important.
    public static void moveToEnd2(int[] arr, int k){
        int size =arr.length;
        int left =0;
        int right = size-1;

        while(left<right){
            while(right>left && arr[right]==k){
                right--;
            }
            if(arr[left]==k){
                swap(arr, left, right);
                right--;
            }
            left++;
        }
    }

    //TODO: Something wrong with login this is not working.
    public static void moveToEnd(int[] arr, int k){
        int current_k_position=-1;
        int i =0;
       // int current_nonk_position=-1;
         while(i<arr.length){
            if(arr[i]==k){
                current_k_position = i;
                i++;
                while(i<arr.length && arr[i]==k){
                    i+=1;
                }
           //  while(i+1<arr.length && arr[i+1]==k)i+=1;
            }else if(current_k_position>=0 && i<arr.length && arr[i]!=k){
                swap(arr, current_k_position, i);
              //  System.out.println(Arrays.toString(arr));
                while(current_k_position<arr.length && arr[current_k_position]!=k){
                   //System.out.println("Curre: "+arr[current_k_position]);
                    current_k_position++;
                }
               // System.out.println("Curre2: "+arr[current_k_position]);
                i++;
            }
        }
    }

    private static void swap(int[] arr, int current_k_position, int i) {
        int temp = arr[i];
        arr[i]= arr[current_k_position];
        arr[current_k_position]=temp;
    }
}
