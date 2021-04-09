
/**
 * TP 8: codage d'un graphe non orienté- classe Graph
 */

import java.util.*;

public class GraphColor extends Graph {
    /* A COMPLETER: attributs privés */
	private Integer[] colors = null;

    /**
     * Constructeur sans paramètre
     */
    public GraphColor() {
		super();
        this.colors = new Integer[1];
		this.colors[0] = null;
    }

    /**
     * Constructeur avec paramètres (à compléter)
     */
    public GraphColor(int n) {
		super(n);
		this.colors = new Integer[n];
		for (int i = 0; i < this.colors.length; i++){
			this.colors[i] = null;
		}
    }
	
    /**
     * Ajoute un sommet déconnecté à la liste des sommets et retourne son numéro (à compléter)
     */
	@Override
    public int addVertex() {
		int n = super.addVertex();
		Integer[] temp = new Integer[n+1];
		for (int i = 0; i < n; i++){
			temp[i] = this.colors[i];
		}
		temp[n] = null;
		this.colors = temp;
		return n;
    }
	
    /**
     * Getter et Setter pour le tableau colors
     */
    public Integer[] getColors() {
		return this.colors;
    }
	
    public void setColors(Integer[] tab) {
		this.colors = tab;
    }
	
    /**
     * Renvoie la couleur du sommet src
     */	
	public Integer getColor(int src){
		return this.colors[src];
	}
	
    /**
     * Renvoie le nombre de voisins du sommet sélectionné.
	 * Renvoie -1 si le sommet n'est pas dans le graphe.
     */
    public int degree(int v) {
		if (super.isVertex(v) == false){
			return -1;	
		}
		else {
			return super.getNeighbors(v).size();
		}
	}
	
    /**
     * Renvoie le degré maximal du graphe
     */
    public int maxDegree() {
		int deg_max = 0;
		for (int i = 0; i < super.getVertexSize(); i++){
			if (this.degree(i) > deg_max){
				deg_max = this.degree(i);
			}
		}
		return deg_max;
    }
	
    /**
     * Vérifie que la coloration du graphe est bonne :
	 * Il faut que chaque sommet ait une couleur non null.
	 * Il faut que deux voisins aient des couleurs différentes.
	 * Toutes les couleurs doivent être comprises entre 0 et maxDegree.
     */
    public boolean goodColors() {
		int deg_max = this.maxDegree();
		for (int i = 0; i < this.colors.length; i++){
			if (this.colors[i] == null || this.colors[i] < 0 || this.colors[i] >= deg_max){
				return false;
			}
		}
		for (int i = 0; i < super.getVertexSize(); i++){
			Set<Integer> temp = super.getNeighbors(i);
			for (Integer j : temp){
				if (Objects.equals(this.colors[i], this.colors[j])){
					return false;
				}				
			}
		}
		return true;
    }
	
    /**
     * Renvoie la plus petite couleur qui n'apparaît pas dans le voisinage de src
     */
    public int minColor(int src) {
		int deg_max = this.maxDegree();
		boolean[] tab = new boolean[deg_max + 1];
		Arrays.fill(tab, false);
		Set<Integer> temp = super.getNeighbors(src);
		for (Integer j : temp){
			if (this.colors[j] != null){
				tab[this.colors[j]] = true;
			}
		}
		for (int i = 0; i < tab.length; i++){
			if (tab[i] == false){
				return i;
			}
		}
		return -1;
    }
	
    /**
     * Donne une couleur à chacun des noeuds du graphe
     */
    public void fillColors() {
		for (int i = 0; i < super.getVertexSize(); i++){
			this.colors[i] = this.minColor(i);
		}
    }

    public static void testImplementation() {
        int score = 0;
        int nTests = 11;

        System.out.println("Testing the implementation:");

        GraphColor g1 = new GraphColor();
		g1.addVertex();
		try{
			if (g1.getColor(1) == null){
				System.out.println("\t- test 1: pass");
				score++;
			}
			else{
				System.out.println("\t- test 1: fail");
			}
		}
		catch (Exception e){
			System.out.println("\t- test 1: fail");
		}
		
		g1.setColors(new Integer[]{0,1});
		try{
			if (g1.getColor(1) == 1){
				System.out.println("\t- test 2: pass");
				score++;
			}
			else{
				System.out.println("\t- test 2: fail");
			}
		}
		catch (Exception e){
			System.out.println("\t- test 2: fail");
		}
		
		GraphColor g2 = new GraphColor(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(1, 4);
		if (g2.degree(0) == 2){
			System.out.println("\t- test 3: pass");
			score++;
		}
		else{
			System.out.println("\t- test 3: fail");
		}
		if (g2.degree(5) == 0){
			System.out.println("\t- test 4: pass");
			score++;
		}
		else{
			System.out.println("\t- test 4: fail");
		}
		if (g2.degree(6) == -1){
			System.out.println("\t- test 5: pass");
			score++;
		}
		else{
			System.out.println("\t- test 5: fail");
		}
		if (g2.maxDegree() == 3){
			System.out.println("\t- test 6: pass");
			score++;
		}
		else{
			System.out.println("\t- test 6: fail");
		}
		if (!g2.goodColors()){
			System.out.println("\t- test 7: pass");
			score++;
		}
		else{
			System.out.println("\t- test 7: fail");
		}
		g2.setColors(new Integer[]{0,1,2,3,4,5});
		if (!g2.goodColors()){
			System.out.println("\t- test 8: pass");
			score++;
		}
		else{
			System.out.println("\t- test 8: fail");
		}
		g2.setColors(new Integer[]{0,1,1,0,0,2});
		if (g2.goodColors()){
			System.out.println("\t- test 9: pass");
			score++;
		}
		else{
			System.out.println("\t- test 9: fail");
		}
		g2.addEdge(3,4);
		if (!g2.goodColors()){
			System.out.println("\t- test 10: pass");
			score++;
		}
		else{
			System.out.println("\t- test 10: fail");
		}
		g2.fillColors();
		if (g2.goodColors()){
			System.out.println("\t- test 11: pass");
			score++;
		}
		else{
			System.out.println("\t- test 11: fail");
		}

        System.out.println("Score " + score + "/" + nTests);
	}


    public static void main(String[] args) {
		testImplementation();
    }
}