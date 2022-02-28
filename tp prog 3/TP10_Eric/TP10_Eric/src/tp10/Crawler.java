package tp10;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// TP10 Eric CORTIAL

class Crawler {
	static HttpClient client = HttpClient.newBuilder()
			 .version(HttpClient.Version.HTTP_2)
			 .followRedirects(HttpClient.Redirect.NORMAL)
			 .connectTimeout(Duration.ofSeconds(20))
			 // Création d'un client (static) en variable de classe
			 // Permet d'envoyer les requetes
			 .build(); 
	
	/**
	 * Fonction permettant d'envoyer une requete synchrone a partir d'une url.
	 * Renvoit la page HTML dans un stream contenant une seul ligne de toute la page.
	 * @param e
	 * @return 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public static Stream<String> send_client_page_sync(String e) throws IOException, InterruptedException 
	{
		// Création d'une nouvelle requete à partir de l'url.
		HttpRequest request = HttpRequest.newBuilder(URI.create(e)).GET().build(); 
		// Envoit par le client en demandant un retour en string. 
		// On récupere son body pour avoir un stream de string.
		return Crawler.client.send(request, HttpResponse.BodyHandlers.ofLines()).body(); 
	}
	
	
	/**
	 * Fonction qui permet de recuperer une image de manière synchrone à partir de son url. 
	 * Puis, permet de la copier dans le path en parametre
	 * @param e
	 * @param string_path
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public static void send_client_image_sync(String e,String string_path) throws IOException, InterruptedException 
	{
		// On crée le path en ajoutant au chemin le nom de l'image qu'on recupere grâce à getRootImage (contient la racine de l'image ainsi que son extension)
		Path path = Paths.get(string_path+"/"+getRootImage(e)); 
		// On crée une requete, on l'envoit en demandant de copier la réponse dans le chemin trouvé précédemment
		HttpRequest request = HttpRequest.newBuilder(URI.create(e)).GET().build(); 
		Crawler.client.send(request, HttpResponse.BodyHandlers.ofFile(path)); 
	}
	
			 
	/**
	 * fonction qui envoit une requete de maniere asynchrone. 
	 * Renvoit un futur contenant la page htlm
	 * @param e
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public static CompletableFuture<HttpResponse<Stream<String>>> send_client_page_async(String e) throws IOException, InterruptedException 
	{
		HttpRequest request = HttpRequest.newBuilder(URI.create(e)).GET().build();
		return Crawler.client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());
	}
	
	
	/**
	 * Fonction qui telecharge l'image dans le chemin passé en paramétre et qui a comme nom sa racine. 
	 * Renvoie aussi un futur
	 * @param e
	 * @param string_path
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public static CompletableFuture<HttpResponse<Path>> send_client_image_async(String e,String string_path) throws IOException, InterruptedException 
	{
		Path path = Paths.get(string_path+"/"+getRootImage(e));
		HttpRequest request = HttpRequest.newBuilder(URI.create(e)).GET().build();
		return Crawler.client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(path));
	}
	
	
	/**
	 * Fonction qui renvoie l'url d'une image a partir d'un string d'une page html.
	 * @param e
	 * @return
	 */
	
	public static String getURLImage(String e) {
		/**
		 * On cree une regex qui contient :
		 - n'importe quoi avant 
		 - https:// 
		 - une adresse de site : toto.com 
		 - un slash 
		 - la racine : suite de caractère) 
		 - une extension d'image. On ne recupere ques les images voulu et pas les images du sites (ex : logo).
		 */
		String regex = ".*(https://.*/.*(\\.jpg|\\.jpeg|\\.gif|\\.webp)).*";  
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(e);
		mat.find();
		return mat.group(1);
	}
	
	
	/**
	 * Fonction qui a partir d'une url d'une image recupére sa racine et son extension
	 * @param e
	 * @return
	 */
	
	public static String getRootImage(String e) {
		// On recupère tout ce qui se trouve aprés le slash
		String regex = "https://.*/(.*(\\.jpg|\\.jpeg|\\.gif|\\.webp))";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(e);
		mat.find();
		return mat.group(1);
	}
	
	
	/**
	 * Fonction qui telecharge les image de manière asynchrone.
	 * @param args
	 */
	
	public static void Download_async(String[] args) {
		// Récupèration des adresses a partir de la classe URLs
		Stream<String> stream_url = Arrays.stream(URLs.imagePages); 
		// List de reponse futur
		List<CompletableFuture<HttpResponse<Stream<String>>>> list_pages_futur= new ArrayList<>();
		TimeLogger.log("Depart avec %d pages HTML a telechargée", URLs.imagePages.length);
		stream_url.forEach(e-> {
			try {
				// Ajout dans la liste de tous les futurs des images que l'on a envoyé.
				list_pages_futur.add(Crawler.send_client_page_async(e)); 
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		// List qui contient les strings des pages html.
		List<Stream<String>> list_pages = new ArrayList<>(); 
		// Add des pages quand les threads sont terminés.
		list_pages_futur.forEach(e -> list_pages.add(e.join().body())); 
		TimeLogger.log("Terminé");
		// List qui contiendra uniquement les urls des images que l'on cherche a telecharger.
		List<String> list_url_images= new ArrayList<>();
		// Add dans la List des urls Images les urls que l'on trouve grace a la fonction. Uniquement le premier
		list_pages.forEach(e-> list_url_images.add(e.map(f -> Crawler.getURLImage(f)).findFirst().get()));
		// list de futur qui seront les renvoies des demandes de telechargements des images
		List<CompletableFuture<HttpResponse<Path>>> list_futur_dl= new ArrayList<>(); 
		TimeLogger.log("Depart avec %d image a telechargée", list_pages.size());
		list_url_images.forEach(e -> {
			try {
				// Ajout par telechargement
				list_futur_dl.add(Crawler.send_client_image_async(e, args[0])); 
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});

		list_futur_dl.forEach(CompletableFuture::join);//on attends la fin de chaque thread
		TimeLogger.log("Terminé");
	}
	
	
	/**
	 * Fonction qui telecharge de maniére synchrone les images
	 * @param args
	 */
	
	public static void Download_sync(String[] args) {
		Stream<String> stream_url = Arrays.stream(URLs.imagePages); 
		// On crée une List qui contiendront les pages HTML
		List<Stream<String>> list_pages= new ArrayList<>();
		TimeLogger.log("Depart avec %d pages HTML a telechargée", URLs.imagePages.length);
		stream_url.forEach(e-> {
			try {
				list_pages.add(Crawler.send_client_page_sync(e)); 
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		TimeLogger.log("Terminé");
		// List qui contient les URLs des images que l'on veut telecharger
		List<String> list_url_images= new ArrayList<>(); 
		// On ajoute dans la liste des urls d'images les URLs que l'on retrouve grâce à la fonction qui utilise une regex.
		list_pages.forEach(e-> list_url_images.add(e.map(f -> Crawler.getURLImage(f)).findFirst().get())); 
		TimeLogger.log("Depart avec %d image a telechargée", list_pages.size());
		list_url_images.forEach(e -> {
			try {
				// On telecharge les images
				Crawler.send_client_image_sync(e, args[0]); 
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		TimeLogger.log("Terminé");
	}
	
	public static void main(String[] args) {
		if(args.length != 1) 
		{
			System.out.println("Uncomplete argument in the main");
			System.exit(0);
		}
		try {
			Crawler.Download_async(args);
			//Crawler.Download_sync(args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
