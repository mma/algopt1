import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Alex on 6/30/2018.
 */


public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private int count;

    private class Node {
        private Node Next;
        private Item Item;
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
        Node temp = new Node();
        temp.Item = item;
        temp.Next = first;
        first = temp;
        count++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            addFirst(item);
        } else {
            Node last = first;
            while (last.Next != null) {
                last = last.Next;
            }
            Node newLast = new Node();
            newLast.Item = item;
            newLast.Next = null;
            last.Next = newLast;
            count++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
        Item current = first.Item;
        first = first.Next;
        count--;
        return current;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (size() == 1) {
           return removeFirst();
        }else {
            Node newLast = first;
            Node oldLast = first;
            while (oldLast.Next != null) {
                newLast = oldLast;
                oldLast = oldLast.Next;
            }
            newLast.Next = null;
            count--;
            return oldLast.Item;
        }
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
            Item item = current.Item;
            current = current.Next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();
        deck.addFirst(3);
        deck.addLast(4);
        deck.addFirst(4);
        deck.addFirst(1);
        deck.addFirst(10);
        deck.addLast(99);
        deck.debug();
        deck.addLast(4);
        deck.addFirst(11);
        deck.debug();
        deck.removeFirst();
        deck.removeFirst();
        deck.removeLast();
        deck.debug();
        deck = new Deque<>();
        deck.addFirst(4);
        deck.debug();
        deck = new Deque<>();
        deck.addLast(6);
        deck.debug();
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.isEmpty();
        deque.removeLast();
        deque.isEmpty();
        deque.addFirst(7);
        System.out.println(deque.removeLast());
    }
}
