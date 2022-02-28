package adventure;

import java.util.*;

/**
 * La classe Player. elle représente le joueur derrière le clavier et 
 * se souvient de sa position et de sa collection de Talismans.
 *
 * Très peu de logique dans cette classe, juste de quoi mettre à jour la
 * position et les talismans...
 * 
 * @author bd
 *
 */

class Player {
       
    Position position;
    List<Talisman>  talismans=new ArrayList<Talisman>();
    boolean alive=true;
    
    boolean addTalisman(Talisman t) {
    	if (talismans.size() < 3) {
    		talismans.add(t);
            System.out.printf("Player has acquired: %s\n", t);
    	}
    	else {
    		System.out.printf("L'inventaire est plein");
    		return false;
    	}
    	return true;
    }
    
    boolean hasTalisman(Talisman t) {
        return talismans.contains(t);
    }
    
    Talisman takeTalisman(Talisman t) {
    	int index = talismans.indexOf(t);
        return talismans.remove(index);
    }
    
    void showTalismans() {
        System.out.printf("Player's talismans:\n");
        talismans.forEach(System.out::println);
    }

    public Player(Position position) {
        this.position=position;
    }
    
    void move(Position.Move move) {
        position=position.move(move);
    }

    void dies( ) {
        alive=false;
    }
    
    // on cr�e la nouvelle fonction 
    
    void talisman_description() {
    	for (int i = 0; i < talismans.size() ; i++) {
    		System.out.printf("%s |", talismans.get(i).toString());
    	}
    	System.out.println();
    }
    
    int force_totale(String name) {
    	int res = 0;
    	for (int i = 0; i < talismans.size(); i++) {
    		if (talismans.get(i).getName().equals(name)) {
    			res += talismans.get(i).getForce();
    		}
    	}
    	return res;
    }
    
    boolean isAlive( ) {
        return alive;
    }
    
}
