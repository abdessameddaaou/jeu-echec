package modele;


public class Fou extends Piece{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fou(String Couleur) {
		super("Fou", Couleur);
	}

	@Override
	public boolean estValide(Deplacement deplacement) {
	
		return Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) == 0 && !deplacement.isNul();

	}
	
}
