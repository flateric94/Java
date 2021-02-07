public class Test {
    
    public static void main(String[] args){
        // Joueur j1 = new Joueur("Eric");
        // System.out.println(Case.effetDepart(j1));
        // CaseRecule cr1 = new CaseRecule(-3);
        // System.out.println(cr1.effetArrive(j1));

        Jeu jeu1 = new Jeu(4);
        for (int i = 0; i<jeu1.joueurs.length; i++){
            System.out.println(jeu1.joueurs[i].nom);
        }
    }
}