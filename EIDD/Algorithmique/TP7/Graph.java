/**
 * TP 9: codage d'un graphe - classe Graph
 */

import java.util.*;


public class Graph {
    /* A COMPLETER: attributs privés */
    private boolean isDirected;
    private int vertexSize;
    private ArrayList<ArrayList<Edge>> adjacency;

    /**
     * Constructeur sans paramètre
     */
    public Graph() {
        /* A COMPLETER */
        this.isDirected = true;
        this.vertexSize = 1;
        this.adjacency = new ArrayList<ArrayList<Edge>>();
        this.adjacency.add(new ArrayList<Edge>());
        this.adjacency.get(0).add(new Edge(0,0,"racine",true));
    }

    /**
     * Constructeur avec paramètres
     */
    public Graph(boolean isDir, int n) {
        /* A COMPLETER */
        this.isDirected = isDir;
        this.vertexSize = n;
        this.adjacency = new ArrayList<ArrayList<Edge>>();
        this.adjacency.add(new ArrayList<Edge>());
    }

    /**
     * Retourne le type du graphe (true = dirigé)
     */
    public boolean getDir() {
        /* A COMPLETER */
        return this.isDirected;
    }

    /**
     * Retourne true si et seulement si i est un sommet du graphe
     */
    public boolean isVertex(int i) {
        /* A COMPLETER */
        return (i < this.vertexSize && i >= 0);
    }

    /**
     * Ajoute un sommet déconnecté à la liste des sommets et retourne son numéro
     */
    public int addVertex() {
        /* A COMPLETER */
        this.adjacency.add(new ArrayList<Edge>());
        this.vertexSize++;
        return this.vertexSize - 1;
    }

    /**
     * Retourne la liste des arcs entre src et dst (la liste doit être vide
     * s'il n'y a pas de tel arc dans le graphe)
     */
    public List<Edge> getEdges(int src, int dst) {
        /* A COMPLETER */
        List<Edge> result = new ArrayList<Edge>();
        for (int i = 0; i < this.adjacency.size(); i++){
            for (int j = 0; j < this.adjacency.get(i).size(); j++){
                if (this.adjacency.get(i).get(j).getSrc() >= src &&
                    this.adjacency.get(i).get(j).getDst() <= dst)
                {
                    result.add(this.adjacency.get(i).get(j));
                }
            }
        }
        return result;
    }

    /**
     * Ajoute un ou deux arcs (en fonction du type de graphe) entre src et dst
     * avec l'étiquette lab
     * Retourne true si l’opération a été effectuée avec succès et false sinon
     */
    public boolean addEdge(int src, int dst, String lab) {
        /* A COMPLETER */
        if(src < this.vertexSize && dst < this.vertexSize && src >=0 && dst >=0)
        {
            try{
                this.adjacency.get(src).add(new Edge(src,dst,lab,this.isDirected));
            } catch(Exception e)
            {
                this.addVertex();
                this.adjacency.get(this.adjacency.size()-1).add(new Edge(src,dst,lab,this.isDirected));
            }
            if(this.isDirected)
            {
                return true;
            }
            else
            {
                this.adjacency.get(dst).add(new Edge(dst,src,lab,this.isDirected));
                return true;
            }
        }
        return false;
    }


    /**
     * Affiche la liste d'adjacence du graphe dans une chaîne de caractères
     * (dans le même format que pour la classe Edge). Si le graphe n'est pas
	 * orienté, chaque arête ne doit apparaître qu'une seule fois. 
     */
    @Override
    public String toString() {
        /* A COMPLETER */
        String s = "";
        if (this.isDirected == true){
            for (int i = 0; i < this.adjacency.size(); i++){
                for (int j = 0; j < this.adjacency.get(i).size(); j++){
                    s += this.adjacency.get(i).get(j).toString();
                    s += "\n";
                }
            }
        }
        else {
            // construction d'un masque qu'on va remplir de false
            boolean[][] masque = new boolean[this.vertexSize][this.vertexSize];
            for (int i = 0; i < this.vertexSize; i++){
                for (int j = 0; j < this.vertexSize; j++){
                    masque[i][j] = false;
                }
            }
            for (int i = 0; i < this.adjacency.size(); i++){
                for (int j = 0; j < this.adjacency.get(i).size(); j++){
                    if (masque[i][j] == false && masque[j][i] == false){
                        s += this.adjacency.get(i).get(j).toString();
                        s += "\n";
                        masque[i][j] = true;
                    }
                }
            }
        }
        return s;
    }

    /**
     * Renvoie le chemin (s'il existe) entre n1 et n2 en effectuant un parcours
     * en largeur du graphe. La chaîne de caractère doit être de la forme 
	 * "1 -> 3 -> 5 -> 2"
     */
    public String searchPathBFS(int n1, int n2) {
        /* A COMPLETER */
        return "";
    }

    /**
     * Renvoie le chemin (s'il existe) entre n1 et n2 en effectuant un parcours
     * en profondeur du graphe
     */
    public String searchPathDFS(int n1, int n2) {
        /* A COMPLETER */
        return "";
    }

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 9;

        System.out.println("Testing the implementation:");

        Graph g1 = new Graph();
        if (g1.isVertex(0)) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        if (g1.getDir()) {
            System.out.println("\t- test 2: pass");
            score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        g1.addVertex();
        if (g1.isVertex(1)) {
            System.out.println("\t- test 3: pass");
            score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

        Graph g2 = new Graph(true, 6);
        g2.addEdge(0, 1, "a");
        g2.addEdge(0, 2, "b");
        g2.addEdge(1, 3, "c");
        g2.addEdge(1, 4, "d");

        List<Edge> edges = g2.getEdges(0, 1);
        if (edges.size() == 1) {
            System.out.println("\t- test 4: pass");
            score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        edges = g2.getEdges(4, 5);
        if (edges.size() == 0) {
            System.out.println("\t- test 5: pass");
            score++;
        } else {
            System.out.println("\t- test 5: fail");
        }

        edges = g2.getEdges(4, 15);
        if (edges.size() == 0) {
            System.out.println("\t- test 6: pass");
            score++;
        } else {
            System.out.println("\t- test 6: fail");
        }

        g2.addEdge(4, 5, "e");
        edges = g2.getEdges(4, 5);
        if (edges.size() == 1) {
            System.out.println("\t- test 7: pass");
            score++;
        } else {
            System.out.println("\t- test 7: fail");
        }

        Graph g3 = new Graph(true, 7);
        g3.addEdge(0, 1, "a");
        g3.addEdge(0, 2, "b");
        g3.addEdge(0, 3, "c");
        g3.addEdge(1, 4, "c");
        g3.addEdge(2, 5, "d");
        g3.addEdge(3, 6, "d");
        g3.addEdge(4, 5, "d");
        g3.addEdge(6, 5, "d");
		if ("0 -> 2 -> 5".equals(g3.searchPathBFS(0,5))) {
            System.out.println("\t- test 8: pass");
            score++;
        } else {
            System.out.println("\t- test 8: fail");
        }
		if ("0 -> 1 -> 4 -> 5".equals(g3.searchPathDFS(0,5))||"0 -> 3 -> 6 -> 5".equals(g3.searchPathDFS(0,5))) {
            System.out.println("\t- test 9: pass");
            score++;
        } else {
            System.out.println("\t- test 9: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
    }
}