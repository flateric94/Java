/**
 * Implementation de la classe Flag (drapeau)
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
 * Classe drapeau
 */
public class Flag {
    /* A COMPLETER: declarer le(s) attribut(s) prive(s) de la classe */
    private Color[] tabcolor;

    /**
     * Constructeur sans paramètres
     */
    public Flag() {
        /* A COMPLETER */
        this.tabcolor = new Color[3];
        this.tabcolor[0] = Color.BLUE;
        this.tabcolor[1] = Color.WHITE;
        this.tabcolor[2] = Color.RED;
    }

    /**
     * Constructeur d'un drapeau avec n couleurs (initialisés à WHITE)
     */
    public Flag(int n) {
        /* A COMPLETER */
        this.tabcolor = new Color[n];
        for (int i = 0; i<tabcolor.length; i++){
            this.tabcolor[i] = Color.WHITE;
        }
    }

    /**
     * Renvoie la couleur à la position i du tableau
     */
    public Color getAt(int i) {
        /* A COMPLETER */
        if (i < this.tabcolor.length){
            return this.tabcolor[i];
        }
        return null;
    }

    /**
     * Change la couleur à la position i du tableau
     */
    public void setAt(int i, Color c) {
        /* A COMPLETER */
        this.tabcolor[i] = c;
    }

    /**
     * Remplit le tableau avec des couleurs aléatoires
     */
    public void fillRandom() {
        /* A COMPLETER */
        /*
           Pour générer un nombre aléatoire vous pouvez utiliser la classe Random :
             https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
           Regardez en particulier la méthode nextInt()
        */
        Random rng = new Random();
        for (int i = 0; i < this.tabcolor.length; i++){
            int alea = rng.nextInt(3);
            this.tabcolor[i] = Color.values()[alea];
        }
    }

    /**
     * Retourne la représentation en chaine de caractères du tableau
     */
    @Override
    public String toString() {
        /* A COMPLETER */
        String s = "";
        for (int i = 0; i < this.tabcolor.length; i++){
            s += this.tabcolor[i] + " ";
        }
        return s;
    }

    /**
     * Trie en temps linéaire les couleurs du drapeau.
     * L’ordre à respecter est : BLUE, WHITE, RED
     */
    public void sort() {
        /* A COMPLETER */
        int nB = 0, nW = 0, nR = 0;
        for (int i = 0; i < this.tabcolor.length; i++){
            if (this.tabcolor[i] == Color.WHITE){
                nW++;
            }
            else if (this.tabcolor[i] == Color.BLUE){
                nB++;
            }
            else if (this.tabcolor[i] == Color.RED){
                nR++;
            }
        }
        for (int i = 0; i < nB; i++){
            this.tabcolor[i] = Color.BLUE;
        }
        for (int i = nB; i < this.tabcolor.length - nR; i++){
            this.tabcolor[i] = Color.WHITE;
        }
        for (int i = nW+nB; i < this.tabcolor.length; i++){
            this.tabcolor[i] = Color.RED;
        }
    }

    /**
     * Trie les couleurs du drapeau en utilisant Arrays.sort (n log n)
     * L’ordre à respecter est : BLUE, WHITE, RED
     */
    public void sortClassic() {
        /* A COMPLETER */
        Arrays.sort(this.tabcolor);
    }
    

    public static void main(String[] args) {
        /* A COMPLETER */
        Flag f = new Flag(11);
        f.fillRandom();
        String s1 = f.toString();
        System.out.println(s1);
        // f.sort();
        f.sortClassic();
        String s2 = f.toString();
        System.out.println(s2);
    }
}
