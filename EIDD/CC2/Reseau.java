public interface Reseau{
	/* retourne le nombre de personnes sur ce reseau */
	int getPopulation();

	/* retourne true si les individus de numéro i1 et i2 sont ami.e.s */
	boolean areFriends(int id1, int id2);

	/* permet de fixer la relation d’amitié entre i1 et i2 : oui si b=true, non si b=
	false*/
	void setFriends(int id1, int id2,boolean b);	

	/* permet d’ajouter un individu au reseau (qui correspondra alors au numéro N, si
	N etait le nb de personnes sur le réseau précédemment */
	void ajouteIndividu();

	public int nbAmis(int i){

	}
}



