
package modele.element;


import java.util.ArrayList;
import java.util.List;


public class Lieu extends Element{
    public List<Porte> listePortes;
    public List<Objet> objets;
    public List<Personnage> monstres;
    
    public Lieu(){
        this.listePortes = new ArrayList<Porte>();
        this.objets = new ArrayList<Objet>();
        this.monstres = new ArrayList<Personnage>();
    }
    
    public Lieu(String d){
        this.description = d;
        this.listePortes = new ArrayList<Porte>();
        this.objets = new ArrayList<Objet>();
        this.monstres = new ArrayList<Personnage>();
    }

    public List<Porte> getListePortes() {
        return listePortes;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public List<Personnage> getMonstres() {
        return monstres;
    }
    
    
    @Override
    public String toString(){
        return "Salle " + this.description;
    }
    
    public void ajoutePorteVers(Lieu l){
        Porte p = new Porte(this,l);
        this.listePortes.add(p);
        l.listePortes.add(p);
    }
    
    public int getNbPortes(){
        return listePortes.size();
    }

    public Porte getPorte(int x){
        return listePortes.get(x);
    }
}
