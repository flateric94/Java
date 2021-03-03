/**
 *
 * @author eric
 */

public class Segment implements Dessinable {
    Point x;
    Point y;

    public class(Point X, point Y){
        this.x = X;
        this.y = Y;
    }

    public void dessineSur(Ardoise a){
        a.dessinePoint(this.x.X, this.x.Y);
        a.dessinePoint(this.y.X, this.y.Y);
        a.dessineSegment(this.x.X, this.x.Y, this.y.X, this.y.Y);
    }
}