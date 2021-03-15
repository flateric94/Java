import java.util.*;

public class ReseauListes {
	/* une liste de listes. amis.get(i) correspond a la liste des amis de i */		
	private List<List<Integer>> amis;
	
	public ReseauListes(int n){
		amis = new ArrayList<List<Integer>>(); 
		for(int i = 0; i<n ; i++){
			amis.add(new ArrayList<Integer>());
		}
	}

	public int getPopulation(){
		return amis.size();
	}

	public boolean areFriends(int id1, int id2){
		return this.amis.get(id1).contains(id2);
	}

	public void setFriends(int id1, int id2, boolean b){
		this.amis.get(id1).add(id2);
		this.amis.get(id2).add(id1);
	}

	public void ajouteIndividu(){
		this.amis.add(this.getPopulation());
	}
}

