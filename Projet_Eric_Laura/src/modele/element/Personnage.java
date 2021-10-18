
package modele.element;

import java.util.List;
import java.util.Random;


public class Personnage extends Element  {
    public String nom;
    private int force;
    public int agilite;
    public int vie;
    public List<Objet> inventaire;
    public Arme arme;
    public Armure armure;
    public Lieu piece;
    private int poids_max = 10; //poids max que l'on peut mettre dans l'inventaire
    
    
    public Personnage(){
        this.vie = 20;
        this.arme = new Arme();
        this.armure = new Armure();
    }
    
    

    public Personnage(String n, String d, int f, int a, int v) {
        this.nom = n;
        this.description =  d;
        this.force = f;
        if (this.force < 0 || this.force > 100){
            this.force = 50;
        }
        this.agilite = a;
        if (this.agilite < 0 || this.agilite > 100){
            this.agilite = 50;
        }
        this.vie = v;
        this.arme = new Arme();
        this.armure = new Armure();
    }

    public String getNom() {
        return nom;
    }

    public int getForce() {
        return force;
    }

    public int getAgilite() {
        return agilite;
    }

    public int getVie() {
        return vie;
    }

    public List<Objet> getInventaire() {
        return inventaire;
    }

    public Lieu getLieu() {
        return piece;
    }

    public int getPoids_max() {
        return poids_max;
    }

    
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public void setPoints_de_vie(int points_de_vie) {
        this.vie = points_de_vie;
    }

    public void setInventaire(List<Objet> inventaire) {
        this.inventaire = inventaire;
    }

    public void setLieu(Lieu piece) {
        this.piece = piece;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    public void setArmure(Armure armure) {
        this.armure = armure;
    }
    
    public int getAgilitetotale(){
        return this.agilite + this.arme.bonusagilite;
    }
    
    public void ajoutePointVie(int valeur){
        this.vie += valeur;
    }
    
    @Override
    public String toString(){
        return this.getNom()+" "+this.description;
    }
    
    public String attaque(Personnage ennemi){
        double va = this.getAgilitetotale();
        double vd = ennemi.getAgilitetotale();
        double proba = va / (va+vd);
        Random random = new Random();
        double alea = Math.random();
        int points_perdus = 0;
        if(this.arme.dommagesMax != 0){
        points_perdus = random.nextInt(this.arme.dommagesMax);
        points_perdus = points_perdus + (this.getForce()/10);
        }
        if (alea < proba){
            points_perdus -= ennemi.armure.bonusprotection;
            ennemi.vie -= points_perdus;
            return this.getNom() + " attaque " + ennemi.getNom() + ": le coup est un succes, " + ennemi.getNom() + " a perdu " + points_perdus + " dommages";
        }
        return this.getNom() + " attaque " + ennemi.getNom() + ": le coup a echoue";
    }
    
    public void equipe(Arme a){
        this.arme = a;
        //this.agilite += a.bonusagilite;
    }
    
    public void equipe(Armure a){
        this.armure = a;
        //this.agilite += a.bonusprotection;
    }
    
    public void mange(Nourriture n){
        this.ajoutePointVie(n.points_gagnes);
    }
    
    public void mange(Poison n){
        this.ajoutePointVie(-n.points_perdus);
    }
    
    public String effetpoison(Poison p){
        return this.getNom() + " a perdu " + p.points_perdus + " points de vie";
    }
    
    
    
    
    public String empruntePorte(Porte p){
        if (!p.sortie.equals(this.piece)){
            this.piece = p.sortie;
            return this.getNom() +" est dans " + p.sortie;
        }
        else{
            this.piece = p.entree;
            return this.getNom() +" est dans " + p.entree;
        }
    }
  

    
    


}
