public class Main {

    private static int NUMBER_OF_PROCESSES = 5;

    public static void main(String[] args) throws InterruptedException {

        // initialize shared variables

        Thread threads[] = new Thread[NUMBER_OF_PROCESSES];
        // boot the algorithm

        for (int i=0; i < NUMBER_OF_PROCESSES; i++){
            Process p = new Process(i, NUMBER_OF_PROCESSES);
            Thread t = new Thread(p);
            threads[i] = t;
            t.start();
        }

        for (int i=0; i < NUMBER_OF_PROCESSES; i++){
            threads[i].join();
        }

        System.out.println("count = " + Process.counter);


    }
}
