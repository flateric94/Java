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
public class Arme extends Equipement{
    int dommagesMax =0;
    int bonusagilite =0;
    
    public Arme(){
        this.nom = "mains nues";
        this.poids = 0;
    }
    
    public Arme(String n, String d, int b, int dm, int p){
        
        this.nom = n;
        this.description = d;
        this.bonusagilite = b;
        this.dommagesMax = dm;
        this.poids = p;
    }
    
    @Override
    public String toString(){
        return this.nom + " "+ this.description + " (bonus : "+this.bonusagilite + ", poids : " +this.poids +")";
    }

    
    /*
    public String effet_arme(Personnage utilisateur, Arme cible){
        utilisateur.arme = cible;
        utilisateur.agilite += cible.getModificateurAgilite();
        return utilisateur.getNom() + "a pris" + cible.getNom();
    }

    @Override
    public String effet(Personnage utilisateur, Element cible) {
        utilisateur.equipe(this);
        return "";
    }
    */
    
    
    int getModificateurAgilite(){
        return this.bonusagilite;
    }
    
    int getModificateurForce(){
        return this.poids;
    }
    
    int getModificateurProtection(){
        return 0;
    }

    public String getNom() {
        return nom;
    }
    
    
    
    
}
