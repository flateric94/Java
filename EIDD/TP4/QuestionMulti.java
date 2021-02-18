import java.util.Scanner;

public class QuestionMulti extends Question {

    String[] reponse;

    public QuestionMulti(int n) {
        this.rep = new String[n];
    }

    public boolean isBonneReponse(String r) {
        for (int i=0; i<this.reponse.length; i++){
            if (r.isEquals(this.reponse[i])){
                return true;
            }
        }
        return false;
    }

    public int saisi(int p_m){
        System.out.println(this.getIntitule());
        Scanner clavier = new Scanner(System.in);
        int reponse = clavier.next();
        if (this.isBonneReponse(reponse)) {
            p_m += this.getValeur();
            System.out.println("Bonne reponse");
        }
        else {
            System.out.println("Mauvaise reponse");
        }
        System.out.println("-------------------------------");
        return p_m;
    }
}

}