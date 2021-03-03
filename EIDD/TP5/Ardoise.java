

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Une classe qui hérite de JPanel et qui permet d'afficher des Dessinable dans un repere orthonormé.
 *
 * Le repere affiché est [xmin; xmax].[ymin,ymax] que l'on peut choisir dans le constructeur
 * Elle fonctionne avec une liste de Dessinable à laquelle on ajoute des elements avec ajoute(Dessinable d)
 * Elle possède des méthodes pour dessiner un point, un cercle et un segment qui prennent en argument les coordonnées des dits objets dans le repere 
 * @author pierrecharbit
 */
public class Ardoise extends JPanel {

    Set<Dessinable> liste;
    private int xmin,xmax;
    private int ymin,ymax;
    private Graphics crayon;

    /**
     * 
     * @param xmin la plus petite abscisse affichée
     * @param xmax la plus grande abscisse affichée
     * @param ymin la plus petite ordonnée affichée
     * @param ymax la plus grande ordonnée affichée
     */
    public Ardoise(int xmin, int xmax, int ymin, int ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.setBorder(new EtchedBorder(Color.lightGray, Color.yellow));
        
        liste = ConcurrentHashMap.newKeySet();
        
    }

    /**
     * appelle this(-1,xmax,-1,ymax)
     */
    public Ardoise(int xmax, int ymax) {
        this(-1,xmax,-1,ymax);
    }
    

    public Ardoise() {
        this(-1,20,-1,20);
    }

    /**
     * 
     * @return la liste do'bjets dessinables qui ont été ajoutés à l'ardoise
     */
    public Set<Dessinable> getListe() {
        return liste;
    }

    public int getXmin() {
        return xmin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getYmin() {
        return ymin;
    }

    public int getYmax() {
        return ymax;
    }

    
    /**
     * permet de récupérer les coordonnées en pixels dans la JPanel en passant les coordonées en double dans le dessin du repère
     * @param v
     * @return 
     */
    int coordX(double v) {
        return (int) ((v -xmin) * this.getWidth() / (xmax-xmin));
    }
    
        /**
     * permet de récupérer les coordonnées en pixels dans la JPanel en passant les coordonées en double dans le dessin du repère
     * @param v
     * @return 
     */
    int coordY(double v) {
        return this.getHeight() - (int) ((v -ymin) * this.getHeight() / (ymax-ymin));
    }
    
    /**
     * inverse de coordX
     * @param x
     * @return 
     */
    double getXFromCoord(int x){
        return xmin+x*(xmax-xmin)/this.getWidth();
    }

    /**
     * inverse de coordY
     * @param x
     * @return 
     */
    double getYFromCoord(int x){
        return ymin+(this.getHeight()-x)*(ymax-ymin)/this.getWidth();
    }
    
    
    /**
     * ajoute un element a la liste de l'ardoise
     * @param d 
     */
    public void ajoute(Dessinable... d) {
        liste.addAll(Arrays.asList(d));
    }

    /**
     * dessine un point sous la forme d'un disque noir de diametre 10 pixels au coordonnées passées en argument (elles en double)
     * @param x la coordonnées du points dans le repere affiché
     * @param y 
     */
    public void dessinePoint(double x, double y) {
        int xx = coordX(x);
        int yy = coordY(y);
        crayon.fillOval(xx - 5, yy - 5, 10, 10);
    }
    
    public void dessineCercle(double x, double y, double r){
        int xx = coordX(x);
        int yy = coordY(y);
        int rrx = (int)(r*this.getWidth()/(xmax-xmin));
        int rry = (int)(r*this.getHeight()/(ymax-ymin));
        crayon.drawOval(xx - rrx, yy - rry, 2*rrx, 2*rry);        
    }

    public void dessineSegment(double x1, double y1, double x2, double y2) {

        int xp = coordX(x1);
        int yp = coordY(y1);
        int xq = coordX(x2);
        int yq = coordY(y2);

        crayon.drawLine(xp, yp, xq, yq);

    }
    
    public void changeCouleur(Color c){
        crayon.setColor(c);
    }

    @Override
    public void paintComponent(Graphics g) {
        //affichage du repere orthonormé
        for (int i = this.xmin; i < this.xmax; i++) {
            for (int j = this.ymin; j < this.ymax; j++) {
                 g.drawOval(coordX(i), coordY(j), 1, 1);
            }
           if(i!=0) 
               g.drawString( ""+i+ (Math.abs(i)<=9?"":" "), coordX(i-.6), coordY(-0.7));
            if(i!=0) 
               g.drawString(""+ i+(Math.abs(i)<=9?" ":""), (i<0?coordX(-1.1):coordX(-0.7)), (i<0 ?coordY(i-.6):coordY(i-.1)));

        }
        g.drawLine(coordX(0), coordY(ymin), coordX(0), coordY(this.ymax));
        g.drawLine(coordX(xmin), coordY(0), coordX(this.xmax), coordY(0));
        
        crayon = g;
        
        
        //affichage de tous les elements Dessinable de la liste.
        for (Dessinable d : liste) {
            d.dessineSur(this);
        }

    }

}
