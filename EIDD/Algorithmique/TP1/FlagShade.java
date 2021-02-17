/**
 * Implementation de la classe FlagShade
 */

import java.util.Arrays;
import java.util.Random;

/**
 * Énumération pour les couleurs
 */
enum Color {
    BLUE, WHITE, RED
}

/**
 * Classe qui représente des couleurs avec intensité
 */
class ColorShade implements Comparable<ColorShade> {
    private Color color;
    private float shade;

    public ColorShade() {
        this.color = Color.WHITE;
        this.shade = 1.0f;
    }

    public ColorShade(Color c, float s) {
        this.color = c;
        this.shade = (s <= 1.0 && s >= 0.0) ? s : ((s < -1.0 || s > 1.0) ? (1.0f / (s*s)) : -s);
    }

    public Color getColor() {
        /* A COMPLETER : le code ci-dessous n'est pas correct */
        // return this.color;
        return this.color;
    }

    public void setColor(Color c) {
        /* A COMPLETER */
        this.color = c;
    }

    public float getShade() {
        /* A COMPLETER : le code ci-dessous n'est pas correct */
        return this.shade;
    }

    public void setShade(float s) {
        /* A COMPLETER */
        this.shade = s;
    }

    /**
     * Compare deux couleurs/intensités
     * @return un entier négatif (si cet objet < c), zéro (si cet objet = c) et
     *   un entier positif (si cet objet > c)
     */
    @Override
    public int compareTo(ColorShade c) {
        if (this.color.ordinal() < c.color.ordinal()){
            return -1;
        }
        else if (this.color.ordinal() == c.color.ordinal()){
            if (this.shade < c.shade){
                return -1;
            }
            else if (this.shade == c.shade){
                return 0;
            }
            return 1;
        }
        return 1;
    }
}

/**
 * Class drapeau (les couleurs ont aussi une intensité)
 */
public class FlagShade {
    /* A COMPLETER: declarer le(s) attribut(s) prive(s) de la classe */
    private ColorShade[] tabcolorshade;
    /**
     * Constructeur sans paramètres
     */
    public FlagShade() {
        /* A COMPLETER */
        this.tabcolorshade = new ColorShade[3];
        this.tabcolorshade[0] = new ColorShade(Color.BLUE, 1.0f);
        this.tabcolorshade[1] = new ColorShade(Color.WHITE, 1.0f);
        this.tabcolorshade[2] = new ColorShade(Color.RED, 1.0f);
    }

    /**
     * Constructeur d'un drapeau avec n couleurs/intensités (initialisés à WHITE et 1)
     */
    public FlagShade(int n) {
        /* A COMPLETER */
        this.tabcolorshade = new ColorShade[n];
        for (int i=0; i<n; i++){
            this.tabcolorshade[i] = new ColorShade();
        }
    }

    /**
     * Renvoie couleur/intensité à la position i du tableau
     */
    public ColorShade getAt(int i) {
        /* A COMPLETER */
        // if (i < this.tabcolorshade.length){
        //     return this.tabcolorshade[i];
        // }
        // return null;
        if (i<this.tabcolorshade.length && i>=0){
            return this.tabcolorshade[i];
        }
        return null;
    }

    /**
     * Change la couleur/intensité à la position i du tableau
     */
    public void setAt(int i, Color c, float s) {
        /* TODO */
        if (i<this.tabcolorshade.length && i>=0){
            this.tabcolorshade[i] = new ColorShade(c,s);
        }if (i<this.tabcolorshade.length && i>=0){
            this.tabcolorshade[i] = new ColorShade(c,s);
        }
    }

    /**
     * Remplit le tableau avec des couleurs/intensités aléatoires
     */
    public void fillRandom() {
        /* A COMPLETER */
        /*
           Pour générer des nombres aléatoires vous pouvez utiliser la classe Random :
             https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
           Regardez en particulier les méthodes nextInt() et nextFloat()
        */
        Random rng = new Random();
        FlagShade palette = new FlagShade();
        for (int i=0; i<this.tabcolorshade.length; i++){
            this.setAt(i, palette.tabcolorshade[rng.nextInt(3)].getColor(), rng.nextFloat());
        }
    }

    /**
     * Retourne la représentation en chaine de caractères du tableau
     */
    @Override
    public String toString() {
        /* A COMPLETER */
        String res = "";
        for (int i=0; i<this.tabcolorshade.length; i++){
            if (this.tabcolorshade[i].getColor() == Color.BLUE){
                res += "BLUE, intensite : "+this.tabcolorshade[i].getShade();
            }
            if (this.tabcolorshade[i].getColor() == Color.WHITE){
                res += "WHITE, intensite : "+this.tabcolorshade[i].getShade();
            }
            if (this.tabcolorshade[i].getColor() == Color.RED){
                res += "RED, intensite : "+this.tabcolorshade[i].getShade();
            }
            if (i != this.tabcolorshade.length - 1){
                res += "\n";
            }
        }
        return res;
    }

