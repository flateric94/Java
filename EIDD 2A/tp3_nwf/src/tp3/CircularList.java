package tp3;

/*
 * Questions:
 * 
 * Rendez la classe Node "static"
 * 
 * 1) Est-ce que ça change quelque chose au code, et pourquoi?
 * 
 * (votre réponse ici)
 * 
 * 2) Serait-ce préférable à une version non-static, et pourquoi?
 * 
 * (votre réponse ici)
 * 
  */

import java.io.PrintStream;
import java.util.EmptyStackException;


public class CircularList {
    private Node head = null;

    private class Node {
        private Node prev;
        private Node next;
        final String payload; 

        Node(String payload, Node prev, Node next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
        
        
    }

    // retourne la derni�re string de la liste
    public String last() {
    	
    	return head==null ? null : head.prev.payload;
    	
    }

    // retourne la première String de la liste
    public String first() {
    	
    	return head==null ? null : head.payload;
    	
    }

    // ajoute la String comme dernier élément de la liste 
    public void append(String payload) {
    	if (head == null) {
    		head = new Node(payload, head, head);
    		head.prev= head;
    	}
    	else {
	    	Node n = new Node(payload, this.head.prev, this.head);
	    	this.head.prev.next = n;
	    	this.head.prev = n;
    	}
    }

    // insère la String comme premier élément de la liste
    // rajoute la string à la dernière place puis décale la dernière place d'un rang 
    public void push(String payload) {
    	append(payload);
    	head = head.prev;
    }
    
    // supprime la première String de la liste et la retourne à l'appelant. 
    // Déclenche une java.util.EmptyStackException si la liste est vide. 
    public String pop() { 
    	if(this.isEmpty()) {
    		throw new EmptyStackException();
    	}
    	String s = head.payload;
    	head.prev.next = head.next;
    	head.next.prev = head.prev;
    	head = head.prev;
    	return s;
    }

    // retourne true si la liste est vide 
    // (et donc si pop() déclencherait une exception). 
    public boolean isEmpty() { 
    	if(head==null) {
    		return true;
    	}
    	return false;
    }
     
    // affiche sur out le contenu de la liste 
    // (rappel: System.out et System.err sont des PrintStream) 
     void dump(PrintStream s) {
         if (head==null) return;
         Node n=head;
         do {
            s.println(n.payload);
            n=n.next;
         } while (n!=head);
    }
     
     // renvoie la taille de la liste, en supposant qu'aucun autre élément n'a la même string que head
     public int size() {
    	 int i = 0;
    	 if(!this.isEmpty()) {
    		 String s = this.first();
    		 i++;
    		 if(head.next!=null) {
	    		 Node E = head.next;
	    		 while(E.payload!=s) {
	    			 i++;
	    			 E = E.next;
	    		 }
    		 }
    	 }
    	 return i;
     }
     
     public String get(int index) {
    	 if(this.isEmpty()) {
    		 return null;
    	 }
    	 int size = this.size();
    	 index = index%size;
    	 Node n = this.head;
    	 for(int i=0; i<index; i++) {
    		 n = n.next;
    	 }
    	 return n.payload;
     }
}
