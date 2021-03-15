/**
 * Implementation des noeuds d'abres binaires avec valeurs entières
 */


public class NodeInt {
	/* A COMPLETER: attributs privés */
	private Integer val; // indique la clé entière du noeud de l'arbre
	private NodeInt leftSon, rightSon; // qui sont les r´ef´erences vers les fils (gauche et droit) du nœud

	
    /** 
     * Constructeur d'un nœud avec clé donnée
     */
    public NodeInt(Integer val) {
		/* A COMPLETER */
		this.val = val;
		this.leftSon = null;
		this.rightSon = null;
    }
	
    /**
     * Constructeur d'un nœud avec clé et fils indiqués en argument
     */
    public NodeInt(Integer val, NodeInt lson, NodeInt rson) {
		/* A COMPLETER */
		this.val = val;
		this.leftSon = lson;
		this.rightSon = rson;
    }

	/* A COMPLETER: accesseurs (getters) pour tous les attributs */
	public Integer getVal(){
		return this.val;
	}

	public NodeInt getLson(){
		return this.leftSon;
	}

	public NodeInt getRson(){
		return this.rightSon;
	}
    /**
     * Teste si noeud est une feuille
     */
    public boolean isLeaf() {
		/* A COMPLETER */
		return this.leftSon == null && this.rightSon == null;
    }

	/**
	 * Affiche le nœud et ses fils
	 * La méthode doit renvoyer "((1) 5 (6)) 8 ((9) 10 ())" pour le nœud 8
	 * de l'exemple suivant :
	 * 
	 *          8
	 *        /   \
	 *       5    10
	 *     /  \   /
	 *    1   6  9
	 */
    @Override
    public String toString() {
		/* A COMPLETER */
		char[] tab = new char[this.nombreNoeuds()*5+1];
        tab[0]=1;
        for(int i=1; i<tab.length; i++){
            tab[i]='v';
        }
        Integer[] arb = new Integer[this.nombreNoeuds()+1];
        arb[0]=1;
        this.toTab(tab,arb);
        // partie où on prend les deux tableaux pour les transformer en string
        String rep="";
        int i=1;
        arb[0]=1;
        while(tab[i+1]!='v'){
            if(tab[i]=='n'){
                rep+=arb[arb[0]].toString();
                arb[0]++;
            } else {
                rep+=tab[i];
            }
            i++;
        }
        return rep;
	}

    public int nombreNoeuds() {
        int[] count = new int[1];
        count[0] = 0;
        this.compteNoeuds(count);
        return count[0];
    }

	public void toTab(char[] tab, Integer[] arb){
        if(this.getLson() != null){
            tab[tab[0]]='(';
            tab[0]++;
            this.getLson().toTab(tab,arb);
        } else if(this.isLeaf()==false){
            tab[tab[0]]='(';
            tab[0]++;
            tab[tab[0]]=')';
            tab[0]++;
        }
        if(this.isLeaf()){
            tab[tab[0]]='n';
            tab[0]++;
            arb[arb[0]]=this.getVal();
            arb[0]++;
        } else {
            tab[tab[0]]=' ';
            tab[0]++;
            tab[tab[0]]='n';
            tab[0]++;
            arb[arb[0]]=this.getVal();
            arb[0]++;
            tab[tab[0]]=' ';
            tab[0]++;
        }
        if(this.getRson() != null){
            tab[tab[0]]='(';
            tab[0]++;
            this.getRson().toTab(tab,arb);
        } else if(this.isLeaf()==false){
            tab[tab[0]]='(';
            tab[0]++;
            tab[tab[0]]=')';
            tab[0]++;
        }
        tab[tab[0]]=')';
        tab[0]++;
    }

    /**
     * Stocke dans un tableau le nombre de noeuds déjà traversés
     */
    public void compteNoeuds(int[] c) {
    	if(this.getLson() != null){
            this.getLson().compteNoeuds(c);
        }
        c[0]++;
        if(this.getRson() != null){
            this.getRson().compteNoeuds(c);
        }
    }

    public void parcours(boolean[] ok,Integer min, Integer max){
        if(this.getLson()!=null){
            this.getLson().parcours(ok, min, max);
        }
        if(this.getVal()<=min || this.getVal()>max){
            ok[0]=false;
        }
        if(this.getRson()!=null){
            this.getRson().parcours(ok, min, max);
        }
    }

    /**
     * Teste si le nœud est la racine d'un ABR ayant des valeurs entre
	 * min et max
     */
    public boolean isIntBST(Integer min, Integer max) {
		/* A COMPLETER */
		boolean[] ok = new boolean[1];
        ok[0]=true;
        this.parcours(ok,min,max);
		return ok[0];
	}
    


    /**
     * Tests unitaires de la classe
     */

    /**
     * Construit le noeud ((1) 5 (6)) 8 ((9) 10 ())
     */ 
    public static NodeInt buildNodeInt8() {
		NodeInt one = new NodeInt(1);
		NodeInt six = new NodeInt(6);
		NodeInt nine = new NodeInt(9);
		NodeInt five = new NodeInt(5, one, six);
		NodeInt ten = new NodeInt(10, nine, null);
		NodeInt eight = new NodeInt(8, five, ten);
		return eight;
    }

    public static void testIsLeaf() {
		System.out.println("Test isLeaf:");
		int score = 0;

		// Test 1
		NodeInt n8 = buildNodeInt8();
		if (!n8.isLeaf()) {
			System.out.println("\t- test 1: pass");
			score ++;
		}
		else {
			System.out.println("\t- test 1: fail");
		}

		/* A COMPLETER: ajouter des tests */

		// Score final
		System.out.println("Test isLeaf: score " + score + "/1");
    }
    
    public static void testToString() {
		System.out.println("Test toString:");
		int score = 0;

		// Test 1
		NodeInt n8 = buildNodeInt8();
		if (n8.toString().equals("((1) 5 (6)) 8 ((9) 10 ())")) {
			System.out.println("\t- test 1: pass");
			score ++;
		}
		else {
			System.out.println("\t- test 1: fail");
		}

		/* A COMPLETER: ajouter des tests */

		// Score final
		System.out.println("Test toString: score " + score + "/1");
    }
    
    public static void testIsIntBst() {
		System.out.println("Test isIntBST:");
		int score = 0;

		// Test 1
		NodeInt n8 = buildNodeInt8();
		if (n8.isIntBST(0,20)) {
			System.out.println("\t- test 1: pass");
			score ++;
		}
		else {
			System.out.println("\t- test 1: fail");
		}

		/* A COMPLETER: ajouter des tests */

		// Score final
        System.out.println("Test isIntBST: score " + score + "/1");
    }
    
    public static void main(String[] args) {
		testIsLeaf();
		testToString();
		testIsIntBst();
    }
}

