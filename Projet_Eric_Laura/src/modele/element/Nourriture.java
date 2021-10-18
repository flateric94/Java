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
public class Nourriture extends Objet{
    public int points_gagnes;

    public Nourriture(String n, int points_gagnes, int p) {
        this.nom = n;
        this.points_gagnes = points_gagnes;
        this.poids = p;
    }
    
    @Override
    public String toString(){
        return this.nom + " (poids : " + this.poids + ")";
    }
    
    
    public String effet_nourriture(Personnage utilisateur, Element cible) {
        utilisateur.ajoutePointVie(points_gagnes);
        points_gagnes=0; //la nourriture ne peut etre utilisée deux fois
        return utilisateur.getNom() +" a mangé " + cible.getDescription();
       }
}
