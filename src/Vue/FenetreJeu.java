package Vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.*;
import javax.swing.*;

import Conrolleur.gestion_des_evenement;
import modele.Case;
import modele.Cavalier;
import modele.Deplacement;
import modele.Echiquier;
import modele.Fou;
import modele.Piece;
import modele.Pion;
import modele.Position;
import modele.Reine;
import modele.Roi;
import modele.Tour;

import java.awt.Color;


@SuppressWarnings("serial")
public class FenetreJeu extends JFrame 
{

	/* Cette classe represente la vue de l'application. On a créer le JFrame et les JPanel qui 
	 * contients les differents bouttons et la table de chess
	 * */
	
	public  JPanel panelControle = new JPanel(); 
	public  JButton start = new JButton();
	public  JButton sauvgarder = new JButton();
	public  JButton charger = new JButton();
	public  JPanel panelGrille = new JPanel(); 
	public  GridLayout gridLayout1 = new GridLayout();
	public  JPanel prom = new JPanel();
	
	public static JLabel[][] tab= new JLabel[8][7];
	public static JLabel[][] tabpromo= new JLabel[4][1];
	
	public static Properties properties = new Properties();
	

	public static gestion_des_evenement gestion= new gestion_des_evenement();

	public int colonneClic,ligneClic;
	public  Echiquier ech;
	public ImageIcon iconeTampon;
	public Position pos;
	public String couleur_controle = "blanc";
	public Deplacement deplacement;
	public Piece piece=null;
	
