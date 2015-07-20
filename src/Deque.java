import java.util.Iterator;

/**
 * Created by dongnanzhy on 7/13/15.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        public Item val;
        public Node next;
        public Node prev;
    }

    private Node dummy_first;
    private Node dummy_last;
    private int size;

    // construct an empty randomized queue
    public Deque() {
        size = 0;
        dummy_first = new Node();
        dummy_last = new Node();
        dummy_first.next = dummy_last;
        dummy_last.prev = dummy_first;
        dummy_first.prev = null;
        dummy_last.next = null;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        size++;
        Node newNode = new Node();
        newNode.val = item;
        newNode.next = dummy_first.next;
        newNode.next.prev = newNode;
        dummy_first.next = newNode;
        newNode.prev = dummy_first;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        size++;
        Node newNode = new Node();
        newNode.val = item;
        newNode.prev = dummy_last.prev;
        newNode.prev.next = newNode;
        dummy_last.prev = newNode;
        newNode.next = dummy_last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException();
        size--;
        Item item = dummy_first.next.val;
        dummy_first.next = dummy_first.next.next;
        dummy_first.next.prev = dummy_first;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException();
        size--;
        Item item = dummy_last.prev.val;
        dummy_last.prev = dummy_last.prev.prev;
        dummy_last.prev.next = dummy_last;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node cur = dummy_first.next;
        @Override
        public boolean hasNext() {
            return cur != dummy_last;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = cur.val;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(1);
        test.addFirst(0);
        test.addLast(2);
        test.addLast(3);
        test.removeFirst();
        test.removeLast();
        test.removeFirst();
        test.removeFirst();
        Iterator<Integer> iter = test.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
