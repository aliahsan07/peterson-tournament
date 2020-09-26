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

        process0.start();
        process1.start();
//
        process0.lock();
        process1.lock();
        process1.lock();
        process0.lock();
        process0.lock();
        process1.lock();
        process0.lock();
        process1.lock();
        process0.lock();
        process0.lock();
        process1.lock();
        process1.lock();
        process1.lock();
        process1.lock();
        process0.lock();


//        Process pt0 = new Process(0);
//        Process pt1 = new Process(1);
//
//        Process.initializeArray();
//
//        pt0.start();
//        pt1.start();
//
//        try {
//            pt0.join();
//            pt1.join();
//        } catch (InterruptedException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        System.out.println("threads all done");


    }
}
