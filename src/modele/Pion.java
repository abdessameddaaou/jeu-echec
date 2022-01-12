package modele;

public class Pion extends Piece {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pion(String Couleur) {
		super("Pion", Couleur);
	}

	@Override
	public boolean estValide(Deplacement deplacement) {
		
		if (deplacement.getDeplacementX() == 0)
			if (this.getCouleur().equals("noir")){
				
				return deplacement.getDeplacementY() <= (deplacement.getDepart().getLigne() == 1 ? 1 : 1) && deplacement.getDeplacementY() > 0;
			}
			else 
				return deplacement.getDeplacementY() >= (deplacement.getDepart().getLigne() == 6 ? -1 : -1) && deplacement.getDeplacementY() < 0;
		return false;
	}


}