    /**
     * Utilise le tri linéaire pour les couleurs et Arrays.sort pour les intensités
     */
    public void sort() {
        /* A COMPLETER */
        int nB = 0, nW = 0, nR = 0, indice;
        for (int i = 0; i < this.tabcolorshade.length; i++){
            if (this.tabcolorshade[i].getColor() == Color.WHITE){
                nW++;
            }
            else if (this.tabcolorshade[i].getColor() == Color.BLUE){
                nB++;
            }
            else if (this.tabcolorshade[i].getColor() == Color.RED){
                nR++;
            }
        }
        float[] tab_blue = new float[nB];
        indice = 0;
        for (int i=0; i<this.tabcolorshade.length; i++){
            if (this.tabcolorshade[i].getColor() == Color.BLUE){
                tab_blue[indice] = this.tabcolorshade[i].getShade();
                indice++;
            }
        }
        Arrays.sort(tab_blue);

        float[] tab_white = new float[nW];
        indice = 0;
        for (int i=0; i<this.tabcolorshade.length; i++){
            if (this.tabcolorshade[i].getColor() == Color.WHITE){
                tab_white[indice] = this.tabcolorshade[i].getShade();
                indice++;
            }
        }
        Arrays.sort(tab_white);

        float[] tab_red = new float[nR];
        indice = 0;
        for (int i=0; i<this.tabcolorshade.length; i++){
            if (this.tabcolorshade[i].getColor() == Color.RED){
                tab_red[indice] = this.tabcolorshade[i].getShade();
                indice++;
            }
        }
        Arrays.sort(tab_red);

        for (int i=0; i<nB; i++){
            tabcolorshade[i].setColor(Color.BLUE);
            tabcolorshade[i].setShade(tab_blue[i]);
        }
        for (int i=nB; i<nB+nW; i++){
            tabcolorshade[i].setColor(Color.WHITE);
            tabcolorshade[i].setShade(tab_white[i-nB]);
        }
        for (int i=nB+nW; i<this.tabcolorshade.length; i++){
            tabcolorshade[i].setColor(Color.RED);
            tabcolorshade[i].setShade(tab_red[i-nB-nW]);
        }
    }

    /**
     * Trie les couleurs/intensités du drapeau en utilisant seulement Arrays.sort
     */
    public void sortClassic() {
        /* A COMPLETER */
        Arrays.sort(this.tabcolorshade);
    }

