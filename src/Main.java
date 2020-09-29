import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.io.FileWriter;

public class Main {

    private static int NUMBER_OF_PROCESSES = 5;
    private static String FILE_NAME = "results.csv";


    public static void main(String[] args) throws InterruptedException, IOException {

        // initialize shared variables

        if (args.length > 0){
            Process.NUMBER_OF_ITERATIONS = Integer.parseInt(args[0]);
        }

        if (args.length > 1){
            NUMBER_OF_PROCESSES = Integer.parseInt(args[1]);
        }

        Process.levels = (int) Math.ceil(Math.log(NUMBER_OF_PROCESSES) / Math.log(2));
        Process.flag = new AtomicIntegerArray[Process.levels];
        Process.turn = new AtomicIntegerArray[Process.levels];


        for (int i=0; i < Process.flag.length; i++){
            int[] myArray = new int[(int) Math.pow(2, Process.levels)];
            for (int j=0; j < myArray.length; j++){
                myArray[j] = 0;
            }
            Process.flag[i] = new AtomicIntegerArray(myArray);
        }

        int cols = (NUMBER_OF_PROCESSES + 1) / 2;
        for (int i=0; i < Process.levels; i++){
            int[] myArray = new int[cols];
            for (int j=0; j < cols; j++){
                myArray[j] = -1;
            }
            Process.turn[i] = new AtomicIntegerArray(myArray);
        }

        // boot the algorithm
        Thread threads[] = new Thread[NUMBER_OF_PROCESSES];
        long startTimeMillis = System.currentTimeMillis();
        for (int i=0; i < NUMBER_OF_PROCESSES; i++){
            Process p = new Process(i, NUMBER_OF_PROCESSES);
            Thread t = new Thread(p);
            threads[i] = t;
            t.start();
        }
        for (int i=0; i < NUMBER_OF_PROCESSES; i++){
            threads[i].join();
        }
        long diff = System.currentTimeMillis() - startTimeMillis;

        double throughput = (NUMBER_OF_PROCESSES * Process.NUMBER_OF_ITERATIONS) / (diff * 1.0);

        BufferedWriter bw = null;
        FileWriter fw = null;


        try {

            String data = NUMBER_OF_PROCESSES + "," + Process.NUMBER_OF_ITERATIONS + "," + diff + "," + throughput + "\n";
            File file = new File(FILE_NAME);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(data);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }


        System.out.println("count = " + Process.counter);


    }
}