	public FenetreJeu()
	{

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
	/* Dans cette fonction on a affché les JPanel, JButton.
	 *  pour la grille on l'a donné un gridlayout de 8 colonne et 7 ligne qui nous permettera de diviser la grille.
	 *  Puis on les a ajouter au JFrame.
	 *  
	 *  Aprés on a ajouter les écouteurs au bouttons.
	 *  
	 *  Ensuite on a créer un boolean white on l'a donné la valeur true, puis on a créé des case de JLabel et 
	 *  chaque case on l'infecte a mouseListener. ensuit on change la valuer de booléen pour permutter entre blanc et gris
	 *  
	 *  A la fin de la boucle, on obtient la table de chess complete.
	 * */
	public void jbInit() throws Exception 
	{

		ech = new Echiquier();

		
	
		this.setSize(new Dimension(1000, 700));
		this.setTitle("Jeu d'Echecs");
		
		panelGrille.setLayout(gridLayout1);
		
		gridLayout1.setColumns(8);
		gridLayout1.setRows(7);
		panelGrille.setBounds(0,50,764,587);
		this.getContentPane().add(panelGrille);
		
		
	
		start.setText("Start");
		sauvgarder.setText("sauvgarder la partie");
		charger.setText("charger la partie");

		
		sauvgarder.setActionCommand("S");
		start.setActionCommand("s");
		charger.setActionCommand("C");
		
		
		panelControle.add(start);
		panelControle.add(prom);
		
		prom.setLayout(new GridLayout(4, 1, 0, 0));
		prom.setSize(new Dimension(100,300));
		
		//panelControle.setAlignmentX(Component.RIGHT_ALIGNMENT);

		panelControle.setSize(new Dimension(764,113));	
		panelControle.setBackground(Color.lightGray);
		this.getContentPane().add(panelControle);


		start.addActionListener(gestion);
		sauvgarder.addActionListener(gestion);
		charger.addActionListener(gestion);
	
		
		boolean white= true;
		for(int ligne=0; ligne<7; ligne ++)
		{
			for(int colonne = 0; colonne<8; colonne ++)
			{
				tab[colonne][ligne] = new JLabel();
				panelGrille.add(tab[colonne][ligne]);
				tab[colonne][ligne].setOpaque(true);
				
				tab[colonne][ligne].setHorizontalAlignment(SwingConstants.CENTER);
				
				if (white)
					tab[colonne][ligne].setBackground(new Color(255, 255, 255));
				else
					tab[colonne][ligne].setBackground(new Color(190, 190, 190));
	
				tab[colonne][ligne].addMouseListener(gestion);
				white=!white;
			}
			white =! white;
		}
		
	}
	
		/* Dans cette fonction, on a mit en place une boucle qui prend à chaque fois une lettre de la liste nommée les_pieces
		 * et l'affecte au chemin de la photo ce qui nous permet de récupérer à chaque fois une phot correspondante à la piece.
		 * 
		 * Puis on initialise la case avec l'objet de la piece correspondante.
		 * 
		 * */
		public void  dessiner()
		{
			String dossierIcone = "Icone/";
			char[] les_pieces = { 'T', 'C', 'F', 'D', 'R', 'F', 'C', 'T' };
			int encore = 1;
			int ligne = 0;
			char couleur = 'N';
		
			int emplacement_les_pieces;
			
			while(encore >= -1)
			{
				for(emplacement_les_pieces = 0; emplacement_les_pieces < 8; emplacement_les_pieces++)
				{
					tab[emplacement_les_pieces][ligne].setIcon(new ImageIcon(dossierIcone + les_pieces[emplacement_les_pieces] + couleur + ".gif"));
				
					switch(les_pieces[emplacement_les_pieces])
					{
						case 'T':
							piece = new Tour(ligne <2 ? "noir" : "blanc");
						break;
						
						case 'C':
							piece = new Cavalier(ligne < 2 ? "noir" : "blanc");
						break;
						
						case 'F':
							piece = new Fou(ligne < 2 ? "noir" : "blanc");
						break;
						
						case 'D':
							piece = new Reine(ligne < 2 ? "noir" : "blanc");
						break;
						
						case 'R':
							piece = new Roi(ligne < 2 ? "noir" : "blanc");
						break;
					}
					ech.getCase(emplacement_les_pieces, ligne).setPiece(piece);
					tab[emplacement_les_pieces][ligne + encore].setIcon(new ImageIcon(dossierIcone + 'P' + couleur + ".gif"));
					ech.getCase(emplacement_les_pieces, ligne + encore).setPiece(new Pion(ligne < 2 ? "noir" : "blanc"));
					System.out.println(ech.getCase(emplacement_les_pieces, ligne).getPiece().getNom());
				}
				couleur = 'B';
				encore = encore -2;
				ligne = 6;
			
				
			}
			promotion();
			
		}

		/* on a créé une méthode qu'on l'a appelé paint cette méthode sera éxécuté quand le joueur clique sur le bouton start a l'aide
		 * de la fonction getActionCommand()
		 * 
		 * Puis on rend le cadre de la case séléctionnée vert pour indiquer au joueur ce qu'il a choisi
		 * */
	
		public void paint()
		{
			if(ech.getCase(colonneClic, ligneClic).getPiece().getCouleur().equals(couleur_controle))
			{
				
				piece = ech.getCase(colonneClic, ligneClic).getPiece();
				pos = new Position(colonneClic,ligneClic);
				iconeTampon = (ImageIcon)tab[colonneClic][ligneClic].getIcon(); 
	
				tab[colonneClic][ligneClic].setBorder(BorderFactory.createLineBorder(new Color(57, 230, 0),4));
			
				
			}
		} 
		
		
		/* pour mettre a jour les pion on les récupère et on modifi grace au setters
		 * 
		 * estValide(deplacement): sert à indiquer si la piéce sélectionnée est autorisée à boujer vers la case d'arrivé sélectionné ou non.
		 * captureParUnPionPossible: sert à indiquer si la case d'arrivé contient une piece qu'on peut capturer.
		 * 
		 * à la fin on trouve une méthode pour permutter la couleur à chaque fois qu'un joueur joue son tour.
		 * */
		public void repaint()
		{
			deplacement = new Deplacement(pos, new Position(colonneClic,ligneClic));
			
			if(piece.estValide(deplacement) && ech.cheminPossible(deplacement) || ech.captureParUnPionPossible(deplacement) )
			{
				
				if(ech.getCase(colonneClic, ligneClic).getPiece() instanceof Roi)
				{
					

					ech.getCase(pos.getColonne(), pos.getLigne()).setPiece(null);
					tab[pos.getColonne()][pos.getLigne()].setIcon(null);
					tab[pos.getColonne()][pos.getLigne()].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),0));//beh la coulleur te3 bourdire terja3 kima kanet ki ybadal blassa
		
					tab[colonneClic][ligneClic].setIcon(iconeTampon);
					ech.getCase(colonneClic, ligneClic).setPiece(piece);
					System.exit(0);

						

				}
				else
				{
					ech.getCase(pos.getColonne(), pos.getLigne()).setPiece(null);
					tab[pos.getColonne()][pos.getLigne()].setIcon(null);
					tab[pos.getColonne()][pos.getLigne()].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),0));//beh la coulleur te3 bourdire terja3 kima kanet ki ybadal blassa
		
					tab[colonneClic][ligneClic].setIcon(iconeTampon);
					ech.getCase(colonneClic, ligneClic).setPiece(piece);
					
		
					
					if(couleur_controle.equals("blanc"))
					{
						couleur_controle="noir";
					}
					else
					{
						couleur_controle="blanc";
					}
				}
			}
			else
			{
				tab[pos.getColonne()][pos.getLigne()].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),0));
				System.out.println("yayy 4");
			}
		}
		
		

		public void sauvgarde() throws IOException
		{
		
			/*
			FileOutputStream fos = new FileOutputStream("C:\\Users\\daaou\\eclipse-workspace\\projet_java_miage\\properties\\sauvgarde_information.properties");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ech.loc);
			*/
			
			try {
				FileOutputStream fos = new FileOutputStream("C:\\Users\\daaou\\eclipse-workspace\\projet_java_miage\\properties\\sauvgarde_information.properties") ; 
				ObjectOutputStream oos = new ObjectOutputStream(fos) ; 
				
				oos.writeObject(ech.loc);
				oos.close() ; 
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		public void charger() throws Exception
		{
			/*
			FileInputStream fis = new FileInputStream("C:\\Users\\daaou\\eclipse-workspace\\projet_java_miage\\properties\\sauvgarde_information.properties");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ech.setLoc((Case[][])ois.readObject());
			*/
				
			try {
				FileInputStream fis = new FileInputStream("C:\\Users\\daaou\\eclipse-workspace\\projet_java_miage\\properties\\sauvgarde_information.properties") ; 
				ObjectInputStream ois = new ObjectInputStream(fis) ; 
				
				Echiquier.setLoc((Case[][])ois.readObject()) ; 
				ois.close() ; 				
			}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}catch(IOException e)
			{
				e.printStackTrace();
			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		public void promotion()
		{
			
			for(int i=0; i<4; i++) {
				tabpromo[i][0] = new JLabel();
				tabpromo[i][0].setIcon( new ImageIcon("Icone/TB.gif") );
				tabpromo[i][0].setBackground(new Color(255, 255, 255));
				tabpromo[i][0].setBorder(BorderFactory.createLineBorder(new Color(57, 230, 0),2));
				tabpromo[i][0].setOpaque(true);
				tabpromo[i][0].setVisible(true);
				
				tabpromo[i][0].setHorizontalAlignment(SwingConstants.CENTER);

				prom.add(tabpromo[i][0]);
			}

		}
		
public static void main(String[] args) 
{
	FenetreJeu j = new FenetreJeu();
	j.setVisible(true);
	j.setLayout(null);
	j.setResizable(false);
	j.setDefaultCloseOperation(EXIT_ON_CLOSE); 
}

		
}
		
