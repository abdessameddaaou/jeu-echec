package modele;

import Vue.FenetreJeu;

public class Echiquier
{
	
	/* dans cette classe on a fait deux grande fonction capturer et chemin possible pour voir si le chemin et libre ou non 
	 * 
	 * estoccupe c'est une méthode dans la calsse case qui sert a savoir si la case est occupe ou non d'une piece d'une 
	 * couleur passée en parametre
	 * 
	 *  la methode chemin possible, nous permet de verifier si le chemin est libre ou bloqué 
	 *  
	 *  cette fonction nous permet de voir si un pion est dans le coté ou non si il est dans le coté il a le droit de passé ou capturer

	 * */

	public Case[][] loc;
	public static FenetreJeu util2 =new FenetreJeu();

	public Echiquier() 
	{
		loc = new Case[8][7];
		
		for (int i = 0; i <= 7; i++)
		{
			for (int j = 0; j <= 6; j++)
			{
				loc[i][j] = new Case();
			}
		}
		
}
	
	public static Case[][] setLoc(Case[][] loca)
	{
		return loca;
	}

	public Case getCase(int colonne, int ligne) 
	{
		return loc[colonne][ligne];
	}
	public Case setCase(int colonne, int ligne) 
	{
		return loc[colonne][ligne];
	}
	
	public boolean captureParUnPionPossible(Deplacement deplacement) 
	{

		
		if(loc[deplacement.getDepart().getColonne()][deplacement.getDepart().getLigne()].getPiece() instanceof Pion)
		{
			
			Case Arrive = loc[deplacement.getArrivee().getColonne()][deplacement.getArrivee().getLigne()];
			String couleurDepart = loc[deplacement.getDepart().getColonne()][deplacement.getDepart().getLigne()].getPiece().getCouleur();
			
			
			if(Arrive.estOccupe(couleurDepart.equals("blanc") ? "noir" : "blanc") && ((deplacement.getDeplacementX()/deplacement.getDeplacementY()==1) || (deplacement.getDeplacementX()/deplacement.getDeplacementY()==-1) ))
			
				return true;
		}
		return false;
		
	}
	

		public boolean cheminPossible(Deplacement deplacement) 
		{
			Piece pieceDepart = loc[(int)deplacement.getDepart().getColonne()][(int)deplacement.getDepart().getLigne()].getPiece();
			
			
			if (!loc[(int)deplacement.getArrivee().getColonne()][(int)deplacement.getArrivee().getLigne()].estOccupe(pieceDepart.getCouleur().equals("blanc") ? "blanc" : "noir") | deplacement.isNul())
			{
				
				if (!(pieceDepart instanceof Cavalier))
				{
					if(!(pieceDepart instanceof Pion))
					{
					
						if(!(Math.abs(deplacement.getDeplacementX()) - Math.abs(deplacement.getDeplacementY()) <= 1 && Math.abs(deplacement.getDeplacementX()) + Math.abs(deplacement.getDeplacementY()) <= 1))
						{

							int jumpX = deplacement.getDeplacementX() == 0 ? 0 : (int)(deplacement.getArrivee().getColonne() - deplacement.getDepart().getColonne())
									/Math.abs((int)(deplacement.getArrivee().getColonne() - deplacement.getDepart().getColonne()));
					
							int jumpY = deplacement.getDeplacementY() == 0 ? 0 : (int)(deplacement.getArrivee().getLigne() - deplacement.getDepart().getLigne())
									/Math.abs((int)(deplacement.getArrivee().getLigne() - deplacement.getDepart().getLigne()));

							
							for (int ctrX = (int)deplacement.getDepart().getColonne() + jumpX, ctrY = (int)deplacement.getDepart().getLigne() + jumpY; ctrX != (int)deplacement.getArrivee().getColonne() ||
									ctrY != (int)deplacement.getArrivee().getLigne(); ctrX += jumpX, ctrY += jumpY)
							{
								
								if (loc[ctrX][ctrY].estOccupe())
								{
									return false;
								}
							}
							return true;
						}
						else
						{

							return true;
						}
					}
					else
					
						return !loc[(int)deplacement.getArrivee().getColonne()][(int)deplacement.getArrivee().getLigne()].estOccupe();
						
				}
				else
					
					return true;
			}
			else

				return false;

			
	}
}
