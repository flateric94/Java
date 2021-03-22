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
    }

    /**
     * Ajoute l'étudiant dont le numéro est n
     * 
     * Comme pour la classe HashSet, la méthode renvoie true si le numéro est
     * ajouté
     */
    public boolean add(NoEtud n) {
        /* A COMPLETER */
        return false;
    }

    /**
     * Rrenvoie la chaîne de caractères avec la liste des inscrits
     */
    public String toString() {
        /* A COMPLETER */
        return "";
    }

    /**
     * Ajoute à l'ensemble inscrits les numéros d'étudiant stockés dans le
     * fichier fname
     */
    public void loadFromFile(String fname) {
        /* A COMPLETER */
    }

    public static void main(String[] args) {
        /* A COMPLETER */
    }
}
