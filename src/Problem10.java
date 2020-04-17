//This problem was asked by Apple.
//Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.

// Scheduler implementation using Timer Class Java: https://codereview.stackexchange.com/questions/214538/implement-a-job-scheduler-which-takes-in-a-function-f-and-an-integer-n-and-call
public class Problem10 {

    //Using Runnable functional interrface helps us pass on functional classes as parameters
    public static void scheduler(Integer n, Runnable fun ) throws InterruptedException {
       //A separate thread for just calling the functions asynchronously, no need of creating new threads for each scheduling call
        Thread t = new Thread(fun);
        t.sleep(n);
        t.start();
    }

    // If being asynchronous is not a problem we can use current thread insted of creating new thread??
    // Cons: This method still pauses the current thread execution can we continue the thread asynchronously ?
    public static void scheduler2(Integer n, Runnable fun ) throws InterruptedException {
        Thread.sleep(n);
        fun.run();
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Calling the Scheduler: ");
        scheduler2(10000, ()-> System.out.println("Printed this after the given timer ended"));
    }
}
