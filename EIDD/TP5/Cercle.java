/**
 *
 * @author eric
 */

public class Cercle implements Dessinable {
    Point p;
    double r;

    public Cercle(Point P, double R){
        this.p = P;
        this.r = R;
    }

    public void dessineSur(Ardoise a){
        a.dessineCercle(this.p.x, this.p.y, r);
    }
}