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
public class Aventure {
    Personnage heros;
    Donjon decor;

    public Aventure() {
    }
    
    

    //inutile
    public Aventure(Personnage p, Donjon d) {
        this.heros = p;
        this.decor = d;
    }

    public Personnage getHeros() {
        return heros;
    }

    public Donjon getDecor() {
        return decor;
    }
    
    
}
