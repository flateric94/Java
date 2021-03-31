/**
 * TP 8: Programmation dynamique - distance d’alignement
 */



public class Distance {
    /* A COMPLETER: attributs privés */
    public enum Fleche { HAUT, HAUT_GAUCHE, GAUCHE }
    private String word1;
    private String word2;
    private int[][] Mat;
    private Fleche[][] Mat_fleche;

    /**
     * Constructeur
     */
    public Distance(String word1, String word2) {
        /* A COMPLETER */
        this.word1 = word1;
        this.word2 = word2;
        this.Mat = new int[word1.length()+1][word2.length()+1];
        this.Mat_fleche = new Fleche[word1.length()+1][word2.length()+1];


    }

    /**
     * Calcule le coût du remplacement du symbole a par b
     */
    int cost(char c, char d) {
        /* A COMPLETER */
        if (c == d){
            return 0;
        }
        return 1;
    }

    /**
     * Calcule la matrice de coûts
     */
    public void computeCosts() {
        /* A COMPLETER */
        for (int i = 1; i < this.word1.length() + 1; i++){
            for (int j = 1; j < this.word2.length() + 1; j++){
                this.Mat[i][j] = this.cost(this.word1.charAt(i-1),this.word2.charAt(j-1));
                if (i == j){
                    this.Mat_fleche[i][j] = Fleche.HAUT_GAUCHE;
                }
                else if (j > i){
                    this.Mat_fleche[i][j] = Fleche.GAUCHE;
                }
                else {
                    this.Mat_fleche[i][j] = Fleche.HAUT;
                }
            }
        }
    }

    /**
     * Retourne la distance d’alignement
     */
    public int distance() {
        /* A COMPLETER */
        // Initialisation
        for (int i = 0; i < this.word1.length() + 1; i++){
            this.Mat[i][0] = i;
            this.Mat_fleche[i][0] = Fleche.HAUT;
        }
        for (int j = 0; j < this.word2.length() + 1; j++){
            this.Mat[0][j] = j;
            this.Mat_fleche[0][j] = Fleche.GAUCHE;
        }

        // Boucle principale
        for (int i = 1; i < this.word1.length() + 1; i++){
            for (int j = 1; j < this.word2.length() + 1; j++){
                /*
                this.Mat[i][j] = Math.min(this.Mat[i-1][j-1] + this.cost(this.word1.charAt(i-1),this.word2.charAt(j-1)), this.Mat[i-1][j] + 1);
                this.Mat[i][j] = Math.min(this.Mat[i][j], this.Mat[i][j-1] + 1);
                */
                int x = this.Mat[i-1][j-1] + this.cost(this.word1.charAt(i-1),this.word2.charAt(j-1));
                int y = this.Mat[i][j-1] + 1;
                int z = this.Mat[i-1][j] + 1;
                if (x <= y && x <= z){
                    this.Mat[i][j] = x;
                    this.Mat_fleche[i][j] = Fleche.HAUT_GAUCHE;
                }
                else if (y <= x && y <= z){
                    this.Mat[i][j] = y;
                    this.Mat_fleche[i][j] = Fleche.GAUCHE;
                }
                else {
                    this.Mat[i][j] = z;
                    this.Mat_fleche[i][j] = Fleche.HAUT;
                }
            }
        }

        return this.Mat[this.word1.length()][this.word2.length()];
    }

    /**
     * Retourne une représentation des deux mots alignés
     * 
     * Exemple: si les deux mots sont "and" et "ad" la méthode doit renvoyer
     * la chaine de caractères :
     *   "and
     *    a-d"
     */
    public String alignment() {
        /* A COMPLETER */
        String s1 = "";
        String s2 = ""; 
        this.distance();
        
        int i = this.word1.length();
        int j = this.word2.length();
        do {
            if (i<1 || j<1){
                break;
            }
            else if (this.Mat_fleche[i][j].equals(Fleche.HAUT_GAUCHE)){
                s1 = this.word1.charAt(i-1) + s1;
                s2 = this.word2.charAt(j-1) + s2;
                // s1 += this.word1.charAt(i-1); ne marche pas
                // s2 += this.word2.charAt(j-1); ne marche pas
                i--;
                j--;
            }
            else if (this.Mat_fleche[i][j].equals(Fleche.GAUCHE)){
                s1 = "-" + s1;
                s2 = this.word2.charAt(j-1) + s2;
                j--;
            }
            else if (this.Mat_fleche[i][j].equals(Fleche.HAUT)){
                s1 = this.word1.charAt(i-1) + s1;
                s2 = "-" + s2;
                i--;
            }
        }while(true);
        return s1+"\n"+s2;
    }

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 10;

        System.out.println("Testing the implementation:");

        Distance dist1 = new Distance("chien", "chat");
        if (dist1.distance() == 3) {
            System.out.println("\t- test  1: pass");
            score++;
        } else {
            System.out.println("\t- test  1: fail");
        }

        Distance dist2 = new Distance("book", "back");
        if (dist2.distance() == 2) {
            System.out.println("\t- test  2: pass");
            score++;
        } else {
            System.out.println("\t- test  2: fail");
        }

        Distance dist3 = new Distance("elephant", "relevant");
        if (dist3.distance() == 3) {
            System.out.println("\t- test  3: pass");
            score++;
        } else {
            System.out.println("\t- test  3: fail");
        }

        Distance dist4 = new Distance("Saturday", "Sunday");
        if (dist4.distance() == 3) {
            System.out.println("\t- test  4: pass");
            score++;
        } else {
            System.out.println("\t- test  4: fail");
        }

        Distance dist5 = new Distance("Google", "Facebook");
        if (dist5.distance() == 8) {
            System.out.println("\t- test  5: pass");
            score++;
        } else {
            System.out.println("\t- test  5: fail");
        }

        Distance dist6 = new Distance("semaine", "madeleine");
        if (dist6.distance() == 5) {
            System.out.println("\t- test  6: pass");
            score++;
        } else {
            System.out.println("\t- test  6: fail");
        }

        Distance dist7 = new Distance("potato", "patata");
        if ("potato\npatata".equals(dist7.alignment())) {
            System.out.println("\t- test  7: pass");
            score++;
        } else {
            System.out.println("\t- test  7: fail");
        }

        Distance dist8 = new Distance("man", "mad");
        if ("man\nmad".equals(dist8.alignment())) {
            System.out.println("\t- test  8: pass");
            score++;
        } else {
            System.out.println("\t- test  8: fail");
        }

        Distance dist9 = new Distance("and", "ad");
        if ("and\na-d".equals(dist9.alignment())) {
            System.out.println("\t- test  9: pass");
            score++;
        } else {
            System.out.println("\t- test  9: fail");
        }

        Distance dist10 = new Distance("wong", "wrong");
        if ("w-ong\nwrong".equals(dist10.alignment())) {
            System.out.println("\t- test 10: pass");
            score++;
        } else {
            System.out.println("\t- test 10: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
    }
}
