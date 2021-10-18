package tp4;

/*
 * Questions:
 * 
 * Rendez la classe Node "static"
 * 
 * 1) Est-ce que ça change quelque chose au code, et pourquoi?
 * 
 * Non parce que le code de Node ne référence pas d'attributs d'instance de DLList
 * 
 * 2) Serait-ce préférable à une version non-static, et pourquoi?
 * 
 * Oui, parce que les Node seraient plus petits en mémoire (économie de la référence à l'instance de DLList)
 * 
 * Dupliquez la classe DLIterator sous un nouveau nom: StaticDLIterator et marquez la "static":
 * 
 * 3) Pouquoi toutes les erreurs?
 * 
 * Parce qu'on ne peut plus référencer les attributs de l'instance englobante
 * 
 * 4) Que faut-il faire pour supprimer les erreurs en gardant le "static"?
 * 
 * Utiliser une instance explicitement (gardée en attribut, passée dans le constructeur)
 * 
 * 5) Peut-on définir l'itérateur comme une classe interne "locale", et pourquoi?
 * 
 * Oui, car il n'y a pas de restrictions particulières sur les classes locales. La classe doit juste 
 * être déclarée dans la méthode qui permet d'obtenir l'itérateur. La classe de l'objet rendu n'est pas 
 * visible du code "client" qui lui ne voit qu'un objet qui implémente Iterator<DLList>, ce qui n'est pas
 * un problème en soi puisque ce code ne cherche pas à appeler d'autre méthodes que les trois méthodes définies 
 * par l'interface. 
 * 
 * 6) Peut-on définir l'itérateur comme une classe interne "anonyme, et pourquoi"?
 *
 * Oui, car il n'implémente qu'une seule interface. Le fonctionnement sera autrement similaire à celui de le
 * classe locale.
 * 
 */

