/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import java.util.ArrayList;
import java.util.Scanner;
import modele.element.Arme;
import modele.element.Armure;
import modele.element.Aventure;
import modele.element.Donjon;
import modele.element.Lieu;
import modele.element.Nourriture;
import modele.element.Objet;
import modele.element.Personnage;
import modele.element.Poison;
import modele.element.Porte;

public class Prog {

    public static void test1() {
        
        
        Personnage p = new Personnage("Toto", "Un Heros", 50, 50, 20); //un constructeur nom/desc/force/agilite/charisme/poids/Vie
        Personnage q = new Personnage("Titi", "Un Vilain", 50, 50, 10);
        Arme a = new Arme("Epee", "une belle epée en fer", 10, 8, 2);//un constructeur nom/desc/bonusagilite/dommagesMax/poids
        Arme b = new Arme("dague", "une petite dague", 15, 3, 1);
        p.equipe(a);
        q.equipe(b);

        System.out.println("Combat entre " + p + " et " + q);//redefinir toString dans la classe Personnage pour un affichage de qqs infos sur le perso (nom,desc?)

        while (p.getVie() > 0 && q.getVie() > 0) {
            System.out.println(p.attaque(q));
            if (q.getVie() == 0) {
                break;
            }
            System.out.println(q.attaque(p));
            if (p.getVie() == 0) {
                break;
            }
        }
        System.out.println("Quelqu'un est mort");
        
        

    }

    public static void test2() {
        
        Lieu l1 = new Lieu("Une petite salle avec deux portes");
        Lieu l2 = new Lieu("Une grande salle avec une porte");
        Lieu l3 = new Lieu("Une chambre avec une porte");
        l1.ajoutePorteVers(l2); //attention ajoutePorteVers doit créer une porte et modifier a la fois l1 et l2
        l1.ajoutePorteVers(l3);
        Personnage q = new Personnage("Titi", "Un Vilain", 50, 50, 10);
        q.setLieu(l1);
        System.out.println(q.nom + " vous etes dans " + l1); //un toString() dans Lieu pour afficher la description
        System.out.println("Il y a " + l1.getNbPortes() + ", où voulez vous aller? (tapez un nombre entre 0 et " + l1.getNbPortes() + ")");
        int x = (new Scanner(System.in)).nextInt();
        Porte po = q.getLieu().getPorte(x);
        System.out.println(q.empruntePorte(po));
        System.out.println("Vous etes dans " + q.getLieu());
         
    }

    public static void test3() {
        /*AppliGraphique g = new AppliGraphique();
        g.setVisible(true);
        */
    }
    
