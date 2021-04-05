/**
 * TP 9: codage d'un graphe - classe Edge
 */

public class Edge {
    /* A COMPLETER: attributs privés */
    private int src;
    private int dst;
    private String lab;
    private boolean isDir;

    /**
     * Constructeur
     */
    public Edge(int src, int dst, String lab, boolean b) {
        /* A COMPLETER */
        this.src = src;
        this.dst = dst;
        this.lab = lab;
        this.isDir = b;

    }

    /* A COMPLETER: méthodes d'acces et d'affectation */
    public int getSrc(){
        return src;
    }

    public int getDst(){
        return dst;
    }

    public String getLab(){
        return lab;
    }

    public boolean isIsDir(){
        return isDir;
    }

    public void setSrc(int src){
        this.src = src;
    }

    public void setdst(int dst){
        this.dst = dst;
    }

    public void setLab(String lab){
        this.lab = lab;
    }

    public void setIsDir(boolean isDir){
        this.isDir = isDir;
    }


    /**
     * Affiche l’arc avec son étiquette
     */
    public String toString() {
        /* A COMPLETER */
        if (this.isDir){
            return (this.src + " -> " + this.dst + " [label = " + this.lab + "]");
        }
        else {
            return (this.src + " -- " + this.dst + " [label = " + this.lab + "]");
        }
    }

    /**
     * Le main teste l’implémentation
     */
    public static void main(String[] args) {
        int score = 0;
        int nTests = 2;

        System.out.println("Testing the implementation:");

        Edge e = new Edge(1, 2, "a", true);
        if ("1 -> 2 [label = a]".equals(e.toString())) {
            System.out.println("\t- test 1: pass");
            score++;
        } else {
            System.out.println("\t- test 1: fail");
        }

        Edge e2 = new Edge(1, 2, "a", false);
        if ("1 -- 2 [label = a]".equals(e2.toString())) {
            System.out.println("\t- test 2: pass");
            score++;
        } else {
            System.out.println("\t- test 2: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }
}