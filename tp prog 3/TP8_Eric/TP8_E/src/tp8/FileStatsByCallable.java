package tp8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileStatsByCallable{
	private ExecutorService pool;
	// On set le nombre de threads differents au nombre de CPU disponibles par l'ordinateur
	// Sinon, on peut le fixer
	private static final int poolSize = Runtime.getRuntime().availableProcessors();
	
	public FileStatsByCallable() {
		super();
		this.pool = Executors.newFixedThreadPool(poolSize); //On lance le nombre thread qui utilisera tous les coeurs virtuelles de l'ordinateur
	}

	
	public FileStats fromDir (String rootDir)
	{
		Stream<Path> str_path = null;
		try {
			str_path = Files.walk(Paths.get(rootDir));
		} catch (IOException e1) {
			System.out.println("Erreur dans le parcourt de l'arborescence");
			System.exit(0);
		}
		List<Future<FileStats>> list = new ArrayList<Future<FileStats>>();
		str_path.filter(Predicate.not(Files::isDirectory))
		.filter(Files::isReadable)
		// On récupere encore les bons fichiers
		.filter((e) -> e.endsWith(".java")) 
		// On ajoute a la liste "future" la création de nos Filestats qui se font dans le thread
		.forEach((e) -> list.add(this.pool.submit(new Callable<FileStats>() { 
			@Override
			public FileStats call() throws Exception {
				return FileStats.fromPath(e);
			}
		})));

		FileStats retour = new FileStats();
		for (Iterator<Future<FileStats>> iterator = list.iterator(); iterator.hasNext();) {
			Future<FileStats> future = (Future<FileStats>) iterator.next();
			try {
				retour.add(future.get());
			} catch (InterruptedException e1) {
				System.out.println("Erreur d'interuption d'un futur");
				System.exit(0);
			} catch (ExecutionException e1) {
				System.out.println("Erreur d'execution des futurs");
				System.exit(0);
			}
		}
		this.pool.shutdown();
		
		return retour;
	}
	
	public static void main(String[] args) {
		FileStatsByCallable fsc = new FileStatsByCallable();
		TimeLogger.log("Depart avec %d pool",poolSize);
		FileStats f = fsc.fromDir(args[0]);
		System.out.println(f);
		TimeLogger.log("Terminé !");
	}
}