   public static void test_aventure(){
      /*
       //Personnage Ericoo = new Personnage("Eric", "le boloss", 40, 20, 60);
       Personnage Ericoo = new Personnage();
       
       Ericoo.inventaire = new ArrayList<Objet>();
       Lieu debut = new Lieu();
       Lieu fin = new Lieu();
       Porte p1 = new Porte(1, debut, fin);
       debut.listePortes.add(p1);
       fin.listePortes.add((p1));
       Personnage Ogre = new Personnage("Ogre", "+ boloss qu'ericoo", 45, 20, 40);
       Arme epee = new Arme("epee", "nulle", 7, 7, 10);
       Arme hache = new Arme("hacge", "balaise", 6, 6, 2);
       Ogre.setArme(hache);
       debut.monstres = new ArrayList<Personnage>();
       debut.monstres.add(Ogre);
       Nourriture banane = new Nourriture("banane", 3, 3);
       Poison mystere = new Poison("mystere", 4, 4);
       debut.objets.add(banane);
       debut.objets.add(mystere);
       Donjon d = new Donjon(debut, fin);
       d.lieux = new ArrayList<Lieu>();
       d.lieux.add(debut);
       d.lieux.add(fin);
       debut.objets.add(epee);
       Ericoo.piece = debut;
       Aventure a = new Aventure(Ericoo, d);
       //AppliGraphique g = new AppliGraphique();
       //g.aventure = a;
       AppliGraphique g = new AppliGraphique(a);
       g.setVisible(true);
*/
       
      
           
       Personnage Ericoo = new Personnage();
       Ericoo.inventaire = new ArrayList<Objet>();
       Lieu salle0 = new Lieu("1");
       Lieu salle1 = new Lieu("2");
       Lieu salle2 = new Lieu("3");
       Lieu salle3 = new Lieu("4");
       Lieu salle4 = new Lieu();
       Lieu salle5 = new Lieu();
       Lieu salle6 = new Lieu();
       Porte porte1 = new Porte(1, salle0, salle1);
       Porte porte2 = new Porte(2, salle0, salle2);
       Porte porte3 = new Porte(3, salle1, salle3);
       Porte porte4 = new Porte(5, salle3, salle1);
       Porte porte5 = new Porte(6, salle3, salle4);
       Porte porte6 = new Porte(7, salle4, salle5);
       Porte porte7 = new Porte(8, salle5, salle6);
       salle0.listePortes.add(porte1);
       salle1.listePortes.add((porte1));
       salle2.listePortes.add(porte2);
       salle0.listePortes.add(porte2);
       salle1.listePortes.add(porte3);
       salle1.listePortes.add(porte4);
       salle3.listePortes.add(porte4);
       salle3.listePortes.add(porte5);
       salle4.listePortes.add(porte5);
       salle4.listePortes.add(porte6);
       salle5.listePortes.add(porte7);
       
       Personnage Ogre1 = new Personnage("Ogre", "faible", 30, 20, 10);
       Personnage Ogre2 = new Personnage("Troll", "fort", 40, 25, 20);
       Personnage Ogre3 = new Personnage("Boss", "", 60, 60, 40);
       Arme epee = new Arme("epee", "en bois", 7, 7, 2);
       Arme hache = new Arme("hache", "", 6, 6, 2);
       Arme hache1 = new Arme("hache", "", 8, 9, 4);
       Arme hache2 = new Arme("hache", "", 13, 14, 5);
       Arme epee2 = new Arme("epee", "en fer", 6, 8, 3);
       Ogre1.setArme(hache);
       Ogre2.setArme(hache1);
       Ogre3.setArme(hache2);
       
       salle0.objets.add(epee);
       salle1.objets.add(epee2);
       
       salle0.monstres = new ArrayList<Personnage>();
       salle3.monstres = new ArrayList<Personnage>();
       salle4.monstres = new ArrayList<Personnage>();
       salle0.monstres.add(Ogre1);
       salle3.monstres.add(Ogre2);
       salle4.monstres.add(Ogre3);
       
       Armure armure1 = new Armure("armure", "en cuir", 5, 3);
       Armure armure2 = new Armure("armure", "en bronze", 6, 6);
       salle1.objets.add(armure1);
       salle3.objets.add(armure2);
       
       
       Nourriture banane0 = new Nourriture("banane", 2, 1);
       Nourriture banane1 = new Nourriture("banane", 3, 1);
       Nourriture banane2 = new Nourriture("banane", 7, 1);
       Nourriture banane3 = new Nourriture("banane", 1, 1);
       Nourriture banane4 = new Nourriture("banane", 9, 1);
       salle0.objets.add(banane0);
       salle1.objets.add(banane1);
       salle2.objets.add(banane2);
       salle3.objets.add(banane3);
       salle4.objets.add(banane4);
       
       Nourriture mystere1 = new Nourriture("gateau mystere", 8,1);
       Nourriture mystere2 = new Nourriture("gateau mystere", 3,1);
       Poison mystere3 = new Poison("gateau mystere", 10, 1);
       Poison mystere4 = new Poison("gateau mystere", 7, 1);
       Poison mystere5 = new Poison("gateau mystere", 2, 1);
       
       salle0.objets.add(mystere1);
       salle1.objets.add(mystere5);
       salle2.objets.add(mystere3);
       salle3.objets.add(mystere2);
       salle4.objets.add(mystere4);
       
       
       Donjon d = new Donjon(salle0, salle4);
       d.lieux = new ArrayList<Lieu>();
       d.lieux.add(salle0);
       d.lieux.add(salle5);
       
       Ericoo.piece = salle0;
       Aventure a = new Aventure(Ericoo, d);
       //AppliGraphique g = new AppliGraphique();
       //g.aventure = a;
       AppliGraphique g = new AppliGraphique(a);
       g.setVisible(true);
      
      
   }

    public static void main(String[] args) {
        test_aventure();
    }

}