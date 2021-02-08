public class Test {

    public String ToString(){
        String retour = "Ticket no "+this.numero+" : ";
        if (this.valide == false){
            retour += "invalide";
        }
        else {
            retour += "valide";
        }
        return retour;
    }
    }
    
    public static void main(String[] args){
        // Joueur j1 = new Joueur("Eric");
        // System.out.println(Case.effetDepart(j1));
        // CaseRecule cr1 = new CaseRecule(-3);
        // System.out.println(cr1.effetArrive(j1));

        // Jeu jeu1 = new Jeu(4);
        // for (int i = 0; i<jeu1.joueurs.length; i++){
        //     System.out.println(jeu1.joueurs[i].nom);
        int [] t1 = {10,8,2};
        int [] t2 = {1,3};
        int h = new fonc(t1,t2);
        System.out.println(h);
        }
    }
