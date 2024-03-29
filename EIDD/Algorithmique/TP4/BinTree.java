/**
 * Implementation des abres binaires avec valeurs entières
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BinTree {
    /* A COMPLETER: attributs privés */
	private NodeInt root;

    /**
     *  Constructeur d'arbre vide (racine = null)
     */
    public BinTree() {
        /* A COMPLETER */
        this.root = null;
    }
    
    /** 
     * Constructeur avec un noeud
     */
    public BinTree(NodeInt r) {
        /* A COMPLETER */
        this.root = r;
        if (!isBST()) {
            throw new IllegalArgumentException("Input tree is not a binary search tree!");
        }
    }
	
    /**
     * Représentation en chaîne de l'arbre.
     * Retourne :
     * - "Arbre vide" si l'abre est vide.
     * - "Arbre [ contenu ]" sinon, où "contenu" est la représentation en chaîne du nœud racine.
     */
    @Override
    public String toString() {
        /* A COMPLETER */
        return this.root.toString();
    }

    /**
     * Test si l'arbre est vide 
     */
    public boolean isEmpty() {
        /* A COMPLETER */
        return this.root == null;
    }
    
    /**
     * Calcule la hauteur de l'arbre binaire
     * -1 si arbre vide
     */
    public int height() {
	    /* A COMPLETER */
        if (this.isEmpty()){
            return -1;
        }
        else {
            return countHeight(this.root);
        }
    }

    public int countHeight(NodeInt node){
        int l=0;
        int r=0;
        if (node.isLeaf()){
            return 0;
        }
        if (node.getLson() != null){
            l=countHeight(node.getLson());
        }
        if (node.getRson() != null){
            r=countHeight(node.getRson());
        }
        return 1 + Math.max(l,r);
    }

    /**
     * Test si l'are est un ABR
     */
    public boolean isBST() {
	    /* A COMPLETER */
        boolean[] ok = new boolean[1];
        ok[0] = true;
        this.parcours(ok,this.root);
	    return ok[0];
    }

    public void parcours(boolean[] ok,NodeInt node){
        if(node.getLson()!=null){
            this.parcours(ok, node.getLson());
        }
        if((node.getLson()!=null && node.getLson().getVal()>node.getVal()) || 
                (node.getRson()!= null && node.getRson().getVal()<node.getVal())){
            ok[0]=false;
        }
        if(node.getRson()!=null){
            this.parcours(ok, node.getRson());
        }
    }

    /**
     * Calcule la cle maximale si arbre est ABR
     */
    public int getMax() {
	    /* A COMPLETER */
        return Integer.MIN_VALUE;
    }

    /**
     * Calcule le nombre de cles plus petites que x
     * -1 si arbre vide
     */
    public int less(Integer x) {
	    /* A COMPLETER */
        if(this.isEmpty()){
            return -1;
        }
        Deque<NodeInt> stack = new ArrayDeque<NodeInt>();
        stack.push(root);
        int count=0;
        while(true){
            if(stack.isEmpty()){
                return count;
            }
            NodeInt curr = stack.pop();
            if(curr.getVal()<x){
                count++;
            }
            NodeInt right = curr.getRson();
            if (right != null) {
                stack.push(right);
            }
            NodeInt left = curr.getLson();
            if (left != null) {
                stack.push(left);
            }
        }
    }

    public boolean contains(Integer x) {
        /* A COMPLETER */
        NodeInt noeud = this.root;
        while(true){
            if(x.equals(noeud.getVal())){
                return true;
            } else if(x<noeud.getVal()) {
                if(noeud.getLson()!=null){noeud=noeud.getLson();}
                else{return false;}
            } else {
                if(noeud.getRson()!=null){noeud=noeud.getRson();}
                else{return false;}
            }
        }
    }

    public static BinTree buildTree1() {
        NodeInt one = new NodeInt(1);
        NodeInt three = new NodeInt(3);
        NodeInt two = new NodeInt(2, one, three);
        return new BinTree(two);
    }

    public static BinTree buildTree2() {
        return new BinTree(new NodeInt(1));
    }

    public static BinTree buildTree3() {
        NodeInt one = new NodeInt(1);
        NodeInt three = new NodeInt(3, one, null);
        NodeInt eight = new NodeInt(8, three, null);
        NodeInt ten = new NodeInt(10, eight, null);
        return new BinTree(ten);
    }

    /**
     * Tests unitaires de la classe
     */
    public static void testIsEmpty() {
        System.out.println("Test isEmpty:");
        int score = 0;
        int nTests = 2;

        // Test 1
        BinTree t8 = new BinTree(NodeInt.buildNodeInt8());
        if (!t8.isEmpty()) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        // Test 2
        BinTree empty = new BinTree();
        if (empty.isEmpty()) {
            System.out.println("\t- test 2: pass");
            score++;
        } else {
            System.out.println("\t- test 2: fail");
        }	    

	    // Score final
    	System.out.println("Test isEmpty: score " + score + "/" + nTests);
    }
    
    public static void testHeight() {
        System.out.println("Test height:");
        int score = 0;
        int nTests = 4;

	    // Test 1 
        BinTree t8 = new BinTree(NodeInt.buildNodeInt8());
    	if (t8.height() == 2) {
	        System.out.println("\t- test 1: pass");
    	    score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
        BinTree t1 = buildTree1();
    	if (t1.height() == 1) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

	    // Test 3 
        BinTree t2 = buildTree2();
    	if (t2.height() == 0) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
        BinTree t3 = buildTree3();
    	if (t3.height() == 3) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

	    // Score final
    	System.out.println("Test height: score " + score + "/" + nTests);
    }
    
    public static void testIsBST() {
        System.out.println("Test isBST:");
        int score = 0;
        int nTests = 2;

	    // Test 1
        BinTree t8 = new BinTree(NodeInt.buildNodeInt8());
        if (t8.isBST()) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        // Test 2
        NodeInt two = new NodeInt(2);
        NodeInt one = new NodeInt(1, two, null);
        try {
            new BinTree(one);
            System.out.println("\t- test 2: fail");
        } catch(Exception e) {
            System.out.println("\t- test 2: pass");
            score++;
        }

	    // Score final
        System.out.println("Test isBST: score " + score + "/" + nTests);
    }
    
    public static void testGetMax() {
        System.out.println("Test getMax:");
        int score = 0;
        int nTests = 4;

        // Test 1
        BinTree t8 = new BinTree(NodeInt.buildNodeInt8());
        if (t8.getMax() == 10) {
            System.out.println("\t- test 1: pass"); 
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
        BinTree t1 = buildTree1();
    	if (t1.getMax() == 3) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

	    // Test 3 
        BinTree t2 = buildTree2();
    	if (t2.getMax() == 1) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
        BinTree t3 = buildTree3();
    	if (t3.getMax() == 10) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        // Score final
        System.out.println("Test getMax: score " + score + "/" + nTests);
    }

    public static void testLess() {
        System.out.println("Test less:");
        int score = 0;
        int nTests = 4;
        
        // Test 1
        BinTree t8 = new BinTree(NodeInt.buildNodeInt8());
        if (t8.less(8) == 3) {
            System.out.println("\t- test 1: pass");
            score ++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
    	if (t8.less(5) == 1) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        // Test 3
        BinTree t1 = buildTree1();
    	if (t1.less(2) == 1) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
    	if (t1.less(3) == 2) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        // Score final
        System.out.println("Test less: score " + score + "/" + nTests);
    }

    public static void testContains() {
        System.out.println("Test contains:");
        int score = 0;
        int nTests = 4;
        
        // Test 1
        BinTree t8 = new BinTree(NodeInt.buildNodeInt8());
        if (t8.contains(8)) {
            System.out.println("\t- test 1: pass");
            score ++;
        } else {
            System.out.println("\t- test 1: fail");
        }

	    // Test 2 
    	if (t8.contains(6)) {
	        System.out.println("\t- test 2: pass");
    	    score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        // Test 3
    	if (t8.contains(9)) {
	        System.out.println("\t- test 3: pass");
    	    score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

	    // Test 4
    	if (!t8.contains(77)) {
	        System.out.println("\t- test 4: pass");
    	    score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        // Score final
        System.out.println("Test less: score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testIsEmpty();
        testHeight();
        testIsBST();
        testGetMax();
        testLess();
        testContains();
    }
}

