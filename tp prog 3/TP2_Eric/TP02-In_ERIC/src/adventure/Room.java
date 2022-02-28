package adventure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/** 
 * La classe qui représente une salle du parcours:
 * 
 * - les dangers éventuels qu'elle contient
 * - les talismans éventuels à ramasser
 * 
 * Pas trop de logique dans cette classe qui sert surtout a conserver les données. 
 * 
 * Pour limiter le couplage, on peut quand même avoir des méthodes pour:
 * 
 *  - afficher les données 
 *  - vérifier les sorties 
 *  - itérer les dangers
 * 
 * ce qui permet de chnagre l'implémentation de la classe sans impacter Cave
 * 
 * @author bd
 */



class Room {
    String name;
    Collection<Position.Move> exits;
    boolean isEntry;
    List<Talisman> talismans=new ArrayList<>();
    List<Character> characters=new ArrayList<>();;

    boolean isEntry() {
        return isEntry;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Room [name=%s, exits=%s, isEntry=%s, talismans=%s, characters=%s]", name,
                exits, isEntry, talismans, characters);
    }
    
    /**
     * Factory method to create a room from the text specification
     * 
     * @param description
     * @return
     */
    static Room from(String description) {
        
        String contents[]=description.split(",");
        if (contents.length!=3) {
            throw new GameInitializationException(String.format("Invalid room specification: \"%s\"",description));
        }
        try (Scanner s = new Scanner(description).useDelimiter(",")) {
            String name = s.next();
            Collection<Position.Move> exits = Position.allowedMovesFrom(s.next());
            boolean isEntry = s.nextBoolean();
            Room room=new Room(name,exits,isEntry);
            System.out.printf("Created room \"%s\"\n", room.name);
            return room;
        }
    }
    
    void addCharacter(Character c) {
        System.out.printf("Adding character \"%s\", to %s\n",c.getName(),name);
        characters.add(c);
    }
    
    void addTalisman(Talisman t) {
        System.out.printf("Adding talisman \"%s\", to %s\n",t.getName(),name);
        talismans.add(t);
    }
    
    boolean hasTalisman(Talisman t) {
        return talismans.contains(t);
    }
    
    Talisman takeTalisman(Talisman t) {
    	//on cherche l'indice du talisman t, (�a trouve le talisman qu'on cherche), on le retourne et on l'enleve
    	int index = talismans.indexOf(t);
        return talismans.remove(index);
    }
    
    private Room(String name,Collection<Position.Move> exits,boolean isEntry) {
        this.name=name;
        this.exits=exits;
        this.isEntry=isEntry;
    }

    void describe() {
        System.out.printf("You are in the %s\n",name);
        System.out.printf("There are exits at: %s\n", exits);
        System.out.printf("Room contents:\n");
        talismans.forEach(System.out::println);
        System.out.printf("Room dangers:\n");
        characters.forEach(System.out::println);
        return;
    }
    
   boolean hasExit(Position.Move direction) {
        return exits.contains(direction); 
    }
   
   Iterator<Character> getCharacters() {
       return characters.iterator();
   }
}
