import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

class Cases extends JPanel
{
	private boolean etatCas = false;
	private Partie _part;
	private int _indice;
	private int symbole = 2;
	
	// Constructeur de Cases
	// Re�oit une r�f�rence � la partie et son indice
	public Cases(Partie part, int indice)
	{
		_indice = indice;	
		_part = part;
		setSize(60,60);
		setBackground(Color.lightGray);
		
		// Ajoute un MouseListener (�couteur de souris) qui va
		// intercepter les clicks sur le Cases
		addMouseListener(new MouseAdapter()
			{
				// D�finition de la m�thode mouseClicked qui sera 
				// automatiquement appel�e quand il y aura un click
				//c'est la commande new MouseAdapter qui permet 
				//de la d�finir de suite
				public void mouseClicked(MouseEvent e)
				{
					if (_part.getIsRunning())
						_part.jeu((Cases)e.getSource());
				}
			});
	}
	
	// M�thode qui renvoit le symbole du Cases
	public int getSymbo()
	{
		return symbole;
	}

	// M�thode qui affecte le symbole du Cases	
	public void setSymbo(int symb)
	{
		symbole = symb;
	}

	// M�thode qui renvoit l'�tat du Cases (d�ssin� oui / non )
	public boolean getEtCase()
	{
		return etatCas;
	}
	
	// M�thode qui affecte l'�tat du Cases
	public void setEtCase(boolean etat)
	{
		etatCas = etat;
	}	
	
	// M�thode qui renvoit l'indice du Cases
	public int getIndice()
	{
		return _indice;	
	}
	
	// M�thode qui est appel� � la cr�ation d'un Cases et
	// d�s que l'on fait un Cases.repaint()
	
	public void paintComponent(Graphics g)
	{
		// Appel de la m�thode paintComponent de la classe parente
		super.paintComponent(g);
		
		if (symbole == _part.CROIX)
		{
			// Dessine une croix
			g.setColor(Color.red);
			
			g.drawLine(5,5,50,50);
			g.drawLine(50,5,5,50);
		}
		if (symbole == _part.ROND)
		{
			// Dessine un rond
			g.setColor(Color.black);
			g.drawOval(5,5,this.getWidth()-5,this.getHeight()-5);
		}
	}
	
}