package modele;


public class Cavalier extends Piece {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cavalier(String Couleur) {
		
		super("Cavalier", Couleur);
	}

	public boolean estValide(Deplacement deplacement) {
		return (Math.abs(deplacement.getDeplacementX() / deplacement.getDeplacementY())) == 2 | (Math.abs(deplacement.getDeplacementX() / deplacement.getDeplacementY())) == .5;
			
			
		}
}