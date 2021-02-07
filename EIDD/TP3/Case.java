import java.util.Random;

public class Case {
    // int numero;

    public String effetDepart(Joueur j){
        int de = (int)(6*Math.random()+1);
        j.position += de;
        String s = "Le de donne "+de+" et vous vous retrouvez en case "+j.position;
        return s;
    }

    public String effetArrive(Joueur j){
        String s = "rien ne se passe";
        return s;
    }
}