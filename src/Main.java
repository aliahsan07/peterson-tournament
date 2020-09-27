public class Main {

    private static int noOfProcesses;

    public static void main(String[] args) throws InterruptedException {

//        noOfProcesses = 2;
//        // initalize shared variables
//
//
//        // boot the algorithm
        Process process0 = new Process(0);
        Process process1 = new Process(1);

//        for (int i = 0; i < flag.length; i++){
//            Process process = new
//        }

        Thread t1 = new Thread(process0);
        Thread t2 = new Thread(process1);
        t1.start();
        t2.start();

        t1.join();
        t2.join();


    }
}
