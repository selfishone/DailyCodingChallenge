import java.util.*;

public class PlaneJourney {
    //A1,A2,....,AN are N groups of people having Ai persons in each group
    //B1,B2,....,Bm are M places with Bi capacity each
    //Multiple planes can make a trip parallely, And each plane can carry at most 1 group
    //Planes can return and take a new group to destination.
    //A group cannot be divided, Minimum time in which

    /*
        There is a one to one mapping between what group of people has to with what plane.
        1. Negative case if the largest plane cant accoammodate the largest group return -1
        2. time taken for dropping people to destination in the first trip is T
        3. Time taken for dropping people to destination in the consequent trips is 2*T because the planes have to return
        4. To measure time we need to know the number of planes at source and destination after each trip(people dropped off)
        5. And after each trip people in source should not be able to board planes in source <size of groups is more>
        6. People left at the source can only be taken by the returning flights <flights called back>
        7. At any ith Position in planes array see how many groups of people it cant carry.
        8. These people should be carried back by the returning plane from destination

     */
    public static void main(String[] args){
         Scanner s = new Scanner(System.in);
         String[] line1 = s.nextLine().split(" ");
         int N = Integer.parseInt(line1[0]);
         int M = Integer.parseInt(line1[1]);
        //N: number of groups of people, M: Number of planes

        String[] peopleGroupSizes = s.nextLine().split(" ");
        String[] planesCapacity = s.nextLine().split(" ");

        int[] people = new int[N];
        int[] planes = new int[M];

        for(int i=0; i< N; i++){
            people[i]= Integer.parseInt(peopleGroupSizes[i]);
        }
        for(int i=0; i< M; i++){
            planes[i]= Integer.parseInt(planesCapacity[i]);
        }

        System.out.println("The time taken to fly all the passengers is: "+minTimeAllTrips(people,planes));
    }

    public static int minTimeAllTrips(int[] people, int[] planes){

        //Sorts both the groups in ascending order.
        Arrays.sort(people);
        Arrays.sort(planes);
        int n = people.length;
        int m = planes.length;
        int minTime =1;
        int maxTime = -1;

        //Base case when no trips are possible
        if(people[n-1]>planes[m-1])
            return-1;

        int i = m-2;
        int j= n-2;
        int source = n;
        int destination = m;

        //i is pointer on the groups of people;
        while(i >= 0){

            //Case where the plane is not able to accoammodate the ith group of people.
           while(planes[j]<people[i]){
               i--;
               minTime+=2; //Since plane must return and take people to destination
               if(minTime>maxTime)
                   maxTime = minTime;
            }
           j--;
           i--;
           if(minTime>maxTime)
               maxTime = minTime;
           source--;
           destination++;

           if(j==-1 && i!= -1 ){
               if(source>i){
                   maxTime++;
                   break;
               }else{
                   source = m;
                   maxTime++;
                   if(source>i){
                       maxTime++;
                       break;
                   }else{
                       int k = (i/source)+(i%source);
                       maxTime+=2*k-1;
                   }
               }
           }
        }

        return maxTime;
    }
}
