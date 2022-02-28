package tp6;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Optional;



public class Computations {
    
    /**
     * Method to measure execution time of any processing of Collection<OwidItem> 
     * @param <R>
     * @param name
     * @param function
     * @param data
     * @return
     */
    static <R> R time(String name, Function<Collection<OwidItem>,R> function,Collection<OwidItem> data) {
        System.err.printf("----\nStarting \"%s\"\n",name);
        Instant before=Instant.now();
        R result=function.apply(data);
        Instant after=Instant.now();
        Duration d=Duration.between(before, after);
        double secs=d.getSeconds()+d.getNano()/1_000_000_000.;
        try {Thread.sleep(50);} catch (Exception e) {} // Messy Eclipse console (System.out.flush() not effective)
        System.err.printf("Done \"%s\", %.6fs\n",name,secs);  
        return result;
    }

    /**
     * Method to measure execution time of any processing of Collection<OwidItem> 
     * Runs it 10 times to avoid jitter on results
     * @param <R>
     * @param name
     * @param function
     * @param data
     * @return
     */
     static <R> R time10(String name, Function<Collection<OwidItem>,R> function,Collection<OwidItem> data) {
        System.err.printf("----\nStarting \"%s\"\n",name);
        Instant before=Instant.now();
        R result=null;
        for (int i=0;i<10;i++) {
           result=function.apply(data);
        }
        Instant after=Instant.now();
        Duration d=Duration.between(before, after);
        double secs=d.getSeconds()+d.getNano()/1_000_000_000.;
        try {Thread.sleep(50);} catch (Exception e) {} // Messy Eclipse console (System.out.flush() not effective)
        System.err.printf("Done \"%s\", %.6fs\n",name,secs);  
        return result;
    }
    /**
     * Wrap those functions that don't return a result into one that returns a dummy
     * @param name
     * @param function
     * @param data
     */
    static void time(String name, Consumer<Collection<OwidItem>> function,Collection<OwidItem> data) {
        time(name,(e)->{function.accept(data); return null;},data);
    }
    
    static void time10(String name, Consumer<Collection<OwidItem>> function,Collection<OwidItem> data) {
        time10(name,(e)->{function.accept(data); return null;},data);
    }
    
    /**************************************************************************
     * Au travail
     */
    
    //TODO:
    static void smallestLocationPopulation(Collection<OwidItem> data) {
    	System.out.println(" Plus petit pays : "+data.stream().sorted(Comparator.comparing(OwidItem::getPopulation)).findFirst().orElse(null));
    }
    
    //TODO:
    static void totalVaccinations(Collection<OwidItem> data) {
    	int total = data.stream().mapToInt(OwidItem::getNewVaccinations).sum();
    	System.out.println("Total vaccination :"+total);
    }
           
    //TODO:
    static void bestVaccinationDay(Collection<OwidItem> data) {
    	// reversed pour avoir le plus gros total
    	System.out.println(data.stream().sorted(Comparator.comparing(OwidItem::getNewVaccinations).reversed()).findFirst().orElse(null));
    }
    
    //TODO:
    static void bestVaccinationDayByEuropeanLocation(Collection<OwidItem> data) {
    	data.stream()
    	.filter((e)->e.getContinent().equals("Europe")).filter((e)->e.getNewVaccinations() > 0)    	
    	.collect(Collectors.groupingBy(OwidItem::getLocation , Collectors.minBy(Comparator.comparing(OwidItem::getDate))))
    	.values()
    	.stream()
    	.map(Optional::get)
    	.sorted(Comparator.comparing(OwidItem::getLocation))
    	.forEachOrdered(System.out::println);
    }
    
