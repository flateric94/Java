
/**
 * Une classe pour représenter des Points du Plan avec abscisse et ordonnée de type double
 * @author pierrecharbit
 */
public class Point implements Dessinable{
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double dist(Point p){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
      

    @Override
    public void dessineSur(Ardoise a) {
        //pour Point c'est facile, l'ardoise possède deja une méthode pour dessiner un point
        a.dessinePoint(this.x,this.y);
    }

}
