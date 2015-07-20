import java.util.Iterator;

/**
 * Created by dongnanzhy on 7/13/15.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        int count = 0;
        Iterator<String> iter = queue.iterator();
        while(iter.hasNext() && count < k) {
            StdOut.println(iter.next());
            count++;
        }
    }
}
