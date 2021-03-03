/**
 *
 * @author eric
 */

public class Vecteur {
    double x;
    double y;

    public Vecteur(double X, double Y){
        this.x = X;
        this.y = Y;
    }

    public double produitScalaire(Vecteur U) {
        return U.x * this.x + U.y * this.y;
    }

    public double produitVectoriel(Vecteur U) {
        return U.x * this.y - U.y * this.x;
    }

    public boolean isColineaire(Vecteur U) {
        return Calcul.isNul(this.produitVectoriel(U));
    }

    public boolean isOrthogonal(Vecteur U) {
        return Calcul.isNul(this.produitScalaire(U));
    }
}
