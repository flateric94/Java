public class CaseRecule extends Case {
    int x;

    public CaseRecule(int x){
        this.x = x;
    }

    public String effetArrive(Joueur j) {
        j.position -= this.x;
        String s = "Vous reculez de "+this.x+" et vous vous retrouvez en case "+j.position;
        return s;
    }
}