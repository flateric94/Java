package tp9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// ERIC CORTIAL EIDD Génie Biologique

public class MolecularWeight {
	
	public void printPattern(String regex, String cible) {
		// On compile la regex
		Pattern pattern = Pattern.compile(regex);
		// On l'utilise dans le string d'entrée
		Matcher m = pattern.matcher(cible); 
		while(m.find()){
			for (int i = 0; i <= m.groupCount(); i++) {
				// Affichage tous les groupes
				System.out.printf("Group(%d): %s\n",i,m.group(i));
			}	
		}	
	}
	
	
	public void iteratePattern(String cible) {
		/* 
		 * On prend cette fois ci une regex composé d'une 
		 * lettre majuscule, avec au plus 1 lettre minuscule, puis d'un nombre 
		 */
		String regex = "([A-Z][a-z]?)(\\d?)"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(cible); 
		while(m.find()) {
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.printf("Group(): %s\n",m.group(i));
			}	
		}
	}
	
	
	public double simpleMolecularWeight(String formula) {
		// On reprend la meme regex
		String regex = "([A-Z][a-z]?)(\\d?)"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(formula);
		double poids = 0;
		// On recherche des occurences dans le matcher tant qu'il y en a
		while(m.find()) {
			try {
				// D'abord on verifie si le groupe possede un chiffre multiplicatif
				if(m.group(0).matches("..?\\d")) {
					/*
					 *  Si oui :
					 *  on récupére la valeur contenue dans le string et on multiplie 
					 *  la valeur de masse atomique avec le multiplicateur qu'on ajoute 
					 *  à la somme
					 */
					poids += Elements.valueOf(m.group(1)).getAtomicWeight() * Integer.parseInt(m.group(2)); 
				}
				else {
					/*
					 * sinon :
					 * cela veut dire qu'on a un atome seul, il suffit d'ajouter 
					 * sa masse à la somme
					 */
					poids += Elements.valueOf(m.group(1)).getAtomicWeight(); 
				}
			} catch (IllegalArgumentException e) {
				System.out.println(formula+" : No enum constant tp9.element."+e.getMessage());
				System.exit(0);
			}
		}
		return poids;
	}
	
	public double compoundMolecularWeight(String formula) {
		/*
		 * Cette fois si on ne s'occupe plus de savoir si ce sont 
		 * des atomes ou pas, la methode simple essaiera d'elle meme. 
		 * On verifie seulement que nous avons une forme de composée.
		 */
		String regex = "(.*)([(](.*?)[)](\\d?))+(\\d*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(formula);
		double poids = 0;
		while(m.find()) {
			try {
				poids += this.simpleMolecularWeight(m.group(1))+this.simpleMolecularWeight(m.group(3))* Integer.parseInt(m.group(4)); 
			} catch (IllegalArgumentException e) {
				System.out.println("No enum constant tp9.element."+m.group(3));
				System.exit(0);
			}
		}
		return poids;
	}
	
	public boolean isFormulaCorrect(String formula) {
		/*
		 *  On verifie qu'on ne trouve pas d'occurence autre 
		 *  que des parentheses ou des maj, min et chiffres
		 */
		Pattern pattern = Pattern.compile("[^\\p{Alnum}[(][)]]"); 
		Matcher m = pattern.matcher(formula);
		if(m.find()) {
			throw new IllegalArgumentException(formula);
		}
		return true;
	}
	
	public static void main(String[] args) {
		MolecularWeight mw = new MolecularWeight();
		//String formula = "NH3";
		//System.out.printf("%s : %.4f",formula,mw.simpleMolecularWeight(formula));
		String formula = "CaMg(SiO3)2";
		System.out.printf("%s : %.4f",formula,mw.compoundMolecularWeight(formula));
		System.out.println(mw.isFormulaCorrect("NH3"));
		//System.out.println(mw.isFormulaCorrect("NH.3"));
	}
}