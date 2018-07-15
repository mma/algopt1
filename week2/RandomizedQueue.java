import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by Alex on 6/30/2018.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;     // array of items
    private int n;        // number of elements on stack

    // construct an empty randomized queue
    public RandomizedQueue()  {
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()   {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private int currentIndex() {
        return n - 1;
    }

    private void resize(int capacity) {
        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (n == a.length) resize(2*a.length);  // double size of array if necessary
        a[n++] = item;                                  // add item
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int removeIndex = StdRandom.uniform(0,n);
        Item item = a[removeIndex];
        if (currentIndex() != removeIndex) {
            Item lastItem = a[currentIndex()];
            a[removeIndex] = lastItem;
        }
        a[currentIndex()] = null;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int sample = StdRandom.uniform(0,n);
        return a[sample];
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomIterator implements Iterator<Item> {
        private int i;
        private Item[] temp;

        public RandomIterator() {
            i = currentIndex();
            temp = (Item[]) new Object[size()];
            for(int j = 0; j <= i; j++) {
                temp[j] = a[j];
            }
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int removeIndex = StdRandom.uniform(0, i+1);
            Item item = temp[removeIndex];
            if (i != removeIndex) {
                Item lastItem = temp[i];
                temp[removeIndex] = lastItem;
            }
            temp[i] = null;
            i--;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        for (int i =0;i<4000;i++) {
            randomizedQueue.enqueue(i);
        }
        for (int i =0;i<3998;i++) {
            randomizedQueue.dequeue();
        }
        randomizedQueue.dequeue();
        Iterator<Integer> iter = randomizedQueue.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
