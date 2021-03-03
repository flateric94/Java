
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author pierrecharbit
 */
public class Prog {
        
    
    public static void main(String[] args) {
        
        /* lancement et parametrage de la fenetre contenant l'ardoise
        * ne pas editer ces lignes
        */
        JFrame fen = new JFrame();
        fen.setPreferredSize(new Dimension(500,500));
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ardoise monardoise = new Ardoise(-20,20,-20,20);
        fen.add(monardoise);
        fen.pack();
        fen.setVisible(true);

        /* vous pouvez arpes les instructions de dessin utilisant les classes que vous aurez ecrit
         * par exemple 
         *                 Point p = new Point(2,3);
         *                 Point q = new Point(4,5.2);
         *                 Point r = new Point(8.5,5);
                           Triangle t = new Triangle(p,q,r);
                            a.ajoute(p,q,r,t)  //ajoute accepte une liste quelqueconque d'objets dessinables
         */
        

        Point p = new Point(2,3);
        Point q = new Point(9,3);
        Point r = new Point(8.5,5);
        Point [] tab = {p,q,r};
        // Polygone poly = new Polygone(tab);
        // Triangle trg = new Triangle(p,q,r);
        Cercle crcl = new Cercle(p,4);
        Cercle crcl2 = new Cercle(q,4);
        monardoise.ajoute(crcl);
        monardoise.ajoute(crcl2);

    }
    
}
