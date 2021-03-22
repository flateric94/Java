/**
 * Classe qui encode un numéro d’étudiant
 */

public class NoEtud {
    /* A COMPLETER: attributs privés */
    private int s;
    private int aa;
    private int mm;
    private int xxx;

    /**
     * Construit le numéro d’étudiant dont les composants sont donnés en paramètre.
     */
    public NoEtud(int s, int aa, int mm, int xxx) {
        /* A COMPLETER */
        if (s > 4 || s < 1 || aa < 1 || aa > 99 || mm < 1 || mm > 12 || xxx < 1 || xxx > 999){
            this.s = 2;
            this.aa = 15;
            this.mm = 01;
            this.xxx = 000;
        }
        else {
            this.s = s;
            this.aa = aa;
            this.mm = mm;
            this.xxx = xxx;
        }
    }

    /**
     * Constructeur à partir d'un entier
     */
    public NoEtud(int n) {
        /* A COMPLETER */
        if (n/10000000 < 1 || n/10000000 > 4 || (n/1000)%100 < 1 || (n/1000)%100 > 12){
            this.s = 1;
            this.aa = 00;
            this.mm = 01;
            this.xxx = 000;
        }
        else {
            this.s = n/10000000;
            this.aa = (n/100000)%100;
            this.mm = (n/1000)%100;
            this.xxx = n % 1000;
        }
    }

    /* A COMPLETER: accesseurs (getters) pour tous les attributs */
    public int getS(){
        return this.s;
    }

    public int getAnnee(){
        return this.aa;
    }

    public int getMois(){
        return this.mm;
    }

    public int getX(){
        return this.xxx;
    }

    /**
     * Renvoie la chaîne de caractères correspondant au numéro d'étudiant
     * 
     * Exemple : si s=1, aa=0, mm=1 et xxx=0, alors la méthode doit renvoyer "1 00
     * 01 000"
     * 
     * Si vous avez des doutes sur le formatage de chaines de caractères :
     * https://koor.fr/Java/Tutorial/java_system_out_printf.wp
     */
    public String toString() {
        /* A COMPLETER */
        String argA, argM, argX;
        if (this.getAnnee() >= 0 && this.getAnnee() <= 9){
            argA = "0" + this.getAnnee();
        }
        else {
            argA = Integer.toString(this.getAnnee());
        }

        if (this.getMois() >= 1 && this.getMois() <= 9){
            argM = "0" + this.getMois();
        }
        else {
            argM = Integer.toString(this.getMois());
        }

        if (this.getX() >= 0 && this.getX() <= 9){
            argX = "00" + this.getX();
        }
        else if (this.getX() >= 10 && this.getX() <= 90){
            argX = "0" + this.getX();
        }
        else {
            argX = Integer.toString(this.getX());
        }

        return this.getS() + " " + argA + " " + argM + " " + argX;
    }

    /**
     * Renvoie l'entier qui représente le numéro d'étudiant.
     *
     * Exemple : si s=1, aa=0, mm=1 et xxx=0, alors la méthode doit renvoyer
     * l'entier 10001000
     */
    private int toInteger() {
        /* A COMPLETER */
        return this.getS() * 10000000 + this.getAnnee() * 100000 + this.getMois() * 1000 + this.getX();
    }

    /**
     * Fonction de hachage 1
     */
    public int hashCodeInt() {
        /* A COMPLETER */
        return this.toInteger();
    }

    /**
     * Fonction de hachage 2
     */
    public int hashCodeUniform(int m) {
        /* A COMPLETER */
        return m * this.toInteger();
    }

    /**
     * Fonction de hachage 3
     */
    public int hashCodeMod(int m) {
        /* A COMPLETER */
        return this.toInteger() % 100;
    }

    /**
     * Fonction de hachage 4
     */
    public int hashCodeGold(int m) {
        /* A COMPLETER */
        double Ak = ((Math.sqrt(5) + 1)/2) * this.toInteger();
        int ak = (int)Ak;
        double mfak = m * (Ak - ak);
        return (int)mfak;
    }

    /**
     * Redéfinition de la méthode equals pour qu'elle corresponde à l'égalité
     * structurelle (c'est-à-dire, ici : equals retourne true si et seulement si
     * elle compare deux instances de NoEtud contenant la même séquence de
     * chiffres).
     * 
     * Remarque : pour deux objets "égaux" selon la méthode equals, il faut
     * s'assurer que la méthode hashCode retourne la même valeur.
     * 
     * Pour plus d'info : https://cs108.epfl.ch/archive/16/c/CSET/CSET-notes.html
     */
    @Override
    public boolean equals(Object n) {
        return this.toInteger() == ((NoEtud) n).toInteger();
    }

