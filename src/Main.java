public class Main {

    private static int NUMBER_OF_PROCESSES = 5;

    public static void main(String[] args) throws InterruptedException {

        // initialize shared variables

        if (args.length > 0){
            Process.NUMBER_OF_ITERATIONS = Integer.parseInt(args[0]);
        }

        if (args.length > 1){
            NUMBER_OF_PROCESSES = Integer.parseInt(args[1]);
        }

        Process.levels = (int) Math.ceil(Math.log(NUMBER_OF_PROCESSES) / Math.log(2));
        Process.flag = new boolean[Process.levels][(int) Math.pow(2, Process.levels)];
        int cols = (NUMBER_OF_PROCESSES + 1) / 2;
        Process.turn = new int[Process.levels][cols];

        for (int i=0; i < Process.flag.length; i++){
            for (int j=0; j < Process.flag[0].length; j++){
                Process.flag[i][j] = false;
            }
        }

        for (int i=0; i < Process.levels; i++){
            for (int j=0; j < cols; j++){
                Process.turn[i][j] = -1;
            }
        }
        // boot the algorithm

        Thread threads[] = new Thread[NUMBER_OF_PROCESSES];
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
