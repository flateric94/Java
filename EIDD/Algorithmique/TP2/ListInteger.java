/**
 * Implementation d'une liste d'entiers
 */


public class ListInteger {
    /* A COMPLETER: declarer le(s) attribut(s) prive(s) de la classe */
    private CellInteger head;

    /**
     * Constructeur sans paramètres
     */
    public ListInteger() {
        /* A COMPLETER */
        this.head = null;
    }

    /**
     * Ajoute une cellule au début de la liste
     */
    public void addFirst(Integer d) {
        /* A COMPLETER */
        CellInteger c = new CellInteger(d);
        c.setNext(this.head);
        this.head = c;
    }

    /**
     * Retourne une chaîne représentant les données contenues dans la liste
     */
    public String toString() {
        /* A COMPLETER */
        String s = "";
        CellInteger c = this.head;
        while (c.getNext() != null){
            s += c.getData();
            if (c.getNext() != null){
                s += " -> ";
                c = c.getNext();
            }
        }
        s += c.getData();
        return s;
    }

    /**
     * Ajoute une cellule à la fin de la liste
     */
    public void add(Integer d) {
        /* A COMPLETER */
        CellInteger c = new CellInteger(d);
        CellInteger fin_liste = this.head;
        while(true){
            if(fin_liste.getNext() == null){
                break;
            }
            fin_liste = fin_liste.getNext();
        }
        fin_liste.setNext(c);
    }

    /**
     * Renvoie le premier entier de la liste ou null si la liste est vide
     */
    public Integer element() {
        /* A COMPLETER */
        if (this.head == null){
            return null;
        }
        return this.head.getData();
    }

    /**
     * Renvoie la taille de la liste
     */
    public int size() {
        /* A COMPLETER */
        int taille = 0;
        CellInteger n = this.head;
        while(true){
            if (n.getNext() == null){
                break;
            }
            taille++;
            n = n.getNext();
        }
        taille++;
        return taille;
    }

    /**
     * Renvoie true si la liste contient d
     */
    public boolean contains(Integer d) {
        /* A COMPLETER */
        CellInteger c = this.head;
        while(true){
            if(c.getNext() == null){
                break;
            }
            else if (c.getData() == d){
                return true;
            }
            c = c.getNext();
        }
        if (c.getData() == d){
            return true;
        }
        return false;
    }

    /**
     * Enlève la première cellule qui contient l'entier d et renvoie true si la
     * liste a changé
     */
    public boolean remove(Integer d) {
        /* A COMPLETER */
        CellInteger c = this.head;
        CellInteger previous_c = c;
        if (this.head.getData() == d){
            this.head = this.head.getNext();
            return true;
        }
        while(true){
            if (c.getNext() == null){
                break;
            }
            else {
                if (c.getData() == d){
                previous_c.setNext(c.getNext());
                return true;
                }
            }
        }
        if (c.getData() == d){
            previous_c.setNext(null);
            return true;
        }
        return false;
    }

    /**
     * Renvoie l'element en position idx
     */
    public Integer get(int idx) {
        /* A COMPLETER */
        CellInteger c = this.head;
        for (int i = 0; i < idx-1; i++){
            if (c.getNext() == null && idx-1 > 1){
                return null;
            }
            c = c.getNext();
        }
        return c.getData();
    }

    /**
     * Affecte l'element en position idx à d et renvoie l'ancienne valeur
     */
    public Integer set(int idx, Integer d) {
        /* A COMPLETER */
    	CellInteger c = this.head;
    	for (int i = 0; i < idx - 1; i++) {
			if(c.getNext() == null && idx - i > 1)
			{
				return null;
			}
			c = c.getNext();
		}
    	Integer previous_c = c.getData();
    	c.setData(d);
    	return previous_c;
    }

    /**
     * Enlève la cellule en tête de liste et la renvoie
     */
    public Integer removeFirst() {
        /* A COMPLETER */
    	Integer valeur = this.head.getData();
    	this.head = this.head.getNext();
    	return valeur;
    }

    public static void main(String[] args) {
        /* A COMPLETER */
        ListInteger l = new ListInteger();
        l.addFirst(5);
        l.addFirst(3);
        l.addFirst(6);
    	l.addFirst(7);
    	l.addFirst(8);
    	l.addFirst(9);
        System.out.println(l.toString());
        System.out.println(l.removeFirst());
    	System.out.println(l.toString());

    }
}
