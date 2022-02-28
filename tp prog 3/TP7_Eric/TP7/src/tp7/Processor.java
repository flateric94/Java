package tp7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Processor {
	
	/*
	 *  Nous souhaitons manipuler des doubles (entrées/sorties)
	 *  donc nous créons notre lambda avec un DoubleUnaryOperator
	 *  qui répond à nos critères. 
	 */
	
	List<DoubleUnaryOperator> Liste_lambda;
	
	// Definition des opérandes
	
	private enum Operator {
		ADD {
			@Override
			DoubleUnaryOperator operation(double operand) {
				return (e) -> e + operand;
			}
		}
		
		,SUB {
			@Override
			DoubleUnaryOperator operation(double operand) {
				return (e) -> e - operand;
			}
		}
		
		,MUL {
			@Override
			DoubleUnaryOperator operation(double operand) {
				return (e) -> e * operand;
			}
		}
		
		,DIV {
			@Override
			DoubleUnaryOperator operation(double operand) {
				return (e) -> e / operand;
			}
		};
			abstract DoubleUnaryOperator operation(double operand);
		}
	
	static private Processor From(String name) {
		Scanner scan;
		// On teste l'ouverture correcte du fichier passé en arg
		try {
			scan = new Scanner(Processor.class.getResourceAsStream(name));
		} catch(NullPointerException e) {
			throw new IllegalArgumentException("ERROR : name of the file incorrect");
		}
		// On crée une liste de lambda qui va nous permettre d'en stocker, et qui sera argument du Processor
		List<DoubleUnaryOperator> List_operator = new ArrayList<>();
		while(scan.hasNextLine() == true) {
			// parcourt du fichier (trim() enleve les espaces avant et après les caractères)
			String line = scan.nextLine().trim();
			// Si la ligne contient des instructions :
			if (line.isBlank() == false) {
				// on vérifie que ça n'est pas un commentaire
				if (line.charAt(0) == '#') {
					continue;
				}
				// on sépare les lignes par des espaces
				String[] line_separ = line.split(" ");
				List_operator.add(Operator.valueOf(line_separ[0].toUpperCase())
						.operation(Double.parseDouble(line_separ[1])));						
			}
		}
		// On crée finalement un nouveau processor qui sera le return et qui a comme variable interne la liste de lambda
		Processor result = new Processor();
		result.Liste_lambda = List_operator;
		// on ferme
		scan.close();
		return result;
	}
	
	/*
	 * prend un DoubleStream et retourne un DoubleStream 
	 * où le stream d'entrée a été complété par les opérations 
	 * de la liste de lambdas créée précédemment
	 */
	private DoubleStream addOps(DoubleStream upStream) {
		// Premiere opération qu'on enlève de la liste sur le stream, puis on stocke ailleurs
		List<Double> temp = upStream.map(this.Liste_lambda.remove(0)).boxed().collect(Collectors.toList());
		temp.forEach(System.out::println);
		return temp.stream().mapToDouble((e)->e);
	}
	
	
	private void process(DoubleStream upStream) {
		// On itère jusqu'à ce que la liste soit vide :
		while(this.Liste_lambda.isEmpty() == false) {	
			// On effectue addOps pour les lambdas contenus dans Liste_lambda
			upStream = this.addOps(upStream);
		}
	}
	
	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.out.println("Usage: Processor [operations file]");
			System.exit(1);
		}
		List<String> liste = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			//on lit n (ici 3) nombres sur l'entrée standard
			liste.add(sc.nextLine()); 
		}
		// On crée le DoubleStream à partir de ces valeurs
		DoubleStream str = liste.stream().mapToDouble((e)->Double.parseDouble(e)); 
		//on crée les lambdas
		Processor pro = Processor.From(argv[0]);
		//on effectue le process
		pro.process(str); 
		sc.close();
	}
}
	