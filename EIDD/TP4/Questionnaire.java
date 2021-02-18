import java.util.Scanner;

public class Questionnaire{

    Question[] tab;

    public Questionnaire(int n) {
        this.tab = new Question[n];
    }

    public void poser() {
        int total_point = 0;
        int points_marques = 0;
        System.out.println("\tBienvenue sur le Quizz du jour : \n");
        for (int i=0; i<this.tab.length; i++){
            System.out.print( (i+1)+") ");
            points_marques += this.tab[i].saisi(points_marques);
            total_point += this.tab[i].valeur;
        }
        System.out.println("FINI. Vous avez marque "+points_marques+" points sur "+total_point+" possibles.");
    }
}