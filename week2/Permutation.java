import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by Alex on 6/30/2018.
 */
public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomizedQueue.enqueue(item);
        }
        Iterator<String> iter = randomizedQueue.iterator();
        int i = 0;
        if (k > 0) {
            while(iter.hasNext()){
                StdOut.println(iter.next());
                i++;
                if(i == k) {
                    break;
                }
            }
        }
    }
}
