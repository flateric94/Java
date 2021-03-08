/**
 * TP5: Arbres
 */


class Noeud {
    private int val; // Valeur du nœud
    private Noeud left; // Fils gauche
    private Noeud right; // Fils droit

    /**
     * Constructeur 1
     * Initialise la valeur du nœud à val et les fils gauche et droit à null
     */
    public Noeud(int val) {
        /* A COMPLETER */
        this.val = val;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructeur 2
     * Initialise la valeur du nœud et les fils du nœud
     */
    public Noeud(int val, Noeud left, Noeud right) {
        /* A COMPLETER */
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Assigne la valeur du nœud
     */
    public void setVal(int val) {
        /* A COMPLETER */
        this.val = val;
    }

    /**
     * Assigne le fils gauche
     */
    public void setLeft(Noeud noeud) {
        /* A COMPLETER */
        this.left = noeud;
    }

    /**
     * Assigne le fils droit
     */
    public void setRight(Noeud noeud) {
        /* A COMPLETER */
        this.right = noeud;
    }

    /**
     * Renvoie la valeur du nœud
     */
    public int getVal() {
        /* A COMPLETER */
        return this.val;
    }

    /**
     * Renvoie le fils gauche
     */
    public Noeud getLeft() {
        /* A COMPLETER */
        return this.left;
    }

    /**
     * Renvoie le fils droit
     */
    public Noeud getRight() {
        /* A COMPLETER */
        return this.right;
    }
	
    /**
     * Compare deux noeuds. A ne pas faire
     */
	public boolean equals(Noeud n) {
        /* A COMPLETER */
        if (this == null && n == null){
            return true;
        }
        else if (this == null && n != null || this != null && n == null){
            return false;
        }
        else if (this.val != n.val){
            return false;
        }
        else if (this.left.equals(n.left) != true) {
            return false;      
        }
        else if (this.right.equals(n.right) != true){
            return false;      
        }         
        return false;
    }
}


public class ArbreBinaire {
    private Noeud racine; // Racine de l'arbre

    /**
     * Constructeur de l'arbre vide
     */
    public ArbreBinaire() {
        this.racine = null;
    }

    /**
     * Constructeur: initialise la racine de l'arbre
     */
    public ArbreBinaire(Noeud noeud) {
        /* A COMPLETER */
        this.racine = noeud;
    }

    /**
     * Fonction visite: affiche la valeur du nœud
     */
    public void visite(Noeud noeud) {
        /* A COMPLETER */
        System.out.println(noeud.getVal());
    }

    /**
     * Parcours prefixe: méthode récursive
     */
    public void parcoursPrefixe(Noeud noeud) {
        /* A COMPLETER */
        this.visite(noeud);
        if (noeud.getLeft() != null) {
            this.parcoursPrefixe(noeud.getLeft());
        }
        if (noeud.getRight() != null) {
            this.parcoursPrefixe(noeud.getRight());
        }
    }

    /**
     * Parcours prefixe
     */
    public void parcoursPrefixe() {
        parcoursPrefixe(racine);
    }

    /**
     * Parcours postfixe: méthode récursive
     */
    public void parcoursPostfixe(Noeud noeud) {
        /* A COMPLETER */
        if (noeud.getLeft() != null) {
            this.parcoursPostfixe(noeud.getLeft());
        }
        if (noeud.getRight() != null) {
            this.parcoursPostfixe(noeud.getRight());
        }
        this.visite(noeud);
    }

    /**
     * Parcours postfixe
     */
    public void parcoursPostfixe() {
        parcoursPostfixe(racine);
    }

    /**
     * Parcours infixe: méthode récursive
     */
    public void parcoursInfixe(Noeud noeud) {
        /* A COMPLETER */
        if (noeud.getLeft() != null) {
            this.parcoursPostfixe(noeud.getLeft());
        }
        this.visite(noeud);
        if (noeud.getRight() != null) {
            this.parcoursPostfixe(noeud.getRight());
        }

    }

    /**
     * Parcours infixe
     */
    public void parcoursInfixe() {
        parcoursInfixe(racine);
    }

    /**
     * Renvoie le nombre de nœuds dans l’arbre
     */

    public int compte_Noeuds(Noeud noeud) {
        /* A COMPLETER */
        int nombrenoeud_left = 0;
        int nombrenoeud_right = 0;
        if (noeud.getLeft() != null) {
            nombrenoeud_left = compte_Noeuds(noeud.getLeft());
        }
        if (noeud.getRight() != null) {
            nombrenoeud_right = compte_Noeuds(noeud.getRight());
        }
        return 1 + nombrenoeud_right + nombrenoeud_left;
    }

    public int nombreNoeuds() {
        return compte_Noeuds(racine);
    } 

    /**
     * Renvoie la hauteur de l’arbre
     */

    public int compte_hauteur(Noeud noeud) {
        int hauteur_left = 0;
        int hauteur_right = 0;
        if (noeud.getLeft() == null && noeud.getRight() == null) {
            return 0;
        }
        if (noeud.getLeft() != null) {
            hauteur_left = compte_hauteur(noeud.getLeft());
        }
        if (noeud.getRight() != null) {
            hauteur_right = compte_hauteur(noeud.getRight());
        }
        return 1 + Math.max(hauteur_right,hauteur_left);
    }
    public int hauteur() {
        /* A COMPLETER */
        return compte_hauteur(racine);       
    }

    /**
     * Renvoie la représentation en chaine de caractères de l’arbre
     * La méthode doit renvoyer "1 2 5 7" pour l'arbre suivant:
     *         5
     *       /  \
     *      2    7
     *     /
     *    1
     */

    /**
     * Methodes permettant de mettre les valeurs des noeuds en tableau ordonné
     */
    public void tab_Arbre(int [] tab, Noeud noeud){
        if (noeud.getLeft() != null) {
            this.tab_Arbre(tab,noeud.getLeft());
        }
        tab[tab[0]] = noeud.getVal();
        tab[0]++;
        if (noeud.getRight() != null) {
            this.tab_Arbre(tab,noeud.getRight());
        }
    }

    public String toString() {
        /* A COMPLETER */
        int [] a = new int[this.nombreNoeuds() + 1];
        a[0] = 1;
        this.tab_Arbre(a,this.racine);
        int nbr;
        String res = "";
        for (int i = 1; i < a.length; i++){
            nbr = a[i];
            res += Integer.toString(nbr);
            if (i != a.length - 1) {
                res += " ";
            }
        }
        return res;
    }

    /**
     * Renvoie la somme des valeurs contenues dans les nœuds de l’arbre
     */
    public int somme_Arbre(int [] tab, Noeud noeud){
        if (noeud.getLeft() != null){
            this.somme_Arbre(tab, noeud.getLeft());
        }
        tab [1] += noeud.getVal();
        tab [0] ++;
        if (noeud.getRight() != null){
            this.somme_Arbre(tab, noeud.getRight());
        }
        return tab[1];
    }

    /**
     * Renvoie la somme des valeurs contenues dans les nœuds de l’arbre
     */
    public int somme() {
        /* A COMPLETER */
        int [] tab = new int [2];
        tab[0] = 1;
        tab[1] = 0;
        return this.somme_Arbre(tab, this.racine);
    }

    /**
     * Renvoie la somme des valeurs contenus dans les feuilles de l’arbre
     */
    public int somme_Feuilles(int [] tab, Noeud noeud){
        if (noeud.getLeft() != null){
            this.somme_Feuilles(tab, noeud.getLeft());
        }
        if (noeud.getLeft() == null && noeud.getRight() == null){
            tab [1] += noeud.getVal();
        }

        tab [0] ++;
        if (noeud.getRight() != null){
            this.somme_Feuilles(tab, noeud.getRight());
        }
        return tab[1];
    }

    /**
     * Renvoie la somme des valeurs contenus dans les feuilles de l’arbre
     */
    public int sommeFeuilles() {
        /* A COMPLETER */
        int [] tab = new int [2];
        tab[0] = 1;
        tab[1] = 0;
        return this.somme_Feuilles(tab, this.racine);
    }
	
    /**
     * Ajout: méthode récursive
     */
    public Noeud ajoutVal(Noeud noeud, int val) {
        /* A COMPLETER */
		return null;
    }

    /**
     * Ajout
     */
    public void ajout(int val) {
        this.racine = ajoutVal(this.racine, val);
    }
	
    /**
     * isDeRecherche: méthode récursive
     */
    public boolean isDeRecherche(Noeud noeud) {
        /* A COMPLETER */
		return false;
    }

    /**
     * isDeRecherche
     */
    public boolean isDeRecherche() {
        return isDeRecherche(racine);
    }

    /**
     * Constructeur à partir d'un tableau
     */
    public ArbreBinaire(int[] tab) {
        /* A COMPLETER */
    }
	
    public static void testArbre1() {
        Noeud d = new Noeud(1, null, null);
        Noeud b = new Noeud(2, d, null);
        Noeud c = new Noeud(7, null, null);
        Noeud a = new Noeud(5, b, c);
        ArbreBinaire arbre = new ArbreBinaire(a);
        Noeud d2 = new Noeud(1, null, null);
        Noeud b2 = new Noeud(2, d2, null);
        Noeud c2 = new Noeud(7, null, null);
        Noeud a2 = new Noeud(5, b2, c2);
        int score = 0;
        System.out.println("**** Test arbre1");
        if (arbre.nombreNoeuds() == 4) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.hauteur() == 2) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.toString().equals("1 2 5 7")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.somme() == 15) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 8) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.racine.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre2() {
        Noeud c = new Noeud(6, null, null);
        Noeud b = new Noeud(5, c, null);
        Noeud a = new Noeud(3, b, null);
        ArbreBinaire arbre = new ArbreBinaire(a);
        Noeud c2 = new Noeud(6, null, null);
        Noeud b2 = new Noeud(5, c2, null);
        Noeud a2 = new Noeud(3, b2, null);
        int score = 0;
        System.out.println("**** Test arbre2");
        if (arbre.nombreNoeuds() == 3) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.hauteur() == 2) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.toString().equals("6 5 3")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.somme() == 14) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 6) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.racine.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre3() {
        Noeud a = new Noeud(2);
        Noeud b = new Noeud(1);
        Noeud c = new Noeud(3);
        Noeud d = new Noeud(4);
        Noeud e = new Noeud(5);
        Noeud f = new Noeud(9);
        Noeud g = new Noeud(10);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        ArbreBinaire arbre = new ArbreBinaire(a);
        Noeud a2 = new Noeud(2);
        Noeud b2 = new Noeud(1);
        Noeud c2 = new Noeud(3);
        Noeud d2 = new Noeud(4);
        Noeud e2 = new Noeud(5);
        Noeud f2 = new Noeud(9);
        Noeud g2 = new Noeud(10);
        a2.setLeft(b2);
        a2.setRight(c2);
        b2.setLeft(d2);
        b2.setRight(e2);
        c2.setLeft(f2);
        c2.setRight(g2);
        int score = 0;
        System.out.println("**** Test arbre3");
        if (arbre.nombreNoeuds() == 7) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.toString().equals("4 1 5 2 9 3 10")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.hauteur() == 2) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.somme() == 34) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 28) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.racine.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre4() {
        Noeud a = new Noeud(2);
        ArbreBinaire arbre = new ArbreBinaire(a);
        Noeud a2 = new Noeud(2);
        int score = 0;
        System.out.println("**** Test arbre4");
        if (arbre.nombreNoeuds() == 1) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.toString().equals("2")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.hauteur() == 0) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.somme() == 2) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 2) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.racine.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }

    public static void testArbre5() {
        Noeud a = new Noeud(7);
        Noeud b = new Noeud(1);
        Noeud c = new Noeud(2);
        Noeud d = new Noeud(4);
        a.setLeft(b);
        b.setLeft(c);
        c.setRight(d);
        ArbreBinaire arbre = new ArbreBinaire(a);
        Noeud a2 = new Noeud(7);
        Noeud b2 = new Noeud(1);
        Noeud c2 = new Noeud(2);
        Noeud d2 = new Noeud(4);
        a2.setLeft(b2);
        b2.setLeft(c2);
        c2.setRight(d2);
        int score = 0;
        System.out.println("**** Test arbre5");
        if (arbre.nombreNoeuds() == 4) {
            System.out.println("\t nombreNoeuds: OK!");
            ++score;
        } else {
            System.out.println("\t nombreNoeuds: fail!");
        }
        if (arbre.toString().equals("2 4 1 7")) {
            System.out.println("\t toString: OK!");
            ++score;
        } else {
            System.out.println("\t toString: fail!");
        }
        if (arbre.hauteur() == 3) {
            System.out.println("\t hauteur: OK!");
            ++score;
        } else {
            System.out.println("\t hauteur: fail!");
        }
        if (arbre.somme() == 14) {
            System.out.println("\t somme: OK!");
            ++score;
        } else {
            System.out.println("\t somme: fail!");
        }
        if (arbre.sommeFeuilles() == 4) {
            System.out.println("\t sommeFeuilles: OK!");
            ++score;
        } else {
            System.out.println("\t sommeFeuilles: fail!");
        }
        if (arbre.racine.equals(a2)) {
            System.out.println("\t equals: OK!");
            ++score;
        } else {
            System.out.println("\t equals: fail!");
        }
        System.out.println("**** Score: " + score + "/6");
    }
	
	public static void testAjout() {
        System.out.println("**** Test fonction ajout");
		ArbreBinaire arbre = new ArbreBinaire();
		int[] tab = {4, 2, 6, 1, 7, 3, 5};
		for (int i = 0; i<tab.length; i++){
			arbre.ajout(tab[i]);
		}
		Noeud a = new Noeud(4);
        Noeud b = new Noeud(2);
        Noeud c = new Noeud(6);
        Noeud d = new Noeud(1);
        Noeud e = new Noeud(3);
        Noeud f = new Noeud(5);
        Noeud g = new Noeud(7);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
		
        if (arbre.racine.equals(a)) {
            System.out.println("\t ajout: OK!");
        } else {
            System.out.println("\t ajout: fail!");
		}	
		
	}

	public static void testDeRecherche(){
        System.out.println("**** Test isDeRecherche");
		int score = 0;
		Noeud a = new Noeud(4);
        Noeud b = new Noeud(2);
        Noeud c = new Noeud(6);
        Noeud d = new Noeud(1);
        Noeud e = new Noeud(3);
        Noeud f = new Noeud(5);
        Noeud g = new Noeud(7);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
		ArbreBinaire arbre = new ArbreBinaire(a);
        if (arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 1: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 1: fail!");
		}
		
		 a = new Noeud(4);
         b = new Noeud(2);
         c = new Noeud(6);
         d = new Noeud(3);
         e = new Noeud(1);
         f = new Noeud(5);
         g = new Noeud(7);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
		 arbre = new ArbreBinaire(a);
        if (!arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 2: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 2: fail!");
		}
		
		 a = new Noeud(4);
         b = new Noeud(3);
         c = new Noeud(6);
         d = new Noeud(1);
         e = new Noeud(7);
         f = new Noeud(2);
         g = new Noeud(8);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
		 arbre = new ArbreBinaire(a);
        if (!arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 3: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 3: fail!");
		}
		
		 a = new Noeud(4);
         b = new Noeud(4);
        a.setRight(b);
		 arbre = new ArbreBinaire(a);
        if (arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 4: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 4: fail!");
		}
		
		 a = new Noeud(4);
         b = new Noeud(4);
        a.setLeft(b);
		 arbre = new ArbreBinaire(a);
        if (!arbre.isDeRecherche()) {
            System.out.println("\t IsBinaire 5: OK!");
            ++score;
        } else {
            System.out.println("\t IsBinaire 5: fail!");
		}
        System.out.println("**** Score: " + score + "/5");
	}
	
	public static void testTableau(){
        System.out.println("**** Test constructeur Tableau");
    int[][] inputs = {
		{4, 2, 6, 1, 7, 3, 5},
		{4, 4, 4},
		{1, 2, 3, 4, 5},
		{5, 4, 3, 2, 1},
		{2, 6, 9, 1, 5, 3, 4, 6, 0, 8, 7}};
    String[] outputs = {
        "1 2 3 4 5 6 7",
        "4 4 4",
        "1 2 3 4 5",
        "1 2 3 4 5",
        "0 1 2 3 4 5 6 6 7 8 9"};		
	int nbTests = inputs.length;
       int score = 0;
       for (int i = 0; i < nbTests; i++) {
           ArbreBinaire arbre = new ArbreBinaire(inputs[i]);
           String s = arbre.toString();
           if (s.equals(outputs[i])) {
               System.out.println("Test " + i + " passed!");
               score++;
           } else {
               System.out.println("Test " + i + " failed!");
           }
       }
    System.out.println("**** Final score: " + score + "/" + nbTests);
    }
		
		

    public static void main(String[] args) {
        testArbre1();
        testArbre2();
        testArbre3();
        testArbre4();
        testArbre5();
		testAjout();
		testDeRecherche();
		testTableau();

        // test persos
        // Noeud a = new Noeud(7, null, null);
        // Noeud b = new Noeud(1, a, null);
        // Noeud c = new Noeud(2, b, null);
        // Noeud d = new Noeud(4, b, c);
        // ArbreBinaire arbre = new ArbreBinaire(a);
        // arbre.parcoursPrefixe();


    }
}