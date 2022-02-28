package tp11;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.DoubleUnaryOperator;

// TP de Eric CORTIAL

@Target(ElementType.TYPE) // L'interface ne pourra etre utilisé que par des TYPE (class,interface ou des enum)
@Retention(RetentionPolicy.RUNTIME) // on definit sa portée dans la JVM
@interface TablePrint { // on créé notre interface d'annotation contenant les noms et les valeurs par défauts demandés
	String header() default "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*";
	String format() default "In: %8.4f , out: %8.4f\n";
	double minValue() default 0.0;
	double maxValue() default 100.0;
	double Step() default 20.0;
	double[] values() default { 10, 30, 50, 70, 90 };
}

public class Printer {
	@SuppressWarnings({ "deprecation", "unchecked" }) // on enleve tout ce qui est warning pour plus de lisibilité
	public static void main(String[] args) {
		if (args.length < 1)
			throw new IllegalArgumentException("Main argument"); 
		for (String str : args) { // parcours des args
			try {
				// On retrouve la classe passé en argument en vérifiant qu'elle implémente un DoubleUnaryOperator
				Class<? extends DoubleUnaryOperator> clazz = (Class<? extends DoubleUnaryOperator>) Class.forName(str); 
				DoubleUnaryOperator instance = clazz.newInstance(); // on l'instancie
				TablePrint anno = (TablePrint) clazz.getDeclaredAnnotation(TablePrint.class); // on récupere toutes les annotations de TablePrint
				System.out.println("-------------------------------");
				System.out.println(clazz.toString()); // Affichage du nom de la classe
				System.out.println("-------------------------------");
				System.out.println(anno.header()); // Affichage du header
				// On initialise un booleen qui nous permettera de savoir si nous avons utilisé le tableau values ou si nous devons utliser maxValue, minValue et Steps
				boolean passage = false; 
				if (anno != null) {
					if (anno.values() != null) {
						if (anno.values().length != 0) {
							passage = true;
							for (double val : anno.values()) { 
								/**
								 * on les affiche avec le format contenue dans les annonations en mettant
								 * en premier la valeur avant l'application de l'opération, puis après
								 * On applique l'opération en utilisant l'instance récupéré précédemment,
								 * nous sommes sûr d'avoir une fonction applyAsDouble car la fonction étend 
								 * DoubleUnaryOperator 
								 */
								System.out.printf(anno.format(), val, instance.applyAsDouble(val)); 
							}
						}
					}
					// on verifie que l'on a pas deja affiché les valeurs du tableau
					if (passage == false) {
						// Si ce n'est pas le cas on fait une boucle for qui commence a la valeur minimum
						// se termine une fois avoir dépassé la valeur maximum et ayant un peu egal a Step
						for (double j = anno.minValue(); j < anno.maxValue(); j += anno.Step()) {
							// dans ce cas on affiche cette valeur dans le format de l'annotation une fois sans et une fois avec
							System.out.printf(anno.format(), j, instance.applyAsDouble(j)); 
						}
					}
				}
			/**
			 *  on récupére toutes les exeception pour chaque noms de classes
			 *  differente cela nous permet d'afficher les erreurs pour les
			 *  classes qui ne fonctionnent pas tout en appliquant la méthode pour celles
			 *  qui fonctionnent
			 */ 
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) { 															
				e.printStackTrace();
			}
		}
	}
}
