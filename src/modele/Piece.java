package modele;

import java.io.Serializable;

/* on a fait une classe abstracte qui sera une abstracte car on aura besoin d'utilisé la méthode estvalide pour valider les dpalcement dans 
 * les autre classe des piece pion, tour, cavalier, fou, reine, roi.
 * 
 * */

public abstract class Piece implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;// nom de la pièce
	private String couleur;//couleur de la piece

	public Piece(String nom, String couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		if ((couleur == "noir") || (couleur == "blanc"))
			this.couleur = couleur;
	}
	public abstract boolean estValide(Deplacement deplacement);


}