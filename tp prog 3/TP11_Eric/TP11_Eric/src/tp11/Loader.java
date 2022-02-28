package tp11;

import java.util.function.DoubleUnaryOperator;

// TP de Eric CORTIAL

public class Loader {
	// on enleve les warnings comme dans printer
	@SuppressWarnings({ "deprecation", "unchecked" }) 
	public static void main(String[] args) throws ClassNotFoundException {
		if (args.length != 1)
			// on verifie qu'il n'y a qu'un seul argument dans notre main
			throw new IllegalArgumentException("Main argument"); 

		Class<? extends DoubleUnaryOperator> clazz = (Class<? extends DoubleUnaryOperator>) Class.forName(args[0]); 
		// on trouve la classe passé en argument en l'obligeant a etendre un DoubleUnaryOperator
		
		DoubleUnaryOperator instance = null;
		try {
			// on l'instancie en récuperant les exceptions
			instance = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		double val1 = 10.5;
		// on affiche la valeur en appliquant l'opération
		System.out.println("Valeur 1 = " + val1 + " ----> " + instance.applyAsDouble(val1)); 

		//double val2 = 150.12;
		//System.out.println("Valeur 2 = " + val2 + " ----> " + instance.applyAsDouble(val2));
	}

}
