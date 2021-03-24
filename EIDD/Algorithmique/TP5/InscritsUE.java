/**
 * Classe qui encode l'ensemble des inscrits à une unité d'enseignement (UE)
 */

import java.io.*;
import java.util.Scanner;
import java.util.HashSet;


public class InscritsUE {
    /* A COMPLETER: attributs privés */
    private HashSet<NoEtud> inscrits;

    /**
     * Constructeur
     */
    public InscritsUE() {
        /* A COMPLETER */
        this.inscrits = new HashSet<NoEtud>(100);
    }

    /**
     * Ajoute l'étudiant dont le numéro est n
     * 
     * Comme pour la classe HashSet, la méthode renvoie true si le numéro est
     * ajouté
     */
    public boolean add(NoEtud n) {
        /* A COMPLETER */
        if (this.inscrits.contains(n) || this.inscrits.size() >= 100){
            return false;
        }
        else {
            this.inscrits.add(n);
            return true;
        }
        
    }

    /**
     * Rrenvoie la chaîne de caractères avec la liste des inscrits
     */
    public String toString() {
        /* A COMPLETER */
        String str = "";
        for (NoEtud inscrit : inscrits) {
            str = str + inscrit.toString() + System.getProperty("line.separator");
        }
        return str;
    }

    /**
     * Ajoute à l'ensemble inscrits les numéros d'étudiant stockés dans le
     * fichier fname
     */
    public void loadFromFile(String fname) {
        /* A COMPLETER */
        try {
            File fich = new File(fname);
            Scanner scan = new Scanner(fich);
            while(scan.hasNextInt())
            {
                this.inscrits.add(new NoEtud(scan.nextInt()));
            }
            scan.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Pas de fichier");
        }
    }

    public static void main(String[] args) {
        /* A COMPLETER */
        InscritsUE ins = new InscritsUE();
        ins.loadFromFile("tp05.dat");
        System.out.println(ins.toString());
    }
}
