public class ReseauMatrice implements Reseau{	
	/** Une matrice de boolean
	 *  si i et j sont amis sur ce reseau si matrice[i][j]=true ET matrice[j][i]=true
	 **/
	boolean[][] matrice;
	
	public ReseauMatrice(int N){
		this.matrice = new boolean[N][N];
	}

	public int getPopulation(){
		return this.matrice.length;
	}

	public boolean areFriends(int id1, int id2){
		if (id1 < 0 || id1 >= this.matrice.length || id2 < 0 || id2 >= this.matrice.length){
			throw new IllegalArgumentException;
		}
		return this.matrice[id1][id2];
	}

	void setFriends(int id1, int id2,boolean b){
		if (id1 < 0 || id1 >= this.matrice.length || id2 < 0 || id2 >= this.matrice.length){
			throw new IllegalArgumentException;
		}
		this.matrice[id1][id2] = b;
		this.matrice[id2][id1] = b;
	}

	void ajouteIndividu(){
		boolean[][] new_matrice = new boolean[this.getPopulation()][this.getPopulation()];
		for (int i = 0; i < this.getPopulation(); i++){
			for (int j = 0; j < this.getPopulation(); j++){
				new_matrice[i][j] = this.matrice[i][j];
			}
		}
		for (int h = 0; h < this.getPopulation(); h++){
			new_matrice[this.getPopulation()][h] = false;
		}
		this.matrice = new_matrice;
	}
}

