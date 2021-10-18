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
public class Porte {
    public int num;
    public Lieu entree;
    public Lieu sortie;
    
    public Porte(Lieu l1, Lieu l2){
        this.entree = l1;
        this.sortie =l2;
    }
    
    public Porte(int n, Lieu l1, Lieu l2){
        this.num = n;
        this.entree = l1;
        this.sortie =l2;
    }
    
    @Override
    public String toString(){
        return "porte " + num;
    }
}
