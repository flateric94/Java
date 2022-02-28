package tp8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileStatsByStream {
	
	public FileStats fromDir (String rootDir)
	{
		Stream<Path> str_path = null;
		try {
			str_path = Files.walk(Paths.get(rootDir));
		} catch (IOException e1) {
			System.out.println("Erreur dans le parcourt de l'arborescence|" + e1.getMessage());
			System.exit(0);
		}
		FileStats retour = str_path.parallel()
				.filter(Predicate.not(Files::isDirectory))
				.filter(Files::isReadable)
				// On recupere les fichiers qui se termine par .java et qui sont lisibles
				.filter((e) -> e.toString().endsWith(".java")) 
				// On créé les FileStats a partir des lignes
				.map(FileStats::fromPath)
				// On les réduit à un seul Filestat, que l'on return
				.reduce(new FileStats(0,0,0),FileStats.combine()); 
		
		str_path.close();
		return retour;
	}
	
	
	public static void main(String[] args) {
		FileStatsByStream fss = new FileStatsByStream();
		TimeLogger.log("Depart avec juste un parallel");
		FileStats f = fss.fromDir(args[0]);
		System.out.println(f);
		TimeLogger.log("Terminé !");
	}
}