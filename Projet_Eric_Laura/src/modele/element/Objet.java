
package modele.element;

public abstract class Objet extends Element{
    public String nom;
    public int poids;

    
    
    

    public String getNom() {
        return nom;
    }

    public int getPoids() {
        return poids;
    }
    
    
    public String effet(Personnage utilisateur, Element cible){
        return "";
    }
    
    public String ramassage(Personnage utilisateur, Element cible){
        return "";
    }

    @Override
    public String toString(){
        return this.getNom();
    }
    
}
