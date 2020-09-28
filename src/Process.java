import java.util.Arrays;

public class Process implements Runnable{

    // shared static variables
    volatile static boolean[][] flag;
    volatile static int[][] turn;
    static int levels = -1;

    // shared counter
    static int counter = 0;
    private int ID;
    private int node;
    private String binaryID;
    static int NUMBER_OF_ITERATIONS = 1000;
    static volatile int current = 0;

    public Process(int id, int n){
        this.ID = id;
        this.binaryID = Integer.toBinaryString(id);

    }

    public void setID(int id){
        this.ID = id;
    }

    public int getID(){
        return this.ID;
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
            if (current > 1){
                System.out.println("violation reported");
            }
            current += 1;
//            System.out.println("Process #" + this.ID + " med lock section " + flag[1 - this.ID] + " : " + turn);
            // execute critical section
//            System.out.println("Process " + ID + " is executing critical section.");
            counter += 1;
//            System.out.println("Process #" + this.ID + " is currently executing critical section.");
//            System.out.println("counter value:" + counter);

            // release the lock
            current -= 1;
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
            flag[i][2 * node + idThisLevel] = true;
            turn[i][node] = 1 - idThisLevel;

//            System.out.println("My original ID: " + ID + " level: " + i + " location " + (2 * node + idThisLevel) + " My opponent " + (2 * node + 1 - idThisLevel) + " flags: " + Arrays.deepToString(flag) + " turn: " + Arrays.deepToString(turn));
            while (flag[i][2 * node + 1 - idThisLevel] == true && turn[i][node] != idThisLevel) ;
        }

    }

    public void unlock(){
        for (int i=levels-1; i >= 0; i--){
            int idThisLevel = 0;
            if (i <  binaryID.length()){
                idThisLevel = binaryID.charAt(binaryID.length()-1-i) - '0';
            }

//            System.out.println("Node* " + ID + " has id: " + idThisLevel + " location " + (2 * node + idThisLevel) + " in level " + i + " nodez value " + node);
            flag[i][2 * node + idThisLevel] = false;
            node = (int) (ID / Math.pow(2, i));
        }
    }

}