import java.io.PrintStream;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DLList implements Iterable<String> {

    private Node head = null;
    private Node tail = null;

    private class Node {
        private Node prev;
        private Node next;
        final String payload; // "final" so we can't move it accross nodes in reverse() and sort()

        Node(String payload, Node prev, Node next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Standard "inner member" iterator
     * @author bd
     *
     */
    private class DLIterator implements Iterator<String> {
        private Node nextReturned = head; // points to the one we return on some future next()
        boolean cannotRemove = true;

        public boolean hasNext() {
            return nextReturned != null;
        }

        public String next() {
            if (nextReturned == null) {
                throw new NoSuchElementException();
            } else {
                String returned = nextReturned.payload;
                nextReturned = nextReturned.next;
                cannotRemove = false;
                return returned;
            }
        }

        /*
         * remove() method.
         */
        public void remove() {
            if (cannotRemove) {
                throw new IllegalStateException();
            }
            cannotRemove=true; // can't remove a second time

            // We remove the node before nextReturned
            if (nextReturned == null) { 
                // reached the end, remove last
                if (head == tail) { // only one left
                    head = tail = null;
                } else {
                    tail = tail.prev; // To next-to-last
                    tail.next = null; // No successor
                }
            } else if (nextReturned.prev == head) { 
                // removing first
                head = nextReturned;
                nextReturned.prev = null;
            } else { 
                // general case: we remove the predecessor of nextReturned
                nextReturned.prev.prev.next = nextReturned; // predecessor or predecessor points to nextReturned
                nextReturned.prev = nextReturned.prev.prev; // nextReturned points to new predecessor
            }
        }
    }

    public Iterator<String> iterator() {
        return new DLIterator();
    }
    
    /**
     * A static DL iterator is possible, but there is no implicit associated DLList,
     * So we have to manage one explicitly (pass in constructor, and keep as explicit attribute)
     * @author bd
     */
    private static class StaticDLIterator implements Iterator<String> {
        DLList dl;
        private Node nextReturned;
        boolean cannotRemove = true;

        private StaticDLIterator(DLList dl) {
            this.dl = dl;
            this.nextReturned = dl.head;
        }

        public boolean hasNext() {
            return nextReturned != null;
        }

        public String next() {
            if (nextReturned == null) {
                throw new NoSuchElementException();
            } else {
                String returned = nextReturned.payload;
                nextReturned = nextReturned.next;
                cannotRemove = false; // remove() is possible now
                return returned;
            }
        }

        /*
         * remove() method.
         */
        public void remove() {
            if (cannotRemove) {
                throw new IllegalStateException();
            }
            cannotRemove=true; // can't remove a second time

            // We remove the one before
            if (nextReturned == null) { 
                // reached the end, remove last
                if (dl.head == dl.tail) { // only one left
                    dl.head = dl.tail = null;
                } else {
                    dl.tail = dl.tail.prev; // To next-to-last
                    dl.tail.next = null; // No successor
                }
            } else if (nextReturned.prev == dl.head) { 
                // removing first
                dl.head = nextReturned;
                nextReturned.prev = null;
            } else { 
                // general case: we remove the predecessor of nextReturned
                nextReturned.prev.prev.next = nextReturned; // predecessor or predecessor points to nextReturned
                nextReturned.prev = nextReturned.prev.prev; // nextReturned points to new predecessor
            }
        }
    }

    public Iterator<String> staticIterator() {
        return new StaticDLIterator(this);
    }

    /**
     * Local iterator, we make it local to the function used to obtain it:
     * 
     * @return
     */
    public Iterator<String> localIterator() {
        class LocalDLIterator implements Iterator<String> {
            private Node pointer = head; // points to the one we return on some future next()

            public boolean hasNext() {
                return pointer != null;
            }

            public String next() {
                if (pointer == null) {
                    throw new NoSuchElementException();
                } else {
                    String returned = pointer.payload;
                    pointer = pointer.next;
                    return returned;
                }
            }
            
            // remove could be same as above
            public void remove() { throw new UnsupportedOperationException(); };
        };
        return new LocalDLIterator();
    }
    
    /**
     * Anonymous iterator, generated in the method used to obtain it:
     * 
     */
    public Iterator<String> anonymousIterator() {

        // The anonymous iterator is generated on-the-fly in the return statement
        return new Iterator<String>() {
            private Node pointer = head; // points to the one we return on some future next()

            public boolean hasNext() {
                return pointer != null;
            }

            public String next() {
                if (pointer == null) {
                    throw new NoSuchElementException();
                } else {
                    String returned = pointer.payload;
                    pointer = pointer.next;
                    return returned;
                }
            }
            
            // remove() could be same as above
            public void remove() { throw new UnsupportedOperationException(); };
        };
    }
    
    public String last() {
        return tail == null ? null : tail.payload;
    }

    public String first() {
        return head == null ? null : head.payload;
    }

    public void append(String payload) {
        Node node = new Node(payload, tail, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public void push(String payload) {
        Node node = new Node(payload, null, head);
        if (tail == null) {
            tail = node;
        } else {
            head.prev = node;
        }
        head = node;
    }

    public boolean isEmpty() {
        return head==null;
    }
    
    public String pop() {
        if (head == null) { // empty list
           throw new EmptyStackException();
        }
        String s = head.payload;
        head = head.next;
        // tail remains the same until we pop the last
        if (head == null) {
            tail = null; // popped last one
        } else {
            head.prev=null; // the new first has no predecessor
        }
        return s;
    }

    public void reverse() {
        // Just swap the next/prev pointers
        // For the iteration our "next" becomes our "prev" in the loop body
        // If the list is empty (head==null) the loop is not executed
        // if the list is a singleton it swaps the null in prev with the null in next
        for (Node node = head; node != null; node = node.prev) {
            Node temp = node.prev;
            node.prev = node.next;
            node.next = temp;
        }
        // Of course tail becomes head and vice versa
        // For empty or singleton lists this swaps identical values
        Node temp=head;
        head = tail;
        tail = temp;
    }

    public void sort() {
        // Bubble sort

        // Skip the cases where the list is empty (head==tail==null)
        // or the list is a singleton (head==tail==element)
        if (head == tail) {
            return;
        }

        class StringComparatorWithNulls { // sorts null first 
            int compare(String s1,String s2) {
                if (s1==null) return -1; // s1 smaller and "stable" sort
                if (s2==null) return 1; // null smaller than anything non null
                return s1.compareTo(s2);
            }
        }
        
        var comparator=new StringComparatorWithNulls(); 
        Node last = null; // no need to sort that one
        Node n1, n2; // the two nodes in the bubble, n1 closer to head
        while (head != last) {    
            for (n1 = head, n2 = head.next; n2 != last; n1 = n2, n2 = n2.next) {
                if (comparator.compare(n1.payload,n2.payload) > 0) { // Swap the two nodes
                    
                    // The two nodes before and after the bubble
                    Node before = n1.prev;
                    Node after = n2.next;

                    // n1 becomes last
                    if (after == null) {
                        tail = n1;
                    } else {
                        after.prev = n1;
                    }
                    n1.next = after;
                    n1.prev = n2;

                    // n2 becomes first
                    if (before == null) {
                        head = n2;
                    } else {
                        before.next = n2;
                    }
                    n2.next = n1;
                    n2.prev = before;

                    // swap the nodes in the iteration variables
                    Node temp = n1;
                    n1 = n2;
                    n2 = temp;
                }
            }

            // and not n2, because the 3rd part of the for(...;...;...)
            // has already shifted the bubble
//            last = n1;
            last = n2;
        }
    }

    void dump(PrintStream s) {
        for (Node n = head; n != null; n = n.next) {
            s.println(n.payload);
        }
    }
}
