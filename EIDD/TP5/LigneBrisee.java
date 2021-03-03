/**
 *
 * @author eric
 */

public class LigneBrisee implements Dessinable {
    Point [] tab;

    public LigneBrisee(Point [] tab) {
        this.tab = tab;
    }

    public void dessineSur(Ardoise a) {
        for (int i = 0; i < this.tab.length - 1; i++) {
            a.dessineSegment(this.tab[i].x, this.tab[i].y, this.tab[i+1].x, this.tab[i+1].y);
        }
    }
}