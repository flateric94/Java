import java.util.*;

public class Test{
	//static String[] prenoms={"toto","Milou","Marty","Chewie","Medor","Kong","Skipper","Brutus","Jokky","Michmich"};
	//static String[] espece={"lion","gazelle","croco","ours","biche","tigre","elephant"};
	//static String[][] incompat = {{"lion","gazelle"},{"lion","biche"},{"tigre","biche"},{"tigre","gazelle"},{"croco","gazelle"},{"croco","biche"}};
	
		/*
	//public static Animal randomAnimal(){
		
		
		
		
		
		
		
		
		
		Random r = new Random();
		String p = prenoms[r.nextInt(prenoms.length)];
		String e = espece[r.nextInt(espece.length)];
		char s = r.nextBoolean()? 'm' : 'f';
		int a = r.nextInt(10);
		int po = r.nextInt(1000);
		Animal ani = new Animal(p,e);
		ani.age=a;
		ani.sexe=s;
		ani.poids=po;
		return ani;
	}
	
	public static void main(String[] arg){
		Animal a = new Animal("Toto","chien",'m',12,4);
		Animal b = new Animal("Tata","chien",'f',3,3);
		
		String d = a.description();
		System.out.println(d);
		
		a.nourrir();
		b.nourrir();
		System.out.println(a.description());

		Animal[] tabA = {a,b};
		System.out.println("Le plus gros est "+Animal.plusGros(tabA).nom);
		
		Animal bebe = a.reproduction(b);
		
		System.out.println(bebe.description());

	}
	
	/*
	 * 
	 * 
	public static void main(String[] arg){
		Zoo z = new Zoo("Paris");
		Animal.incompatibilite=incompat;
		int n = 3;
		z.tabEnclos = new Enclos[n];
		for(int i = 0 ; i<n; i++){
			z.tabEnclos[i]=new Enclos(15);			
			for(int j = 0; j<7; j++){
				Animal a = randomAnimal();
				System.out.println(a.description()+"\n"+z.tabEnclos[i].ajout(a));
			} 
		}
		
		z.print();
		for(int i = 1; i<50; i++){
			System.out.println("JOUR "+i+ "\n");
				z.passeUnJour();
				z.print();
				z.nourrir();
		}
		

		
	
	}
	*/



	public static void main(String[] args){
		Animal a,b;
		a = new Animal("Kiki","chien");
		b = new Animal("Kiki","chien");
		b.id=a.id;
		
		System.out.println(a.equals(b));
		

		
	}
}
