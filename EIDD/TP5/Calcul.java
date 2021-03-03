
/**
 *
 * @author pierrecharbit
 */
public class Calcul {
    static final double PRECISION = 0.000000001;
    
    public static boolean isNul(double d){
        return Math.abs(d)<PRECISION;
    }
}
