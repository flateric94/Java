/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laura
 */
public class Donjon {
    public List<Lieu> lieux;
    public Lieu depart;
    public Lieu arrivee;
    
    public Donjon(){
        this.lieux = new ArrayList<Lieu>();
    }

    public Donjon(Lieu depart, Lieu arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }
    
    
}
