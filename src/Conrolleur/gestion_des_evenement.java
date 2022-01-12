package Conrolleur;

import java.io.IOException;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

import Vue.FenetreJeu;


public class gestion_des_evenement  extends MouseAdapter implements ActionListener
{

	
	/*cette classe représente le controlleur qui hérite de la classe MouseAdapter  puis on a trouvé un problème pour identifier quel bouton on a cliqué c'est pour cela qu'on implimenter aussi l'interface
	 * ActionListener pour utiliser la méthode ActionPerformed
	 * 
	 * pour faire le lien entre la classe fenetrejeu et cette classe on a instancié un objet de type FenetreJeu
	 * 
	 * */
	FenetreJeu util = new FenetreJeu();


	@Override			
	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
			 if(e.getSource() instanceof JLabel)
				{

					for (int i = 0; i < 7; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							
							if (e.getSource() == FenetreJeu.tab[j][i]) 
							{
								util.ligneClic = i;
								util.colonneClic = j;
							}
						}
					}
					
					if((util.ech.getCase(util.colonneClic, util.ligneClic).getPiece() != null | util.piece != null) )
					{
						
						if( util.ech.getCase(util.colonneClic, util.ligneClic).getPiece() != null && 
						((util.ech.getCase(util.colonneClic, util.ligneClic).getPiece().getCouleur().equals(util.couleur_controle))))
						{    	 
							if(util.ech.getCase(util.colonneClic, util.ligneClic).getPiece().getCouleur().equals(util.couleur_controle))
							{
								System.out.println("yayy2");
								util.paint();
								
							}
							
						}	
						else
						{
								System.out.println("yayy 3");
								util.repaint();						
						}
	
			
					}
					else
					{
						System.out.println("ayyy sortie ");
					}

				}
				
				else
				{
					System.out.println("ayyy6");
				}
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand() == "S") {
			try {
				util.sauvgarde();
				System.err.println("yes");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand() == "s")
		{
			util.dessiner();
		}
		if(e.getActionCommand() == "C") {
			try {
				System.err.println("yes");
				util.charger();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
		}
		
	}
	
}