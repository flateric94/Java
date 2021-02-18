import java.util.Scanner;

public class QuestionNum extends Question {
    
    int reponse;
    int marge_erreur;

    public String getIntitule() {
        return this.intitule+" (a "+this.marge+" pres)";
    }

    public boolean isBonneReponse(String r) {
        try{ 
            int rep = Integer.parseInt(r);
            if (rep > this.reponse-marge && rep < this.reponse+marge) {
                return true;
            }
            return false;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public int saisi(int p_m){
        System.out.println(this.getIntitule());
        Scanner clavier = new Scanner(System.in);
        int rep = clavier.next();
        if (this.isBonneReponse(rep)) {
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

}