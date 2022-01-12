package modele;


public class Reine extends Piece{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Reine(String Couleur) {
		super("Reine", Couleur);
	}

	@Override
	public boolean estValide(Deplacement deplacement) {
		
		return (Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) == 0 | deplacement.getDeplacementX() * deplacement.getDeplacementY() == 0) && !deplacement.isNul();

	}
		
}
