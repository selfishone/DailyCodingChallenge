import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RemovalOfVertices {

    public static void main(String[] args){
        Scanner s =  new Scanner(System.in);
        //Number of test cases.
        int T = Integer.parseInt(s.nextLine());
        String[] testCases = new String[T];

        for(int i=0; i< T; i++){
            testCases[i] = s.nextLine();
        }

        //Can we do the same thing without constructing polygon?
        for(int i=0; i<T; i++){
            String[] values = testCases[i].split(" ");
            Polygon p = new Polygon(Double.parseDouble(values[0]));
            System.out.println(p.explode(Double.parseDouble(values[1]),Double.parseDouble(values[0])));
        }
    }
}


class Polygon{

    private Vertex head;
    private Vertex tail;

    class Vertex{
        double value;
        Vertex next;

        public Vertex(double value){
            this.value = value;
        }
    }

    private void addVertex(double i){
        Vertex newVertex = new Vertex(i);
        if(head == null){
            head = newVertex;
        }else{
            tail.next = newVertex;
        }
        tail = newVertex;
        newVertex.next = head;
    }

    //Instead of adding vertices and removing them try using a ArrayList and remove indexes from them
    public Polygon(double N){
        for(double i =1; i<=N; i++){
            addVertex(i);
        }
    }

    public int explode(double K, double N){
        goToStartVertex(K,N);
        int initialIndex = (int)head.value;
        //Algorithm for list of all the index that can be deleted ?
        while(head.next.value != tail.value || head==tail){
                head = head.next;
                tail.next = head;
                tail = tail.next;
                head= head.next;
        }
        return (int)tail.value;
    }

    public void goToStartVertex(double K, double N){
        double index = ((2+ (2*K)%N) > N)?1:(2+ (2*K)%N);
        while(head.value != index){
            head= head.next;
            tail=tail.next;
        }
    }
}