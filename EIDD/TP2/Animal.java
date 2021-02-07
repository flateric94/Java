public class Animal{
	String nom;
	String espece;
	char sexe; // 'm' ou 'f'
	int poids;
	int age;
	int faim=5; // valeur defaut 
	
	int id;
	
	private static int nmax=0;
	private static String[][] incompatibilite = {{"chien","chat"},{"lion","gazelle"},{"chat","souris"}};
	
	
	/**
	constructeur vide : remplit le champ id avec une valeur unique
	*/
	public Animal(){
		Animal.nmax++;
		this.id=nmax;
	}
		
	public Animal(String n, String e){
		this();
		this.nom=n;
		this.espece=e;
		this.age=0;	
	}
	
	public Animal(String n,String e, char g, int p, int a){
		this(n,e);
		this.sexe=g;
		this.poids=p;
		this.age=a;
	}
		
	public String toString(){
		String s="";
		//s=s+"Animal no "+this.id+" : ";
		s=s+"Je m'appelle "+this.nom;
		s+=", je suis un";
		if(this.sexe=='m'){
			s+=" "+this.espece+" male";
		}
		else if(this.sexe=='f'){
			s+="e "+this.espece+" femelle";
		}	
		s+=" je pese "+this.poids+" kilos, et j'ai "+this.age+" ans";
		s+=" mon niveau de faim est "+this.faim;
		return s;
	}
	
	public boolean equals(Object b){
			Animal bb = (Animal) b;
			return this.id==b.id;		
	}
	
	public void nourrir(){
		this.faim-=5;
		if(this.faim<0) this.faim=0;		
	}

	public static Animal plusGros(Animal[] t ){
		int imax=0;
		for(int i =1; i<t.length; i++){
			if(t[i]!=null){
				if(t[i].poids>t[imax].poids){
					imax=i;
				}
			}
		}
		return t[imax];	
	}
	
	
	/**
	fonction permettant d'implémenter la reproduction entre animaux
	@param a un Animal

	@return un Animal issu de la reproduction de this et a
	*/
	public Animal reproduction(Animal a){
		if(a.espece.equals(this.espece) && a.sexe!=this.sexe && a.faim<=5 && this.faim<=5){
			
			Animal bb = new Animal();
			bb.nom=a.nom+this.nom;
			bb.espece=a.espece;
			if(Math.random()>0.5){
				bb.sexe='f';
			}
			else{
				bb.sexe='m';
			}
			
			bb.poids=(int) (a.poids+(this.poids-a.poids)*Math.random());
			
			return bb;						
		}
		else{
			return null;
		}
	}
	
	
	public boolean estCompatibleAvec(Animal a){
		for(int i = 0 ; i<Animal.incompatibilite.length; i++){
				if(	this.espece.equals(incompatibilite[i][0]) && a.espece.equals(incompatibilite[i][1])
					|| a.espece.equals(incompatibilite[i][0]) && this.espece.equals(incompatibilite[i][1])
				){
					return false;			
				}
						
		}
		return true;
	}
	
	
	
}
	


