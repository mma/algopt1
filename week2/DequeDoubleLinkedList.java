import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Alex on 6/30/2018.
 */


public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private int count;

    private class Node {
        Node next;
        Node prev;
        Item item;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.size() == 0) {
            this.initializeFirstElement(item);
        } else {
            Node toAdd = new Node();
            toAdd.item = item;
            toAdd.next = first;
            if (size() == 1) {
                toAdd.prev = first;
            } else {
                toAdd.prev = first.prev;
            }
            first.prev = toAdd;
            first = toAdd;
        }
        count++;
    }

    private void initializeFirstElement(Item item) {
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = null;
        newFirst.prev = null;
        first = newFirst;
    }

    private void initializeLastElement(Item item) {
        Node newLast = new Node();
        newLast.item = item;
        newLast.prev = first;
        first.next = newLast;
        first.prev = newLast;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.isEmpty()) {
            this.addFirst(item);
        } else {
           if (this.size() == 1) {
               this.initializeLastElement(item);
           } else {
               Node newLast = new Node();
               newLast.item = item;
               newLast.prev = first.prev;
               first.prev.next = newLast;
               first.prev = newLast;
           }
           count++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
       if (this.isEmpty()) throw new java.util.NoSuchElementException();
       if (this.size() == 1) {
           count--;
           return this.removeTheFirstElement();
       } else if (this.size() == 2) {
           count--;
           return this.removeTheFirstElementSizeOf2();
       }
       count--;
       return this.removeTheFirstElementSizeBiggerThan2();
    }

    private Item removeTheFirstElementSizeBiggerThan2() {
        Item  item = first.item;
        Node last = first.prev;
        first = first.next;
        first.prev = last;
        return item;
    }

    private Item removeTheFirstElementSizeOf2() {
        Item  item = first.item;
        first = first.next;
        first.prev = null;
        first.next = null;
        return item;
    }

    private Item removeTheFirstElement() {
        Item item = first.item;
        first = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
        if (this.size() == 1) {
           return this.removeFirst();
        } else if (this.size() == 2) {
            count--;
            return this.removeTheLastElementSizeOf2();
        }
        count--;
        return this.removeTheLastElementSizeBiggerThan2();
    }

    private Item removeTheLastElementSizeOf2 () {
        Item item = first.prev.item;
        first.prev = null;
        first.next = null;
        return item;
    }

    private Item removeTheLastElementSizeBiggerThan2 () {
        Item item = first.prev.item;
        first.prev = first.prev.prev;
        first.prev.next = null;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void debug() {
        Iterator<Item> deckIter = iterator();
        while(deckIter.hasNext()) {
            System.out.println(deckIter.next());
        }
        System.out.println(isEmpty());
        System.out.println("Size:" + size());
    }

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();
        deck.size();
        deck.addLast(1);
        deck.addFirst(2);
        deck.addFirst(3);
        deck.addLast(4);
        deck.debug();
        deck.removeFirst();
        deck.removeFirst();
        deck.removeLast();
        deck.debug();
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(0);
        deque.addLast(1);
        deque.removeLast();
        deque.addLast(3);
        deque.removeLast();
        deque.removeLast();
        deck = new Deque<>();
        deck.addFirst(4);
        deck.debug();
        deck = new Deque<>();
        deck.addLast(6);
        deck.debug();
        deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.isEmpty();
        deque.removeLast();
        deque.isEmpty();
        deque.addFirst(7);
        System.out.println(deque.removeLast());
    }
}
