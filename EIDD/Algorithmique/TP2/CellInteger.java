/**
 * Classe qui représente une cellule de liste d'entiers
 */


public class CellInteger {
    /* A COMPLETER: declarer le(s) attribut(s) prive(s) de la classe */
    private Integer data;
    private CellInteger next;

    /**
     * Constructeur sans paramètres
     */
    public CellInteger() {
        /* A COMPLETER */
        this.data = 0;
        this.next = null;
    }

    /**
     * Constructeur avec argument
     */
    public CellInteger(Integer val) {
        /* A COMPLETER */
        this.data = val;
        this.next = null;
    }

    /**
     * Méthode d'accès en lecture à data
     */
    public Integer getData() {
        /* A COMPLETER */
        return this.data;
    }

    /**
     * Méthode d'accès en lecture à next
     */
    public CellInteger getNext() {
        /* A COMPLETER */
        return this.next;
    }

    /**
     * Méthode d'accès en écriture pour data
     */
    public void setData(Integer val) {
        /* A COMPLETER */
        this.data = val;
    }

    /**
     * Méthode d'accès en écriture pour next
     */
    public void setNext(CellInteger n) {
        /* A COMPLETER */
        this.next = n;
    }

    /**
     * Returne une chaîne représentant le contenu de la cellule
     */
    public String toString() {
        /* A COMPLETER */
        String s = "";
        s += this.data+" -> ";
        if (this.next != null){
            s+= "suivant";
            // s += this.data+" -> ";
            // s += this.next.data;
        }
        else {
            s += "null";
        }
        return s;
    }

    public static void main(String[] args) {
        /* A COMPLETER */
        CellInteger c1 = new CellInteger(5);
        CellInteger c2 = new CellInteger(3);
        c1.next = c2;
        System.out.println(c1.toString());
        System.out.println(c2.toString());
    }
}