    //TODO:
    static void vaccinationDayStatsByEuropeanLocation(Collection<OwidItem> data) {
    	data.stream()
    	.filter((e)->e.getContinent().equals("Europe"))
    	.filter((e)->e.getNewVaccinations() > 0)
    	.collect(Collectors.groupingBy(OwidItem::getLocation))
    	.values()
    	.stream()
    	.sorted((e,f) -> e.get(0).getLocation().compareTo(f.get(0).getLocation()))
    	.forEachOrdered((e) -> System.out.println(
    			"--------\n Lieux :" +e.get(0).getLocation()+
    			"\n Minimum : " + (double) e.stream().min(Comparator.comparing(OwidItem::getNewVaccinations)).get().getNewVaccinations()+
    			"\n Moyenne : " + e.stream().collect(Collectors.averagingDouble(OwidItem::getNewVaccinations))+
    			"\n Maximum : " + e.stream().max(Comparator.comparing(OwidItem::getNewVaccinations)).get().getNewVaccinations()+
    			"\n Total : " + e.stream().collect(Collectors.summingLong(OwidItem::getNewVaccinations))+
    			"\n--------"
    			));
    }
    
    //TODO:
   static void firstVaccinationDayByEuropeanLocation(Collection<OwidItem> data) {
	   data.stream()
	   .filter((e)->e.getContinent().equals("Europe")).filter((e) -> (e.getNewVaccinations() > 0))
	   .collect(Collectors.groupingBy(OwidItem::getLocation, Collectors.minBy(Comparator.comparing(OwidItem::getDate))))
	   .values()
	   .stream()
	   .map(Optional::get)
	   .sorted(Comparator.comparing(OwidItem::getLocation))
	   .forEachOrdered(System.out::println);
	}
    
   //TODO:
   static Collection<OwidItem> keepLast(Collection<OwidItem> all) {
	   return all.stream()
			   .collect(Collectors.toMap(OwidItem::getIsoCode, Function.identity(), (a,b)->a.getDate().isBefore(b.getDate())? a:b))
			   .values();
   }

    //TODO:
    static void vaccinationRatio(Collection<OwidItem> data,Predicate<OwidItem> keep, String label) {
        class Classe{
        	long vaccination, population;
        	Classe(long a, long b){
        		vaccination = a;
        		population = b;
        	}
        	public void show() {
        		System.out.println("Vaccination ratio: "+label+ "|" +(double) vaccination/population);
        	}        	
        }
        data.stream()
        .filter((e) -> !(e.getContinent().isBlank()))
        .filter(keep)
        .map((e) -> new Classe(e.getPeopleFullyVaccinated(),e.getPopulation()))
        .reduce(new Classe((long) 0, (long) 0),(a,b) -> new Classe(a.vaccination+b.vaccination,a.population+b.population))
        .show();
    }
    
    static void continentsVaccinationRatio(Collection<OwidItem> data) {
        vaccinationRatio(data,(o)->o.getContinent().equals("Europe"), "vaccinationRatioEurope");
        vaccinationRatio(data,(o)->o.getContinent().equals("Asia"), "vaccinationRatioAsia");
        vaccinationRatio(data,(o)->o.getContinent().equals("Africa"), "vaccinationRatioAfrica");
    }
    
    static void worldVaccinationRatio(Collection<OwidItem> data) {
        vaccinationRatio(data,(o)-> true, "worldVaccinationRatio"); // Keep them all
    }
    
    public static void main(String[] args) {
        try {
        	// Load the two files
            Collection<OwidItem> owidWordData=OwidItem.loadCSVResource("owid-covid-data-world.csv");
            Collection<OwidItem> owidWordDataLastDay=OwidItem.loadCSVResource("owid-covid-data-last.csv");
            
            //TODO: a décommenter quand vous aurez un keepLast() fonctionnel
            //owidWordDataLastDay=time("Extract last day",Computations::keepLast,owidWordData);
            
            time("Smallest population",Computations::smallestLocationPopulation,owidWordDataLastDay);
            time("Total vaccinations",Computations::totalVaccinations,owidWordDataLastDay);
            time("Best Vaccination Day",Computations::bestVaccinationDay,owidWordData);
            time("First Vaccination Day by European Location",Computations::firstVaccinationDayByEuropeanLocation,owidWordData);
            time("Best Vaccination Day by European Location",Computations::bestVaccinationDayByEuropeanLocation,owidWordData);
            time("Vaccination Day Statistics by European Location",Computations::vaccinationDayStatsByEuropeanLocation,owidWordData);
            time("Continents vaccination ratios",Computations::continentsVaccinationRatio,owidWordDataLastDay);
            time("World vaccination ratio",Computations::worldVaccinationRatio,owidWordDataLastDay);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

