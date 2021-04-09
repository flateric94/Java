
/**
 * TP 8: codage d'un graphe non orienté- classe Graph
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {
    private int vertexSize;
    private final List<Set<Integer>> adjacency; // matrice d'adjacence maintenue symétrique

    /**
     * Constructeur sans paramètre
     */
    public Graph() {
        this(1);
    }

    /**
     * Constructeur avec paramètres
     */
    public Graph(int n) {
        this.adjacency = new ArrayList<>(n);
                /* remarque : le paramètre du constructeur n'est qu'une capacité initiale, ce n'est pas la taille de la liste représentée (c'est donc juste de l'optimisation de passer ce paramètre maintenant) */
        this.vertexSize = n;
        for (int i = 0; i < n; i++){
			var outgoingEdges = new HashSet<Integer>();
			adjacency.add(outgoingEdges);
		}
    }

    /**
     * Retourne la taille du graphe (en nombre de sommets)
     */
    public int getVertexSize() {
        return vertexSize;
    }

    /**
     * Retourne true si et seulement si i est un sommet du graphe
     */
    public boolean isVertex(int i) {
        return i >= 0 && i < vertexSize;
    }

    /**
     * Ajoute un sommet déconnecté à la liste des sommets et retourne son numéro
     */
    public int addVertex() {
        var outgoingEdges = new HashSet<Integer>();
        adjacency.add(outgoingEdges);
        return vertexSize++;
    }

    /**
     * Retourne si un arc est présent entre src et dst.
     */
    public boolean isEdge(int src, int dst) {
        if (isVertex(src) && isVertex(dst)) {
            return adjacency.get(src).contains(dst);
            // get en O(1), contains en O(log(d)) (dépend de l'implémentation de hashCode et de HashMap)
        } else
            return false;
    }

    /**
     * Ajoute un arc entre src et dst. Retourne true si l’opération a été effectuée
     * avec succès et false sinon
     */
    public boolean addEdge(int src, int dst) {
        if (isVertex(src) && isVertex(dst)) {
            adjacency.get(src).add(dst);
            adjacency.get(dst).add(src);
            return true;
        } else
            return false;
    }

    /**
     * Retourne les voisins du sommet src
     */
    public Set<Integer> getNeighbors(int src) {
        return adjacency.get(src);
    }

    /**
     * Affiche la liste d'adjacence du graphe dans une chaîne de caractères.
     */
    @Override
    public String toString() {
        return IntStream.range(0, vertexSize).mapToObj(x -> x)
                .flatMap(
                    src -> adjacency.get(src).stream()
                    .filter(dst -> src <= dst)
                    .map(dst -> String.format("(%s--%s)[%s]"))
                    )
                .collect(Collectors.joining(", "));
    }

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 7;

        System.out.println("Testing the implementation:");

        Graph g1 = new Graph();
        if (g1.isVertex(0)) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        g1.addVertex();
        if (g1.isVertex(1)) {
            System.out.println("\t- test 2: pass");
            score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        Graph g2 = new Graph(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 3);
        g2.addEdge(1, 4);

        if (g2.isEdge(0, 1)) {
            System.out.println("\t- test 3: pass");
            score++;
        } else {
            System.out.println("\t- test 3: fail");
        }

        if (g2.isEdge(1,0)) {
            System.out.println("\t- test 4: pass");
            score++;
        } else {
            System.out.println("\t- test 4: fail");
        }

        if (!g2.isEdge(4,5)) {
            System.out.println("\t- test 5: pass");
            score++;
        } else {
            System.out.println("\t- test 5: fail");
        }

        if (!g2.isEdge(4,15)) {
            System.out.println("\t- test 6: pass");
            score++;
        } else {
            System.out.println("\t- test 6: fail");
        }

        g2.addEdge(4, 5);
        if (g2.isEdge(4,5)) {
            System.out.println("\t- test 7: pass");
            score++;
        } else {
            System.out.println("\t- test 7: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
    }
}