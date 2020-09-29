import java.util.concurrent.atomic.AtomicIntegerArray;

public class Process implements Runnable{

    // shared static variables
    volatile static AtomicIntegerArray[] flag;
    volatile static AtomicIntegerArray[] turn;
    static int levels = -1;

    // shared counter
    static int counter = 0;
    private int ID;
    private int node;
    private String binaryID;
    static int NUMBER_OF_ITERATIONS = 1000;

    public Process(int id, int n){
        this.ID = id;
        this.binaryID = Integer.toBinaryString(id);
    }


    @Override
    public void run(){

        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void doWork() throws InterruptedException {

        for (int i=0; i < NUMBER_OF_ITERATIONS; i++) {
            lock();

            counter++;

            unlock();
        }


    }


    public void lock() throws InterruptedException {

        node = ID;
        for (int i=0; i < levels; i++) {

            int idThisLevel = 0;
            if (i <  binaryID.length()){
                idThisLevel = binaryID.charAt(binaryID.length()-1-i) - '0';
            }

            node = node/2;
            flag[i].set(2 * node + idThisLevel, 1);
            turn[i].set(node, 1 - idThisLevel);

            while ( flag[i].get(2 * node + 1 - idThisLevel) == 1 && (turn[i].get(node) != idThisLevel)) {};
        }

    }

    public void unlock(){
        for (int i=levels-1; i >= 0; i--){
            int idThisLevel = 0;
            if (i <  binaryID.length()){
                idThisLevel = binaryID.charAt(binaryID.length()-1-i) - '0';
            }

            flag[i].set(2 * node + idThisLevel, 0);
            node = (int) (ID / Math.pow(2, i));
        }
    }

}

