public class Process implements Runnable{

    // shared static variables
    volatile static boolean[] flag = new boolean[2];
    volatile static int turn = -1;
    // shared counter
    static int counter = 0;
    private int ID;
    final int NUMBER_OF_ITERATIONS = 10000000;

    public Process(int id){
        this.ID = id;
    }

    @Override
    public void run(){

//        System.out.println("Process #" + this.ID + " started running...");
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void doWork() throws InterruptedException {

        for (int i=0; i < NUMBER_OF_ITERATIONS; i++) {
//            System.out.println("Process #" + this.ID + " begins lock section " + flag[1 - this.ID] + " : " + turn);
            lock();
//            System.out.println("Process #" + this.ID + " med lock section " + flag[1 - this.ID] + " : " + turn);
            // execute critical section
            counter += 1;
//            System.out.println("Process #" + this.ID + " is currently executing critical section.");
//            System.out.println("counter value:" + counter);

            // release the lock
            unlock();
        }

    }

    public void lock() throws InterruptedException {

        flag[this.ID] = true;
        turn = 1 - this.ID;
//        System.out.println(" lock status myID:"+  this.ID + " " + flag[this.ID] + ":" + flag[1 - this.ID] + " : " + turn);
        while (flag[1-ID] && turn != ID);

    }

    public void unlock(){
        flag[this.ID] = false;
    }

}



//import java.util.concurrent.ThreadLocalRandom;
// https://github.com/nikoSchoinas/MutExMethods/blob/master/src/Main.java
// https://stackoverflow.com/questions/2911915/peterson-algorithm-in-java
// https://www.cs.tau.ac.il/~shanir/multiprocessor-synch-2003/mutex/notes/mutex2.pdf
//public class Process extends Thread {
//    // lock array combined with turn variable
//    // defines which thread will run.
//    private static volatile boolean[] lock = new boolean[2];
//    private static volatile int turn = 0;
//    private static volatile int counter = 0; // Just a counter
//    private int index;  // This variable helps while loop inside run().
//    private int id; //thread identification.
//
//    public Process(int id) {
//        this.id = id;
//        this.index = id;
//    }
//
//    public static void initializeArray() {
//        //set initial values to array.
//        lock[0] = true;
//        lock[1] = false;
//    }
//
//    public void run() {
//        while (counter < 21) {
//            lock[index] = true; // if id = 0, index = 0 else index = 1.
//            turn = index;
//            while (lock[(index+1) % 2] && turn == (index+1) % 2) {
//                //This condition  assures that a thread will be interrupted if it's going to print a number bigger than 20.
//                //That means it will not enter critical section.
//                if(counter>20) {
//                    Thread.currentThread().interrupt();
//                    return;
//                }
//            };
//            //inside critical section
//            System.out.println("Thread: " + currentThread().getName() + " prints " + counter++);
//            lock[index] = false;
//            //outside critical section.
//            try {
//                sleep(ThreadLocalRandom.current().nextInt(1, 1000)); // random sleeping time between 1 and 1000
//                // milliseconds
//            } catch (InterruptedException e) {
//            }
//        }
//    }
//}