    /**
     * Redéfinition de la méthode hashCode
     */
    @Override
    public int hashCode() {
        /* A COMPLETER */
        return hashCodeUniform(100);
    }

    /**
     * Teste l’implémentation
     */
    public static void testImplementation() {
        int score = 0;
        int nTests = 0;

        System.out.println("Test NoEtud:");

        // Test 1
        nTests++;
        NoEtud etud1 = new NoEtud(1, 9, 3, 111);
        if (etud1.toString().equals("1 09 03 111")) {
            System.out.println("\t- test  1: pass");
            score++;
        } else {
            System.out.println("\t- test  1: fail");
        }

        // Test 2
        nTests++;
        NoEtud etud2 = new NoEtud(5, 9, 3, 111);
        if (etud2.toString().equals("2 15 01 000")) {
            System.out.println("\t- test  2: pass");
            score++;
        } else {
            System.out.println("\t- test  2: fail");
        }

        // Test 3
        nTests++;
        NoEtud etud3 = new NoEtud(1, 9, 15, 999);
        if (etud3.toString().equals("2 15 01 000")) {
            System.out.println("\t- test  3: pass");
            score++;
        } else {
            System.out.println("\t- test  3: fail");
        }

        // Test 4
        nTests++;
        NoEtud etud4 = new NoEtud(1, 9, 12, 1982);
        if (etud4.toString().equals("2 15 01 000")) {
            System.out.println("\t- test  4: pass");
            score++;
        } else {
            System.out.println("\t- test  4: fail");
        }

        // Test 5
        nTests++;
        NoEtud etud5 = new NoEtud(10003123);
        if (etud5.toString().equals("1 00 03 123")) {
            System.out.println("\t- test  5: pass");
            score++;
        } else {
            System.out.println("\t- test  5: fail");
        }

        // Test 6
        nTests++;
        NoEtud etud6 = new NoEtud(10000123);
        if (etud6.toString().equals("1 00 01 000")) {
            System.out.println("\t- test  6: pass");
            score++;
        } else {
            System.out.println("\t- test  6: fail");
        }

        // Test 7
        nTests++;
        if (etud1.toInteger() == 10903111) {
            System.out.println("\t- test  7: pass");
            score++;
        } else {
            System.out.println("\t- test  7: fail");
        }

        // Test 8
        nTests++;
        if (etud2.toInteger() == 21501000) {
            System.out.println("\t- test  8: pass");
            score++;
        } else {
            System.out.println("\t- test  8: fail");
        }

        // Test 9
        nTests++;
        if (etud2.hashCodeInt() == 21501000) {
            System.out.println("\t- test  9: pass");
            score++;
        } else {
            System.out.println("\t- test  9: fail");
        }

        // Test 10
        nTests++;
        if (etud2.hashCodeUniform(2) == 43002000) {
            System.out.println("\t- test 10: pass");
            score++;
        } else {
            System.out.println("\t- test 10: fail");
        }

        // Test 11
        nTests++;
        if (etud5.hashCodeUniform(1) == 10003123) {
            System.out.println("\t- test 11: pass");
            score++;
        } else {
            System.out.println("\t- test 11: fail");
        }

        // Test 12
        nTests++;
        if (etud1.hashCodeMod(100) == 11) {
            System.out.println("\t- test 12: pass");
            score++;
        } else {
            System.out.println("\t- test 12: fail");
        }

        // Test 13
        nTests++;
        if (etud5.hashCodeMod(100) == 23) {
            System.out.println("\t- test 13: pass");
            score++;
        } else {
            System.out.println("\t- test 13: fail");
        }

        // Test 14
        nTests++;
        if (etud1.hashCodeGold(77) == 13) {
            System.out.println("\t- test 14: pass");
            score++;
        } else {
            System.out.println("\t- test 14: fail");
        }

        // Test 15
        nTests++;
        if (etud5.hashCodeGold(77) == 0) {
            System.out.println("\t- test 15: pass");
            score++;
        } else {
            System.out.println("\t- test 15: fail");
        }

        System.out.println("Score " + score + "/" + nTests);
    }

    public static void main(String[] args) {
        testImplementation();
    }
}
