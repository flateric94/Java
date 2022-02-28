package tp6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OwidItem {
    private String isoCode = null;
    private String continent = null;
    private String location = null;
    private LocalDate date = null;
    private int totalCases = 0;
    private int newCases = 0;
    private double newCasesSmoothed = 0.;
    private int totalDeaths = 0;
    private int newDeaths = 0;
    private double newDeathSmoothed = 0.;
    private double totalCasesPerMillion = 0.;
    private double newCasesPerMillion = 0.;
    private double newCasesSmoothedPerMillion = 0.;
    private double totalDeathsPerMillion = 0.;
    private int newDeathsPerMillion = 0;
    private double newDeathsSmoothedPerMillion = 0.;
    private double reproductionRate = 0.;
    private int icuPatients = 0;
    private double icuPatientsPerMillion = 0.;
    private int hospPatients = 0;
    private double hospPatientsPerMillion = 0.;
    private int weeklyIcuAdmissions = 0;
    private double weeklyIcuAdmissionsPerMillion = 0.;
    private int weeklyHospAdmissions = 0;
    private double weeklyHospAdmissionsPerMillion = 0.;
    private int newTests = 0;
    private int totalTests = 0;
    private double totalTestsPerThousand = 0.;
    private double newTestsPerThousand = 0.;
    private int newTestsSmoothed = 0;
    private double newTestsSmoothedPerThousand = 0.;
    private double positiveRate = 0.;
    private double testsPerCase = 0.;
    private String testUnits=null;
    private int totalVaccinations = 0;
    private int peopleVaccinated = 0;
    private int peopleFullyVaccinated = 0;
    private int totalBoosters = 0;
    private int newVaccinations = 0;
    private int newVaccinationsSmoothed = 0;
    private double totalVaccinationsPerHundred = 0;
    private double peopleVaccinatedPerHundred = 0;
    private double peopleFullyVaccinatedPerHundred = 0;
    private double totalBoostersPerHundred = 0;
    private int newVaccinationsSmoothedPerMillion = 0;
    private double stringencyIndex = 0.;
    private int population = 0;
    private double populationDensity = 0.;
    private double medianAge = 0.;
    private double aged65Older = 0.;
    private double aged70Older = 0.;
    private double gdpPerCapita = 0.;
    private double extremePoverty = 0.;
    private double cardiovascularDeathRate = 0.;
    private double diabetesPrevalence = 0.;
    private double femaleSmokers = 0.;
    private double maleSmokers = 0.;
    private double handwashingFacilities = 0.;
    private double hospitalBedsPerThoudands = 0.;
    private double lifeExpectancy = 0.;
    private double humanDevelopmentIndex = 0.;
    private double excessMortalityCumulativeAbsolute = 0;
    private double excessMortalityCumulative = 0;
    private double excessMortality = 0.;
    private double excessMortalityCumulativePerMillion = 0;
    
    String nextString(Scanner sc) {
        String s=sc.next();
        return (s.startsWith("\"") && s.endsWith("\"")) ? s.substring(1,s.length()-1) : s;
    }
    
    int nextIntOrZero(Scanner sc) {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            sc.next();
            return 0;
        }
    }    

    double nextDoubleOrZero(Scanner sc) {
        if (sc.hasNextDouble()) {
            return sc.nextDouble();
        } else {
            sc.next();
            return 0.;
        }
    }    

    private OwidItem() {
        isoCode="*****";
        continent="******";
        location="******";
        date=LocalDate.parse("1970-01-01");
    }
    
    static OwidItem UNKNOWN=new OwidItem();
    
    OwidItem(String csvLine) {
        try (Scanner sc = new Scanner(csvLine)) {
            sc.useDelimiter(",");
            isoCode = nextString(sc);
            continent = nextString(sc);
            location = nextString(sc);
            date = LocalDate.parse(nextString(sc));
            totalCases = nextIntOrZero(sc);
            newCases = nextIntOrZero(sc);
            newCasesSmoothed = nextDoubleOrZero(sc);
            totalDeaths = nextIntOrZero(sc);
            newDeaths = nextIntOrZero(sc);
            newDeathSmoothed = nextDoubleOrZero(sc);
            totalCasesPerMillion = nextDoubleOrZero(sc);
            newCasesPerMillion = nextDoubleOrZero(sc);
            newCasesSmoothedPerMillion = nextDoubleOrZero(sc);
            totalDeathsPerMillion = nextDoubleOrZero(sc);
            newDeathsPerMillion = nextIntOrZero(sc);
            newDeathsSmoothedPerMillion = nextDoubleOrZero(sc);
            reproductionRate = nextDoubleOrZero(sc);
            icuPatients = nextIntOrZero(sc);
            icuPatientsPerMillion = nextDoubleOrZero(sc);
            hospPatients = nextIntOrZero(sc);
            hospPatientsPerMillion = nextDoubleOrZero(sc);
            weeklyIcuAdmissions = nextIntOrZero(sc);
            weeklyIcuAdmissionsPerMillion = nextDoubleOrZero(sc);
            weeklyHospAdmissions = nextIntOrZero(sc);
            weeklyHospAdmissionsPerMillion = nextDoubleOrZero(sc);
            newTests = nextIntOrZero(sc);
            totalTests = nextIntOrZero(sc);
            totalTestsPerThousand = nextDoubleOrZero(sc);
            newTestsPerThousand = nextDoubleOrZero(sc);
            newTestsSmoothed = nextIntOrZero(sc);
            newTestsSmoothedPerThousand = nextDoubleOrZero(sc);
            positiveRate = nextDoubleOrZero(sc);
            testsPerCase = nextDoubleOrZero(sc);
            testUnits = nextString(sc);
            totalVaccinations = nextIntOrZero(sc);
            peopleVaccinated = nextIntOrZero(sc);
            peopleFullyVaccinated = nextIntOrZero(sc);
            totalBoosters = nextIntOrZero(sc);
            newVaccinations = nextIntOrZero(sc);
            newVaccinationsSmoothed = nextIntOrZero(sc);
            totalVaccinationsPerHundred = nextDoubleOrZero(sc);
            peopleVaccinatedPerHundred = nextDoubleOrZero(sc);
            peopleFullyVaccinatedPerHundred = nextDoubleOrZero(sc);
            totalBoostersPerHundred = nextDoubleOrZero(sc);
            newVaccinationsSmoothedPerMillion = nextIntOrZero(sc);
            stringencyIndex = nextDoubleOrZero(sc);
            population = nextIntOrZero(sc);
            populationDensity = nextDoubleOrZero(sc);
            medianAge = nextDoubleOrZero(sc);
            aged65Older = nextDoubleOrZero(sc);
            aged70Older = nextDoubleOrZero(sc);
            gdpPerCapita = nextDoubleOrZero(sc);
            extremePoverty = nextDoubleOrZero(sc);
            cardiovascularDeathRate = nextDoubleOrZero(sc);
            diabetesPrevalence = nextDoubleOrZero(sc);
            femaleSmokers = nextDoubleOrZero(sc);
            maleSmokers = nextDoubleOrZero(sc);
            handwashingFacilities = nextDoubleOrZero(sc);
            hospitalBedsPerThoudands = nextDoubleOrZero(sc);
            lifeExpectancy = nextDoubleOrZero(sc);
            humanDevelopmentIndex = nextDoubleOrZero(sc);
            excessMortalityCumulativeAbsolute = nextDoubleOrZero(sc);
            excessMortalityCumulative = nextDoubleOrZero(sc);
            excessMortality = nextDoubleOrZero(sc);
            excessMortalityCumulativePerMillion = nextDoubleOrZero(sc);
        } 
        catch (NoSuchElementException e) {
            // CSV has no data for the rest of the columns
        }
        
        // Other exceptions are caught and rethrown as DatalLoadException (see below) 
    }
    
    /*
     * Long list of getters (generatted by Eclipse) useful because streams 
     * cannot refer directly to attribute but can refer to methods
     */
    

    public String getIsoCode() {
        return isoCode;
    }

    public String getContinent() {
        return continent;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public double getNewCasesSmoothed() {
        return newCasesSmoothed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public double getNewDeathSmoothed() {
        return newDeathSmoothed;
    }

    public double getTotalCasesPerMillion() {
        return totalCasesPerMillion;
    }

    public double getNewCasesPerMillion() {
        return newCasesPerMillion;
    }

    public double getNewCasesSmoothedPerMillion() {
        return newCasesSmoothedPerMillion;
    }

    public double getTotalDeathsPerMillion() {
        return totalDeathsPerMillion;
    }

    public int getNewDeathsPerMillion() {
        return newDeathsPerMillion;
    }

    public double getNewDeathsSmoothedPerMillion() {
        return newDeathsSmoothedPerMillion;
    }

    public double getReproductionRate() {
        return reproductionRate;
    }

    public int getIcuPatients() {
        return icuPatients;
    }

    public double getIcuPatientsPerMillion() {
        return icuPatientsPerMillion;
    }

    public int getHospPatients() {
        return hospPatients;
    }

    public double getHospPatientsPerMillion() {
        return hospPatientsPerMillion;
    }

    public int getWeeklyIcuAdmissions() {
        return weeklyIcuAdmissions;
    }

    public double getWeeklyIcuAdmissionsPerMillion() {
        return weeklyIcuAdmissionsPerMillion;
    }

    public int getWeeklyHospAdmissions() {
        return weeklyHospAdmissions;
    }

    public double getWeeklyHospAdmissionsPerMillion() {
        return weeklyHospAdmissionsPerMillion;
    }

    public int getNewTests() {
        return newTests;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public double getTotalTestsPerThousand() {
        return totalTestsPerThousand;
    }

    public double getNewTestsPerThousand() {
        return newTestsPerThousand;
    }

    public int getNewTestsSmoothed() {
        return newTestsSmoothed;
    }

    public double getNewTestsSmoothedPerThousand() {
        return newTestsSmoothedPerThousand;
    }

    public double getPositiveRate() {
        return positiveRate;
    }

    public double getTestsPerCase() {
        return testsPerCase;
    }

    public String getTestUnits() {
        return testUnits;
    }

    public int getTotalVaccinations() {
        return totalVaccinations;
    }

    public int getPeopleVaccinated() {
        return peopleVaccinated;
    }

    public int getPeopleFullyVaccinated() {
        return peopleFullyVaccinated;
    }

    public int getTotalBoosters() {
        return totalBoosters;
    }

    public int getNewVaccinations() {
        return newVaccinations;
    }

    public int getNewVaccinationsSmoothed() {
        return newVaccinationsSmoothed;
    }

    public double getTotalVaccinationsPerHundred() {
        return totalVaccinationsPerHundred;
    }

    public double getPeopleVaccinatedPerHundred() {
        return peopleVaccinatedPerHundred;
    }

    public double getPeopleFullyVaccinatedPerHundred() {
        return peopleFullyVaccinatedPerHundred;
    }

    public double getTotalBoostersPerHundred() {
        return totalBoostersPerHundred;
    }

    public int getNewVaccinationsSmoothedPerMillion() {
        return newVaccinationsSmoothedPerMillion;
    }

    public double getStringencyIndex() {
        return stringencyIndex;
    }

    public int getPopulation() {
        return population;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public double getMedianAge() {
        return medianAge;
    }

    public double getAged65Older() {
        return aged65Older;
    }

    public double getAged70Older() {
        return aged70Older;
    }

    public double getGdpPerCapita() {
        return gdpPerCapita;
    }

    public double getExtremePoverty() {
        return extremePoverty;
    }

    public double getCardiovascularDeathRate() {
        return cardiovascularDeathRate;
    }

    public double getDiabetesPrevalence() {
        return diabetesPrevalence;
    }

    public double getFemaleSmokers() {
        return femaleSmokers;
    }

    public double getMaleSmokers() {
        return maleSmokers;
    }

    public double getHandwashingFacilities() {
        return handwashingFacilities;
    }

    public double getHospitalBedsPerThoudands() {
        return hospitalBedsPerThoudands;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public double getHumanDevelopmentIndex() {
        return humanDevelopmentIndex;
    }

    public double getExcessMortalityCumulativeAbsolute() {
        return excessMortalityCumulativeAbsolute;
    }

    public double getExcessMortalityCumulative() {
        return excessMortalityCumulative;
    }

    public double getExcessMortality() {
        return excessMortality;
    }

    public double getExcessMortalityCumulativePerMillion() {
        return excessMortalityCumulativePerMillion;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-25.25s %8s %10d %10d %10d %10d",isoCode,continent,location,date,population,newVaccinations,totalVaccinations,peopleFullyVaccinated);
    }

    static Collection<OwidItem> loadCSVResource(String file) throws IOException {
        List<OwidItem> owidItems = null;
        Instant beforeLoad = Instant.now();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(OwidItem.class.getResourceAsStream(file)))) {
            owidItems = br.lines().skip(1) // skip header
                    .parallel() // Go parallel
                    .map(OwidItem::new) // Each line is an entry
                    .collect(Collectors.toList()); // Generate collection
        }
        Instant afterLoad = Instant.now();
        Duration d = Duration.between(beforeLoad, afterLoad);
        double secs=d.getSeconds() + d.getNano() / 1_000_000_000.;
        System.err.printf("Loaded %d entries in %.2f seconds from resource %s\n", owidItems.size(),secs, file);
        return owidItems;
    }
    
    /*
     * A small main to smoke-test the constructor and the file loader  
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String test="FRA,Europe,France,2021-05-02,5713420,9921,22037.714,104982,114,280.571,84562.767,146.838,326.174,1553.81,1.687,4.153,0.81,5585,82.662,28818,426.527,2403.682,35.576,10273.105,152.049,58017,,,0.859,295413,4.372,0.067,14.9,people tested,22752334,16211481,6598127,7112,128667,397611,33.68,23.99,9.77,0.01,5885,75,67564251,122.578,42,19.718,13.079,38605.671,,86.06,4.77,30.1,35.6,,5.98,82.66,0.901,63400.8,7.61,13.61,938.37790046692";
        OwidItem fr=new OwidItem(test);
        System.out.println(fr);
        try {
            loadCSVResource("owid-covid-data-world.csv");
        }
        catch (Exception e) {e.printStackTrace();}
    }
}

