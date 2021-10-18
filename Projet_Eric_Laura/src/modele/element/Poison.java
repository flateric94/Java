/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

/**
 *
 * @author laura
 */
public class Poison extends Objet{
    int points_perdus;

    public Poison(String n, int points_perdus, int p) {
        this.nom = n;
        this.points_perdus = points_perdus;
        this.poids = p;
    }
    
    @Override
   public String toString(){
        return this.nom + " (poids : " + this.poids + ")";
    }
    
    
}
