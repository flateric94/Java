package tp3;

/*
 * Questions:
 * 
 * Rendez la classe Node "static"
 * 
 * 1) Est-ce que ça change quelque chose au code, et pourquoi?
 * 
 * (votre réponse ici) -> NON car dans la classe Node, il n'y a aucune référence aux attributs instancés dans CircularList.
 * 
 * 2) Serait-ce préférable à  une version non-static, et pourquoi?
 * 
 * (votre réponse ici) -> Ceci ferait économiser de la mémoire, donc OUI. 
 * 
  */

import java.io.PrintStream;
import java.util.EmptyStackException;


public class CircularList {
    private Node head = null;

    private static class Node {
        private Node prev;
        private Node next;
        final String payload; 

        Node(String payload, Node prev, Node next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
    }

    // TODO:
    public String last() {
    	// le dernier élément est le successeur du premier donc :
        return this.head.prev.payload;
    }

    // TODO:
    public String first() {
    	// le premier élément est le successeur du dernier
        return this.head.payload;
    }

    // TODO:
    public void append(String payload) {
    	// attention au cas null
    	if (this.head == null){
    		Node n_null = new Node(payload, null, null);
    		n_null.prev = n_null;
    		n_null.next = n_null;
    		this.head = n_null;
    		return;
    	}
    	// On insère un nveau node entre 2, on redefinit donc le suivant et le précédent
    	Node n = new Node(payload, this.head.prev, this.head);
    	this.head.prev.next = n;
    	this.head.prev = n;
    	}

    // TODO:
    public void push(String payload) {
    	// toujours attention au cas null
    	if (this.head == null){
    		Node n_null = new Node(payload, null, null);
    		n_null.prev = n_null;
    		n_null.next = n_null;
    		this.head = n_null;
    		return;
    	}
    	// idem que pour append sauf que n es le nveau 1er élément.
    	Node n = new Node(payload, this.head.prev, this.head);
    	this.head.prev.next = n;
    	this.head.prev = n;
    	this.head= n;
    }
    
    // TODO:
    public String pop() { 
        if (this.head == null) {
        	throw new EmptyStackException();
        }
        // objectif : supprimer head,. On garde en memoire ce qu'il y a dedans. Si il n'est plus relier à rien, java le supprime automatiquement. 
        String result = this.head.payload;
        // si c'est pas vide, je regarde qu'il n'y ait pas qu'un seul élément
        if (this.head == this.head.next) {
        	this.head = null;
        }
        else {
        	this.head.prev.next = this.head.next;
        	this.head.next.prev = this.head.prev;        	
        	this.head = this.head.next;
        }
        return result;
    }

    // TODO:
    public boolean isEmpty() {
    	String s;
        try {
        	s = this.pop();
        } catch (EmptyStackException e)
        {
        	return true;
        }
        this.push(s);
        return false;
    }
    
    public int size() {
    	if (this.isEmpty() == true) {
    		return 0;
    	}
    	int NbElem = 1;
    	Node n = this.head.prev;
    	while (n != this.head) {
    		NbElem++;
    		n = n.prev;
    	}
    	return NbElem;
    }
    
    public String get(int index) {
    	if (this.isEmpty() == true) {
    		return null;
    	}    	
    	Node n = this.head;
    	String result = n.payload;
    	for (int i = 0; i < index; i++) {
    		n = n.next;
    	}
    	return result;
    }
     
     void dump(PrintStream s) {
         if (head==null) return;
         Node n=head;
         do {
            s.println(n.payload);
            n=n.next;
         } while (n!=head);
    }
}
