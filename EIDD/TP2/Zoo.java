public class Zoo {

    String ville;
    Enclos[] tabEnclos;

    
    public Zoo(String ville){
    	this.ville=ville;
    }


    public boolean ajout(Animal a) {
        int i =0;
        while(i<tabEnclos.length && !tabEnclos[i].ajout(a)){
        	i++;
        }
        if(i==tabEnclos.length)
        	return false;
        else
        	return true;
        	   
    }
    
    public void nourrir(){
    	for(int i = 0 ; i< tabEnclos.length; i++){
    		tabEnclos[i].nourrir();
    	}
    }
    
    public void passeUnJour(){
    	for(int i = 0 ; i< tabEnclos.length; i++){
			tabEnclos[i].passeUnJour();
			tabEnclos[i].reproduction();			
		}
    
    }
    
    public void print(){
    	for(int i = 0 ; i< tabEnclos.length; i++){
			System.out.print("-- Enclos no "+i+"\n"+tabEnclos[i].toString());
		}
		System.out.println("-------------\n");
	}
}
