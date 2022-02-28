package tp8;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BinaryOperator;

class FileStats {
	long fichier = 0;
	long ligne = 0;
	long character = 0;
		
	public FileStats() {
	}
	
	public FileStats(long fichier, long ligne, long character) {
		this.fichier = fichier;
		this.ligne = ligne;
		this.character = character;
	}
	
	// BinaryOperator sommant l'interieur des FileStats
	public static BinaryOperator<FileStats> combine() {
		return (e,f) -> new FileStats(e.fichier+f.fichier,e.ligne+f.ligne,e.character+f.character);
	}
	
	
	public void add(FileStats f) {
		this.character += f.character;
		this.ligne += f.ligne;
		this.fichier += f.fichier;
	}
	
	public static FileStats fromPath (Path path){
		FileStats str = null;
		try {
			str = Files.lines(path, StandardCharsets.UTF_8)
					.map((e) -> e.trim())
					// On recupere les strings des lignes en négligeant les commentaires
					.filter(e -> e.startsWith("//") || 
								 e.startsWith("*") || 
								 e.startsWith("/*")) 
					.map((e) -> new FileStats(0,1,e.length())) //on cree des filestats sur les lignes avec leurs nombre de caractÃ©res
					.reduce(new FileStats(1,0,0),FileStats.combine());// on les combines en commencant par un filestat qui contient un fichier
		} catch (IOException e1) {
			System.out.println("ERROR : no file found");
			System.exit(0);
		}		
		return str;
	}
}