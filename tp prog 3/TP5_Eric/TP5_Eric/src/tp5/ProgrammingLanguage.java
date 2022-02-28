package tp5;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ProgrammingLanguage {
    public static final Object[][] languages= {
            {1985,"C++"},
            {1979,"Rexx"},
            {1990,"Python"},
            {1998,"Java"},
            {1964,"Basic"},
            {1972,"Smalltalk"},
            {1995,"JavaScript"},
            {1970,"Forth"},
            {1957,"Fortran"},
            {1959,"Cobol"},
            {1960,"Algol"},
            {1964,"PL/1"},
            {1995,"PHP"},
            {1987,"Perl"},
            {1993,"Lua"},
            {1966,"APL"},
            {1972,"Prolog"},
            {1972,"C"},
            {1980,"Ada"},
            };
    final String name;
    final int year;
    
    public ProgrammingLanguage(String name,int year) {
        this.name=name;
        this.year=year;       
    }
    
    public String toString() {
        return String.format("[%4d]: %s",year,name);
    }
    
    public static void affichage(Map<?, ProgrammingLanguage> e, String name) {
    	System.out.println("--{"+name+"}");
		e.forEach((k, v) -> System.out.println("key = "+k+" | value = "+v));
		System.out.println();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> void init(Map<T, ProgrammingLanguage> e, int position_array) {
    	if (!(position_array == 0 || position_array == 1)) {
    		throw new IllegalArgumentException();
    	}
    	for (int i = 0; i < languages.length; i++) {
    		e.put((T) languages[i][position_array], new ProgrammingLanguage((String) languages[i][1], (int) languages[i][0]));
    	}
    }
    
    
    
    public static void main(String[] args) {
    	// CREATION DES 3 SORTEDMAP
    	SortedMap<Integer, ProgrammingLanguage> byYear = new TreeMap<Integer, ProgrammingLanguage>();
    	SortedMap<String, ProgrammingLanguage> byNameAlphabetic = new TreeMap<String, ProgrammingLanguage>();
    	SortedMap<String, ProgrammingLanguage> byNameReversed = new TreeMap<String, ProgrammingLanguage>(
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {

						return -o1.compareTo(o2);
					}
				});
    	
    	ProgrammingLanguage.<Integer>init(byYear, 0);
		ProgrammingLanguage.<String>init(byNameAlphabetic, 1);
		ProgrammingLanguage.<String>init(byNameReversed, 1);

    	
    	ProgrammingLanguage.affichage(byYear, "byYear");
		ProgrammingLanguage.affichage(byNameAlphabetic, "byNameAlphabetic");
		ProgrammingLanguage.affichage(byNameReversed, "byNameReversed");
		
    	/*
    	 * On observe que lorsque 2 éléments de la SortedMap ont une même clé
    	 * l'un des 2 disparait, à cause du fait qu'il ne peut y avoir
    	 * qu'un seul élément par clé.    	 * 
    	 */
		
		SortedMap<Integer, ProgrammingLanguage> eighties = byYear.subMap(1980, 1990);
		ProgrammingLanguage.affichage(eighties, "eighties");
     }
}