    public static void testFlagShade() {
        int score = 0;
        int nbTests = 0;
        ColorShade cs;
        FlagShade flag;
        boolean isOK;

        System.out.println("**** Test FlagShade:");

        // Test 1 : Constructeur sans paramètres
        nbTests++;
        isOK = true;
        flag = new FlagShade();
        Color[] expected = {Color.BLUE, Color.WHITE, Color.RED};
        for (int i = 0; i < 3; ++i) {
            cs = flag.getAt(i);
            if (cs == null) {
                isOK = false;
                break;
            }
            if (cs.getColor() != expected[i]) {
                isOK = false;
                break;
            }
            if (Math.abs(cs.getShade() - 1.0f) > 1e-3) {
                isOK = false;
                break;
            }
        }
        cs = flag.getAt(-20);
        if (cs != null) isOK = false;
        cs = flag.getAt(20);
        if (cs != null) isOK = false;
        if (isOK) {
            score++;
            System.out.println("\tTest 1 passed!");
        } else {
            System.out.println("\tTest 1 failed!");
        }

        // Test 2 : Constructeur n couleurs/intensités
        nbTests++;
        isOK = true;
        flag = new FlagShade(10);
        for (int i = 0; i < 10; ++i) {
            cs = flag.getAt(i);
            if (cs == null) {
                isOK = false;
                break;
            }
            if (cs.getColor() != Color.WHITE) {
                isOK = false;
                break;
            }
            if (Math.abs(cs.getShade() - 1.0f) > 1e-3) {
                isOK = false;
                break;
            }
        }
        cs = flag.getAt(-20);
        if (cs != null) isOK = false;
        cs = flag.getAt(20);
        if (cs != null) isOK = false;
        if (isOK) {
            score++;
            System.out.println("\tTest 2 passed!");
        } else {
            System.out.println("\tTest 2 failed!");
        }

        // Test 3 : setAt et getAt
        nbTests++;

        flag.setAt(-20, Color.BLUE, 0.5f);
        isOK = true;
        for (int i = 0; i < 10; ++i) {
            cs = flag.getAt(i);
            if (cs == null) {
                isOK = false;
                break;
            }
            if (cs.getColor() != Color.WHITE) {
                isOK = false;
                break;
            }
            if (Math.abs(cs.getShade() - 1.0f) > 1e-3) {
                isOK = false;
                break;
            }
        }

        flag.setAt(20, Color.BLUE, 0.5f);
        isOK = true;
        for (int i = 0; i < 10; ++i) {
            cs = flag.getAt(i);
            if (cs == null) {
                isOK = false;
                break;
            }
            if (cs.getColor() != Color.WHITE) {
                isOK = false;
                break;
            }
            if (Math.abs(cs.getShade() - 1.0f) > 1e-3) {
                isOK = false;
                break;
            }
        }

        flag.setAt(4, Color.BLUE, 0.5f);
        cs = flag.getAt(4);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.BLUE) isOK = false;
            if (Math.abs(cs.getShade() - 0.5f) > 1e-3) isOK = false;
        }

        if (isOK) {
            score++;
            System.out.println("\tTest 3 passed!");
        } else {
            System.out.println("\tTest 3 failed!");
        }

        // Test 4 : fillRandom
        nbTests++;

        flag.fillRandom();
        isOK = true;
        for (int i = 0; i < 10; ++i) {
            cs = flag.getAt(i);
            if (cs == null) {
                isOK = false;
                break;
            }
            if (cs.getColor() == null) {
                isOK = false;
                break;
            }
            if (cs.getShade() < 0.0f || cs.getShade() > 1.0f) {
                isOK = false;
                break;
            }
        }
        if (isOK) {
            score++;
            System.out.println("\tTest 4 passed!");
        } else {
            System.out.println("\tTest 4 failed!");
        }

        // Test 5 : sort
        nbTests++;

        flag = new FlagShade(5);
        flag.setAt(0, Color.WHITE, 0.5f);
        flag.setAt(1, Color.WHITE, 0.1f);
        flag.setAt(2, Color.BLUE, 0.8f);
        flag.setAt(3, Color.RED, 0.8f);
        flag.setAt(4, Color.RED, 0.2f);
        flag.sort();
        cs = flag.getAt(0);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.BLUE) isOK = false;
            if (Math.abs(cs.getShade() - 0.8f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(1);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.WHITE) isOK = false;
            if (Math.abs(cs.getShade() - 0.1f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(2);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.WHITE) isOK = false;
            if (Math.abs(cs.getShade() - 0.5f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(3);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.RED) isOK = false;
            if (Math.abs(cs.getShade() - 0.2f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(4);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.RED) isOK = false;
            if (Math.abs(cs.getShade() - 0.8f) > 1e-3) isOK = false;
        }

        if (isOK) {
            score++;
            System.out.println("\tTest 5 passed!");
        } else {
            System.out.println("\tTest 5 failed!");
        }

        // Test 6 : sortClassic
        nbTests++;

        flag.setAt(0, Color.WHITE, 0.5f);
        flag.setAt(1, Color.WHITE, 0.1f);
        flag.setAt(2, Color.BLUE, 0.8f);
        flag.setAt(3, Color.RED, 0.8f);
        flag.setAt(4, Color.RED, 0.2f);
        flag.sortClassic();
        cs = flag.getAt(0);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.BLUE) isOK = false;
            if (Math.abs(cs.getShade() - 0.8f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(1);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.WHITE) isOK = false;
            if (Math.abs(cs.getShade() - 0.1f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(2);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.WHITE) isOK = false;
            if (Math.abs(cs.getShade() - 0.5f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(3);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.RED) isOK = false;
            if (Math.abs(cs.getShade() - 0.2f) > 1e-3) isOK = false;
        }
        cs = flag.getAt(4);
        if (cs == null) {
            isOK = false;
        } else {
            if (cs.getColor() != Color.RED) isOK = false;
            if (Math.abs(cs.getShade() - 0.8f) > 1e-3) isOK = false;
        }

        if (isOK) {
            score++;
            System.out.println("\tTest 6 passed!");
        } else {
            System.out.println("\tTest 6 failed!");
        }

        System.out.println("**** Final score: " + score + "/" + nbTests);
    }

    public static void main(String[] args) {
        testFlagShade();
        // FlagShade drapeau = new FlagShade(5);
        // drapeau.fillRandom();
        // String s = drapeau.toString();
        // System.out.println(s);
        // drapeau.sort();
        // // drapeau.sortClassic();
        // String t = drapeau.toString();
        // System.out.println("\n");
        // System.out.println(t);
    }
}
