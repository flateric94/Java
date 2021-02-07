public class CaseAvance extends Case {
    int x;

    public CaseAvance(int x){
        this.x = x;
    }

    public static String effetArrive(Joueur j) {
        j.position += this.x;
        String s = "Vous avancez de "+this.x+" et vous vous retrouvez en case "+j.position;
        return s;
    }
}