/**
 *
 * @author eric
 */

public class Polygone extends LigneBrisee {
    
    public Polygone(Point [] tab) {
        super(tab);
    }

    public void dessineSur(Ardoise a) {
        for(int i = 0; i < tab.length - 1; i++){
            a.dessineSegment(tab[i].x, tab[i].y, tab[i+1].x, tab[i+1].y);
        }
        a.dessineSegment(tab[tab.length - 1].x, tab[tab.length - 1].y, tab[0].x, tab[0].y);
    }    

}