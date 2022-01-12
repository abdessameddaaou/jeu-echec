package modele;

import java.io.Serializable;

public class Case implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/* On a cr�er une classe case qui contiendera la classe piece 
 * on acr�er cette classe car dans notre grille on a cr�er des case de type JLabel et on ajout� au case en fonction de leur emplacement
 * 
 * 
 * */
 Piece piece;

	public Case()
	{
		
	}
	

	public Case(Piece piece)
	{
		this.piece = piece;
	}
	
	public Piece getPiece() 
	{
		return piece;
	}
	
	
	public void setPiece(Piece piece) 
	{
		this.piece = piece;
	}
	
	public boolean estOccupe()
	{
		return (piece != null);
	}
	public boolean estOccupe(String couleur)
	{
		if (piece == null)
			return false;
		else
			return (piece.getCouleur().equals(couleur));
	}
	
	
}
