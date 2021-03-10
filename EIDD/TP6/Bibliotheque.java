import java.util.ArrayList;

public class Bibliotheque{
    ArrayList<Document> catalogue;
    ArrayList<Utilisateur> utilisateurs;

    public Bibliotheque(){
        this.catalogue = new ArrayList<Document>();
        this.utilisateurs = new ArrayList<Utilisateur>();
    }

    public ArrayList<Document> getCatalogue(){
        return this.catalogue;
    }

    public ArrayList<Utilisateur> getUtilisateurs(){
        return this.utilisateurs;
    }

    //retourne le Document créé
    public Document ajoutDocument(String titre, String auteur){
        Document doc = new Document(titre, auteur);
        this.catalogue.add(doc);
        return doc;
    }

    //retourne l'Utilisateur créé
    public Utilisateur ajoutUtilisateur(String nom, String prenom){
        Utilisateur user = new Utilisateur(nom, prenom);
        this.utilisateurs.add(user);
        return user;
    }

    public boolean emprunte(Document doc, Utilisateur user){
        if (doc.isEmpruntable() == true){
            doc.setEmprunteur(user);
            user.emprunt.add(doc);
            return true;
        }
        return false;
    }

    public boolean retour(Document doc, Utilisateur user){
        doc.emprunteur = null;
        for (int i = 0; i < user.emprunt.size(); i++){
            if (user.emprunt.get(i) == doc) {
                user.emprunt.remove(doc);
                return true;
            }
        }
        return false;
    }

    //retourne la liste des documents empruntables
    public ArrayList<Document> getEmpruntables(){
        ArrayList<Document> empruntable = new ArrayList<Document>();
        for (int i = 0; i < this.catalogue.size(); i++){
            if (this.catalogue.get(i).emprunteur == null) {
                empruntable.add(this.catalogue.get(i));
                System.out.println(this.catalogue.get(i).toString());
            }
        }
        return empruntable;
    }

    //retourne les documents dont le nom ou titre contient la chaine s
    public ArrayList<Document> rechercheDocuments(String s){
        ArrayList<Document> recherche = new ArrayList<Document>();
        for (int i = 0; i < this.catalogue.size(); i++){
            if (this.catalogue.get(i).getTitre().contains(s) || this.catalogue.get(i).getAuteur().contains(s)){
                recherche.add(this.catalogue.get(i));
                System.out.println(this.catalogue.get(i).toString());
            }
        }
        return recherche;
    }

    //retourne les utilisateurs dont le nom contient la chaine s
    public ArrayList<Utilisateur> rechercheUtilisateurs(String s){
        ArrayList<Utilisateur> recherche = new ArrayList<Utilisateur>();
        for (int i = 0; i < this.utilisateurs.size(); i++){
            if (this.utilisateurs.get(i).getNom().contains(s) || this.utilisateurs.get(i).getPrenom().contains(s)){
                recherche.add(this.utilisateurs.get(i));
                System.out.println(this.utilisateurs.get(i).toString());
            }
        }
        return recherche;
    }

}