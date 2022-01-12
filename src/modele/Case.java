package modele;

import java.io.Serializable;

public class Case implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/* On a créer une classe case qui contiendera la classe piece 
 * on acréer cette classe car dans notre grille on a créer des case de type JLabel et on ajouté au case en fonction de leur emplacement
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
