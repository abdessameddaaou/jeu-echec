package modele;

public  class Tour extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tour(String Couleur) {
		super("Tour", Couleur);
	

	}

	@Override
	public boolean estValide(Deplacement deplacement) {
		
		return ( (deplacement.getDeplacementX()==0 || deplacement.getDeplacementY()==0) );
	}
}
