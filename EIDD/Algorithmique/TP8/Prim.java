import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {
    /**
     * Retourne l'arbre couvrant minimal du graphe passé en paramètre, sous la forme
     * d'un tableau donnant le "père" de chaque sommet.
     */
    public static Integer[] spanningTree(WeightedGraph graph, int v0) {
        /* À COMPLÉTER ! */
        return null;
    }

    public static void testSpanningTree() {
        var testBench = new Object() {
            int totalTests = 0, testSuccesses = 0;

            void printTestResult(boolean r) {
                totalTests++;
                System.out.printf("Test %d: %s\n", totalTests, r ? "PASSED" : "FAILED");
                if (r)
                    testSuccesses++;
            }

            void printSummary() {
                System.out.printf("Final result: %d/%d\n", testSuccesses, totalTests);
            }

        };

        var g1 = new WeightedGraph(5);
        g1.addEdge(0, 1, 2);
        g1.addEdge(0, 2, 2);
        g1.addEdge(0, 4, 5);
        g1.addEdge(1, 3, 3);
        g1.addEdge(3, 4, 1);

        testBench.printTestResult(Arrays.deepEquals(spanningTree(g1, 1), new Integer[] { 1, null, 0, 1, 3 }));
        testBench.printTestResult(Arrays.deepEquals(spanningTree(g1, 4), new Integer[] { 1, 3, 0, 4, null }));

        testBench.printSummary();

    }

    public static void main(String[] args) {
        testSpanningTree();
    }

}
