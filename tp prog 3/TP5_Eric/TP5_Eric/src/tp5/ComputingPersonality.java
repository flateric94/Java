package tp5;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ComputingPersonality implements Comparable<ComputingPersonality> {
    public static String[][] personalities={
            {"Alan","Turing"}, 
            {"Ken","Thompson"}, 
            {"Brian","Kernighan"},
            {"Dennis","Ritchie"},
            {"Ada","Lovelace"},
            {"Guido","Van Rossum"},
            {"Richard","Stallman"},
            {"Bjarne","Stroustrup"},
            {"Grace","Hopper"},
            {"Charles","Babbage"},
            {"Edsger","Dijkstra"},
            {"Hedy","Lamarr"},
            {"Larry","Wall"},
            {"Alan","Turing"}, 
            {"John","Backus"},
            {"Gary","Kildall"},
            {"Jean","Ichbiah"},
            {"Katherine","Johnson"},
            {"John","McCarthy"},
            {"Steve","Jobs"},
            {"Richard","Stallman"},
            {"Niklaus","Wirth"},
            {"Linus","Torvalds"},
            };

    final String surname; // nom de famille
    final String givenname; // prénom
    
    public ComputingPersonality(String surname,String givenname) {
        this.surname=surname;
        this.givenname=givenname;
    }     
    
    public String toString() {
        return String.format("%-9s %-20s",givenname,surname);
    }
    
    // TRI NATUREL PAR NOM DE FAMILLE
    public int compareTo(ComputingPersonality cp) {
    	if (cp == null) return -1;
    	int compare_surname = this.surname.compareTo(cp.surname);
    	if (compare_surname != 0) {
    		return compare_surname;
    	}
    	return this.givenname.compareTo(cp.givenname);
    }
    
    // AFFICHAGE 
    static void affichage(String name, Set<ComputingPersonality> e) {
    	System.out.println("-- {"+name+"}");
    	e.forEach(System.out::println);
    	System.out.println();
    }
    
    public final static void main(String[] args) {
    	// CONSTRUCTION DES 3 SETS
    	Set<ComputingPersonality> setperso = new HashSet<>();
    	SortedSet<ComputingPersonality> sortedbysurname = new TreeSet<>();
    	SortedSet<ComputingPersonality> sortedbygivenname = new TreeSet<>(
    			new Comparator<ComputingPersonality>() {
    				@Override	
    	    		public int compare(ComputingPersonality cp1, ComputingPersonality cp2) {
    	    			if ((cp1 == null) || (cp2 == null)) {
    	    				return -1;
    	    			}
    	    			int compare_givenname = cp1.givenname.compareTo(cp2.givenname);
    	    			if (compare_givenname != 0) {
    	    				return compare_givenname;
    	    			}
    	    			return cp1.surname.compareTo(cp2.surname);
    	    		}		

				});
    	for (int i = 0; i < personalities.length; i++) {
			setperso.add(new ComputingPersonality(personalities[i][1], personalities[i][0]));
			sortedbygivenname.add(new ComputingPersonality(personalities[i][1], personalities[i][0]));
			sortedbysurname.add(new ComputingPersonality(personalities[i][1], personalities[i][0]));
		}
    	
    	ComputingPersonality.affichage("Set non-trié", setperso);
		ComputingPersonality.affichage("Set trié par prénom", sortedbygivenname);
		ComputingPersonality.affichage("Set trié par nom", sortedbysurname);
    }
}
