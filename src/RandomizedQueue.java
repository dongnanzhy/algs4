import java.util.Iterator;
import java.util.Objects;

/**
 * Created by dongnanzhy on 7/13/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private int count;
    private Object[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 1;
        count = 0;
        items = new Object[size];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the queue
    public int size() {
        return count;
    }

    private void enlarge() {
        size *= 2;
        Object[] tmp = new Object[size];
        for (int i = 0; i < count; i++) {
            tmp[i] = items[i];
        }
        items = tmp;
    }
    private void compress() {
        size /= 2;
        Object[] tmp = new Object[size];
        for (int i = 0; i < count; i++) {
            tmp[i] = items[i];
        }
        items = tmp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        items[count] = item;
        count++;
        if (count >= size) {
            enlarge();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0) throw new java.util.NoSuchElementException();
        int pos = StdRandom.uniform(count);
        Object tmp = items[pos];
        items[pos] = items[--count];
        if (count <= size/4) {
            compress();
        }
        return (Item)tmp;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (count == 0) throw new java.util.NoSuchElementException();
        int pos = StdRandom.uniform(count);
        return (Item)items[pos];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private int pos;
        private Object[] tmp;
        public RandomizedIterator() {
            tmp = new Object[count];
            for (int i = 0; i < count; i++) {
                tmp[i] = items[i];
            }
            pos = 0;
            StdRandom.shuffle(tmp);
        }
        @Override
        public boolean hasNext() {
            return pos < count;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Object rst = tmp[pos];
            pos++;
            return (Item)rst;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(0);
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        System.out.println(test.dequeue());
        System.out.println("size = " + test.size());
        System.out.println(test.dequeue());
        System.out.println("size = " + test.size());
        System.out.println(test.dequeue());
        System.out.println("size = " + test.size());
        System.out.println(test.dequeue());
        System.out.println("size = " + test.size());
        // System.out.println(test.dequeue());
        //System.out.println("size = " + test.size());
        Iterator<Integer> iter = test.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }  // unit testing
}