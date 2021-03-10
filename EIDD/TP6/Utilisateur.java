import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Utilisateur{
    String nom;
    String prenom;
    ArrayList<Document> emprunt;

    public Utilisateur(String n, String p) {
        this.nom = n;
        this.prenom = p;
        this.emprunt = new ArrayList<Document>();
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public ArrayList<Document> getEmprunts(){
        for (int i = 0; i < this.emprunt.size(); i++){
            System.out.println(this.emprunt.get(i));
        }
        return this.emprunt;
    }

	public ArrayList<Document> getEmpruntsRetard() {
		ArrayList<Document> retour = new ArrayList<Document>();
		for (int i = 0; i < this.emprunts.size(); i++) {
			if(ChronoUnit.DAYS.between(LocalDate.now(), this.getEmprunts().get(i).getDate_retour()) < 0) {
				retour.add(this.getEmprunts().get(i));
			}
		}
		return retour;
	}

    public String toString(){
        return this.nom + " " + this.prenom;
    }

    public 
}