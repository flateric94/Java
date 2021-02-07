public class Jeu {
    Case[] plateau = new Case[60];
    Joueur[] joueurs;
    int joueurCourant = 0;

    public Jeu(int n){
        this.joueurs = new Joueur[n];
        for (int i = 0; i<n; i++){
            this.joueurs[i] = new Joueur("joueur"+(i+1));
        }
    }
}

