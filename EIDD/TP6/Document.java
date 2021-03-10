public class Document {
    String titre;
    String auteur;
    Utilisateur emprunteur;

    public Document(String t, String a){
        this.titre = t;
        this.auteur = a;
        this.emprunteur = null;
    }

    public String getTitre(){
        return this.titre;
    }

    public String getAuteur(){
        return this.auteur;
    }

    //retourne null si le document n’est pas emprunté actuellement
    public Utilisateur getEmprunteur(){
        return this.emprunteur;
    }

    public void setEmprunteur(Utilisateur u){
        this.emprunteur = u;
    }

    //retourne true si le Document est disponible et false s’il a deja été emprunté
    public boolean isEmpruntable(){
        if (this.emprunteur == null){
            return true;
        }
        return false;
    }

    public String toString(){
        return this.titre + " de " + this.auteur;
    }

}