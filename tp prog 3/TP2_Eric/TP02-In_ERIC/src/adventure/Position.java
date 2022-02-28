package adventure;

import java.util.Collection;
import java.util.HashSet;

class Position {
    int x;
    int y;
    
    /**
     * Classe interne/enum pour les déplacements possibles
     */
   public enum Move {
        N(0,-1),
        S(0,+1),
        W(-1,0),
        E(+1,0);
 
        int deltaX;
        int deltaY;
        
        Move(int deltaX,int deltaY) {
            this.deltaX=deltaX;
            this.deltaY=deltaY;
        }
        
        Position from(Position p) { 
            return new Position(p.x+deltaX,p.y+deltaY);
            }
    }
    
    /**
     * Méthode "factory" pour  générer une collection de Move à partir d'une String
     * de spécifications. Noter que Room ne sait pas du tout comment décoder
     * la String. On pourrait même cacher la Collection<Move> dans un objet
     * pour le rendre plus opaque.
     * @param s
     * @return
     */
    static Collection<Move> allowedMovesFrom(String s) { 
        Collection<Move> set=new HashSet<Move>();
        for (int i=0;i<s.length();i++) {
            String direction=s.substring(i,i+1);
            set.add(Move.valueOf(direction));
        }
        return set;
    }

    /**
     * Méthode "factory" pour décoder un déplacement à partir d'une entrée utilisateur
     * @param s
     * @return
     */
    static Move moveFrom(String s) {
        for (Move move:Move.values()) {
            if (move.toString().equals(s)) {
                return move;
            }
        }
        return null;
    }
    
    public final static Position OUT=new Position(-1,-1);

    private Position(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public Position move(Move m) {
        return m.from(this);
    }
    
    public String toString() {
        return String.format("[%2d,%2d]",x,y);
    }
    
    public boolean equals(Position other) {
        if (other.x==x && other.y==y) return true;
        return false;
    }
    
    static Position at(int x, int y) {
        return new Position(x,y);
    }
}
