public class Enclos {

    Animal[] tabEnclos;

    public Enclos(int taille) {//constructeur tabl de bonne taille
        this.tabEnclos = new Animal[taille]; //on met ce tabl dans this.tabEnclos
    }

	public int nbAnimaux(){
		int c=0;
		for(int i =0; i< this.tabEnclos.length; i++){
			if(this.tabEnclos[i]!=null)
				c++;
		}
		return c;	
	}
	
	public int nbPlacesLibres(){
		return tabEnclos.length-this.nbAnimaux();
	}


    public boolean ajout(Animal a) {
		int spotLibre = -1;
		
        for(int i =0; i< this.tabEnclos.length; i++){
			if(this.tabEnclos[i]==null){
				spotLibre = i;
			}
			else if(this.tabEnclos[i].estCompatibleAvec(a)==false) {
            	return false;
        	}
		}
		//si on arrive la il n'y a pas d'incompatibilite
		
		if(spotLibre!=-1){
			this.tabEnclos[spotLibre]=a;
			return true;   
		}
		else{
			return false;
		}
    }
    
    public void nourrir(){
        for(int i =0; i< this.tabEnclos.length; i++){
			if(tabEnclos[i]!=null && this.tabEnclos[i].faim>5)
				this.tabEnclos[i].nourrir();	        
        }    	
    }
    
    public void reproduction(){
		boolean[] acopule = new boolean[this.tabEnclos.length];
		
		for(int i =0; i< this.tabEnclos.length; i++){
			Animal a = this.tabEnclos[i];
			if(a!=null && acopule[i]==false){
				for(int j = i+1; j< this.tabEnclos.length; j++){
						Animal b = this.tabEnclos[j];
						if(b!=null && acopule[j]==false){
							Animal bb = a.reproduction(b);
							if(bb!=null){
								acopule[i]=true;
								acopule[j]=false;
								this.ajout(bb);
							}
						}
				}
			}
		
		}
	}
	
	public void passeUnJour(){
    	for(int j = 0 ; j< tabEnclos.length; j++){
    			Animal a = this.tabEnclos[j];
    			if(a!=null){
				a.age++;
				a.faim++;
				if(a.faim>=10){
					System.out.println(a.nom+ " est mort de faim ");
					this.tabEnclos[j]=null;
				}
			}
				
		}						
	}
	
	public String toString(){
			String s = "";
			for(int i = 0; i<this.tabEnclos.length; i++){
					Animal a = this.tabEnclos[i];
					if(a!=null){
						s+=this.tabEnclos[i].description();
						s+="\n";
					}
			}
			return s;
	}
	
	
	
	
    
}
