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
public class Armure extends Equipement{
    public int bonusprotection = 0;
    
    public Armure(){
        this.nom = "sans armure";
        this.poids = 0;
    }
    
    
    public Armure(String n, String d, int b, int p){
        this.nom = n;
        this.description = d;
        this.bonusprotection = b;
        this.poids = p;
    }
    
    @Override
    public String toString(){
        return this.nom + " "+ this.description + " (bonus : "+this.bonusprotection + ", poids : " +this.poids +")";
    }
    
     public static String effet_armure(Personnage utilisateur, Armure cible){
        utilisateur.armure = cible;
        utilisateur.agilite += cible.getModificateurProtection();
        return utilisateur.getNom() + "a mis" + cible.getNom();
    }
    
    int getModificateurAgilite(){
        return 0;
    }
    
    int getModificateurForce(){
        return this.poids;
    }
    
    int getModificateurProtection(){
        return this.bonusprotection;
    }

    public String getNom() {
        return nom;
    }
    
}
