package tp4;

/*
 * TODO:
 * 
 * - Classe générique 
 * - Méthode iterator() (l'itérator doit supporter remove(), mais pas de "failfast")
 * - Methode forEach()
 * - Méthode sort() (d'ou contrainte sur le type paramétrable)
 */

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


// extends pour qu'à chaque fois qu'il y ait T il y ait Comparable<T>
public class CircularList<T extends Comparable<T>> {
    private Node head = null;

    /*
     * Une decision à prendre: comment implémente-t-on un eliste avec un seul Node?
     * Que doivent valoir ses prev/next? null ou autre chose? Dans la pratique
     * faire point prev et next sur le node lui-même permet d'éviter d'avoir à traiter
     * beaucoup de cas spéciaux dans le code.
     */
    
    private class Node implements Comparable<T> {
        private Node prev;
        private Node next;
        final T payload; 

        Node(T payload, Node prev, Node next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }

        /*
         * Un constructeur pour le premier des listes
         */
        Node(T payload) {
            this.payload = payload;
            this.prev = this;
            this.next = this;
        }
        
        // fonction qui va m'être utile pour la fonction sort() 
        @Override
        public int compareTo(T obj) {
        	return this.payload.compareTo(obj);
        }
    }

    public T last() {
        return head==null ? null : head.prev.payload;
    }

    public T first() {
        return head==null ? null : head.payload;
    }

    /*
     * Que ce soit push() ou append(), l'insertion d'un node est toujours entre head
     * et son prédécesseur. La différence entre push() et append() est juste sur 
     * quoi pointe head à la fin.
     */
    private Node insert(Node head, T payload) {
        Node inserted;
        if (head==null) {
            inserted=new Node(payload);
        }
        else {
            // Le nouveau node hérite du prédécesseurs de head
            // tandis que son successeur est head
            inserted=new Node(payload,head.prev,head); 
            inserted.prev.next=inserted;
            inserted.next.prev=inserted;
        }
        return inserted;
    }
    
    /*
     * Pour un append head devient successeur du node inséré
     */
    public void append(T payload) {
        head=insert(head,payload).next;
    }

    /*
     * Pour un push head devient just le node inséré
     */
    public void push(T payload) {
        head=insert(head,payload);
    }
    
    public T pop() { 
        if (head==null) {
            throw new EmptyStackException();
        }
        T payload=head.payload;
        // Cour-circuit des références
        if (head.next!=head) {
            head.next.prev=head.prev;
            head.prev.next=head.next;
            head=head.next;
        }
        else { // Dernier élément
            head=null;
        }
        return payload;
    }

    public boolean isEmpty() { 
        return head==null;
    }
     
    /*
     * Une autre solution pour size() est de garder un compteur,
     * mis à jour par push/pop/append. Sur une grosse list ce serait
     * interessant car parcourir la list pour la compter fait beaucoup
     * d'accès mémoire et pourrati entraîner de la pagination.
     */
    public int size() { 
        if (head==null) return 0;
        int size=1; // comme ça on peut démarrer sur le successeur de head
        for (Node n=head.next;n!=head;n=n.next) {
            size++;
        }
        return size;
    }
 
    /*
     *  Pour faire joli, on implémente sans utiliser size, 
     *  pour éviter le double scan.
     */
    public T get(int index) {
        if (head==null || index < 0) throw new NoSuchElementException();
        Node n=head;
        // On avance N fois en vérifiant qu'on ne repasse pas par l'origine
        for (int i=0; i<index;i++) {
            n=n.next;
            if (n==head) throw new NoSuchElementException();
        }
        return n.payload;
    }
    
    void dump(PrintStream s, String tag) {
        s.println("-------------"+tag);
        if (head == null) return;
        Node n = head;
        do {
            s.println(n.payload.toString());
            n = n.next;
        } while (n != head);
    }
    
    
    public Iterator<T> iterator() {
    	Iterator<T> it = new Iterator<T>(){
    		private Node thisNode = head;
    		private boolean testRemove = false;
    		private boolean test_isFirst = true;
    		
    		
    		public T next() {
    			// Si il n'y a pas de suivant
    			if (hasNext() == false) {
    				throw new NoSuchElementException();
    			}
    			// si il n'y a rien
    			if (thisNode == null) {
    				throw new NoSuchElementException();
    			}
    			test_isFirst = false;
    			// on stock le resultat
	    		T result = thisNode.payload;
	    		// tu passes au suivant
	    		thisNode = thisNode.next;
	    		// je suis passé au suivant, donc je peux l'enlever 
	    		testRemove = true;   				
    			return result;
    		}
    		
    		public boolean hasNext() {
    			if (thisNode == null) {
    				return false;
    			}
    			if (test_isFirst) {
    				return true;
    			}
    			if (head == thisNode) {
    				return false;
    			}
    			return true;
    		}
    		
    		public void remove() {
    			if (head == null) {
    				throw new IllegalStateException(); 
    			}
    			// premier cas : on sait qu'on ne peut pas remove, donc on renvoit exception
    			if (testRemove == false) {
    				throw new IllegalStateException();
    			}
    			testRemove = false;
    			// deuxieme cas : il n'y a qu'une seule Node, donc on met la tête à null
    			if (thisNode == thisNode.next) {
    				head = null;
    			}
    			// troisieme cas : si on est à head, alors on permute :
    			else if (thisNode.prev == head) {
	    			thisNode.prev.prev.next = thisNode;
	    			thisNode.prev = thisNode.prev.prev;
	    			head = thisNode;
	    			test_isFirst = true; //pour stopper l'itérator sinon il tourne en boucle et ça embête forEach
    			}
    			//
    			else {
    				thisNode.prev.prev.next = thisNode;
    				thisNode.prev = thisNode.prev.prev;
    			}
    		}
    	};
    	return it;
    }
    
    public void sort() {
    	// test preliminaires :
    	if (head == null) {
    		throw new IllegalStateException();
    	}
    	// si il n'y a qu'un seul élément à trier 
    	if (this.head.next == this.head) {
    		return;
    	}
    	Node thisNode = this.head;
    	// je recupère les Nodes dans une arraylist
    	ArrayList<Node> L = new ArrayList<Node>();
    	do {
    		L.add(thisNode);
    		thisNode = thisNode.next;    		
    	}
    	while(thisNode != this.head);
    	L.sort(new Comparator<Node>() {
    		@Override
    		public int compare(Node o1, Node o2) {
    			return o1.payload.compareTo(o2.payload);
    		}
    	});
    	// circularList dans laquelle mes éléments seront triés
    	CircularList<T> newCircular = new CircularList<>();
    	while(!L.isEmpty()) {
    		newCircular.append(L.remove(0).payload);
    	}
    	this.head = newCircular.head;
    }
    
    // pour mettre un lambdaa en argument, utiliser un consumer 
    public void forEach(Consumer<? super T> action) {
    	for (Iterator<T> it = this.iterator();it.hasNext();) {
    		T obj = it.next();
    		action.accept(obj);
    	}
    }
}
