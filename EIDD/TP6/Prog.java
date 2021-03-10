public class Prog{
    public static void main(String[] args){
            Bibliotheque bib = new Bibliotheque();
            Document d1 = bib.ajoutDocument("Le Lotus Bleu", "Herge");
            Document d2 = bib.ajoutDocument("Germinal", "Herge");
            Document d3 = bib.ajoutDocument("Les Miserables", "Victor Hugo");
            Utilisateur u1 = bib.ajoutUtilisateur("Pierre", "Charbit");
            Utilisateur u2 = bib.ajoutUtilisateur("Zinedine", "Zidane");
            Utilisateur u3 = bib.ajoutUtilisateur("James", "Brown");
            System.out.println("Dans la bibliotheque, on a "+bib.rechercheDocuments("rables").get(0).toString());
            System.out.println("Parmi les utilisateurs, on a "+bib.rechercheUtilisateurs("Zida").get(0).toString());
            bib.emprunte(d2, u1);
            if (bib.emprunte(d2, u3)){
                System.out.println("Emprunt impossible effectue, il y a un probleme");
            }
            else{
                System.out.println("Emprunt impossible bien gere");
            }
            if (bib.retour(d3, u1)){
                System.out.println("Retour impossible effectue, il y a un probleme");
            }
            else{
                System.out.println("Retour impossible bien gere");
            }
            bib.retour(d2,u1);
            if (bib.retour(d2, u1)){
                System.out.println("Double retour impossible effectue, il y a un probleme");
            }
            else{
                System.out.println("Double retour impossible bien gere");
        }
    }
}