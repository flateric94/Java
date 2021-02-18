public class Question {
    String intitule;
    int valeur;

    public Question(String s, int n) {
        this.intitule = s;
        this.valeur = n;

    }

    public String getIntitule() {
        return this.intitule;
    }

    public int getValeur() {
        return this.valeur;
    }

    public boolean isBonneReponse(String s) {
        return true;
    }

}