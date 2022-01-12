package modele;


public class Deplacement {
	
	// Deplacement sur l'axe des X

	private double deplacementX;
	
	// Deplacement sur l'axe des Y
	private double deplacementY;
	
	//Coordonnee de la case d'arrivee
	private Position arrivee;
	
	// Coordonnee de la case d'�part
	private Position depart;
	
/* cette m�thode d�placement va nous permettre pour avoir les colonne de d�part arriv� et les dplacement x et y qui va nous
 * faciliter le travaille par la suite pour d�finir les d�placment
 * 
 * 
 * */
	
	public Deplacement(Position depart, Position arrivee)
	{
		this.arrivee = arrivee;
		this.depart = depart;
		this.deplacementX = arrivee.getColonne() - depart.getColonne();
		this.deplacementY = arrivee.getLigne() - depart.getLigne();
	}


	public double getDeplacementX() {
		return deplacementX;
	}

	public double getDeplacementY() {
		return deplacementY;
	}
	
	public Position getArrivee() {
		return arrivee;
	}

	public Position getDepart() {
		return depart;
	}
	
	//v�rifie si le d�placement est nul.
	public boolean isNul(){
		return deplacementX == 0 && deplacementY == 0;
	}
	
}
