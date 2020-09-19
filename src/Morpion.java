
import javax.swing.*; 	 
import java.awt.*;		 
import java.awt.event.*; 
import java.util.*;	  



class Morpion extends JFrame implements ActionListener
{
		
	private Cases cas0,cas1,cas2,cas3,cas4,cas5,cas6,cas7,cas8;
	private Cases pans[] = { cas0,cas1,cas2,cas3,cas4,cas5,cas6,cas7,cas8 };
	private Partie part;
	private JPanel haut, bas;
	private JButton but;
	
	private GridLayout grid;
	
	// Constructeur de la classe Morpion
	public Morpion()
	{
		setTitle("Morpion");
				
		Container cont = getContentPane();
		
		// Le tableau des Cases est transmis au constructeur de Partie
		part = new Partie(pans);
		
		// Cr�ation de 2 JPanel pour la mise en forme de la fenetre
		haut = new JPanel();
		cont.add(haut);
		bas = new JPanel();
		cont.add(bas,"South");

		// Le gestionnaire de mise en forme du Panel haut est red�fini
		// en grille de 3 lignes, 3 colonnes avec 2 pixels entre chaques
		haut.setLayout(new GridLayout(3,3,2,2));

		// Cr�ation des 9 Cases et ajout au Panel haut
		for (int  i=0 ; i<9 ; i++)
		{
			pans[i] = new  Cases(part,i);
			haut.add(pans[i]);
		}
				
		// D�finition du bouton, ajout au Panel bas
		but = new JButton("Commencer");
		bas.add(but);

		// Ajout d'un ActionListener (�couteur d'action) sur le bouton
		// Cela va permettre d'intercepter le click par le biais de la 
		// m�thode actionPerformed (nom de m�thode d�ja d�fini et � respecter)

		but.addActionListener(this);
					
	}

	// Methode qui intercepte toutes les actions se d�roulant dans la classe
	// Re�oit l'�venement d�clencheur en argument
	
	public void actionPerformed(ActionEvent e)
	{
		// Test sur la source de l'�v�nement
		// Ici le test aurait pu etre supprim� etant donn� qu'il n'y �
		// q'un seul bouton dans ma classe
		
		if (e.getSource() == but)
		{
			// Cr�ation d'une boite de dialogue de type Dialogue d�riv�e de JDial
			// Le constructeur attend comme argument une fenetre et une partie
			Dialogue dial = new Dialogue(this,part);
			dial.setSize(200,200);
			
			// Recupere la taille de l'�cran et positionne lla Dialogue au milieu
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			dial.setLocation((screenSize.width-dial.getWidth())/2,(screenSize.height-dial.getHeight())/2);
			
			dial.setVisible(true);
			
			// Lance la partie
			part.commencer();
		}
	}
	
}





 
