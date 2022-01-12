package modele;


public class Roi extends Piece{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Roi(String Couleur) {
		super("Roi", Couleur);
	}

	@Override
	public boolean estValide(Deplacement deplacement) {
	
		
		return Math.abs(deplacement.getDeplacementX()) * Math.abs(deplacement.getDeplacementY()) <= 1
				&& Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) <= 1
				&& Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) >= -1
					&& !deplacement.isNul();
	}

}
