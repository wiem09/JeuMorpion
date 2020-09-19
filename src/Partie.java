import java.util.Random;

import javax.swing.JOptionPane;


class Partie
{
	static public final int CROIX	= 0;
	static public final int ROND  	= 1;
	static public final int RIEN  	= 2;
	static public final int NON		= 10;
		
	private boolean 	isRunning = false;
	private int	 	tab[] = { RIEN,RIEN,RIEN,RIEN,RIEN,RIEN,RIEN,RIEN,RIEN };
	private int 		sol[][] = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
	private int 		strat = NON;
	private Joueur  	j1,j2;
	private Cases		pans[];
	public Partie(Cases t[])
	{
		pans = t;
		
	}
	public void createJoueurs(int symb, String nom)
	{
		j1 = new Joueur(symb,nom);
		if (symb == CROIX)
		{
			j2 = new Joueur(ROND,"L'Ordinateur");
			jeuOrdi();
		}
		else
			j2 = new Joueur(CROIX,"L'Ordinateur");
	}
	public int getCase(int _case)
	{
		return tab[_case];	
	}	
	public void setCase(int _case, int symb)
	{
		tab[_case] = symb;
	}
	public void commencer()
	{
		isRunning = true;
	}
	
	public void jeuOrdi()
	{
		int jouEn;
		
		jouEn = ouJouer(j2.getSymb());
		if (jouEn == NON)
		{
			jouEn = ouJouer(j1.getSymb());
			if (jouEn == NON)
			{
				gagnant2cps();
				if (strat == NON)
				{
					
					Random alea = new Random();
					jouEn = alea.nextInt(9);
					while ( tab[jouEn] != RIEN )
						jouEn = alea.nextInt(9);
				}
				else
					jouEn = strat;
			}
		}	
		tab[jouEn] = j2.getSymb();
		pans[jouEn].setSymbo(j2.getSymb());
		pans[jouEn].repaint();
		Rejouer(j2);
		
	}
	public void Rejouer(Joueur j)
	{
		int result = Fin(j.getSymb());			
		
		if (result == 1)
		{
			JOptionPane.showMessageDialog(null,j.getNom() + " a Gagné !");

			int rep = JOptionPane.showConfirmDialog(null,"Voulez vous rejouer ?","Partie finie",JOptionPane.YES_NO_OPTION)	;
			propRej(rep);
		}
		else if ( ( j.getNom() != "L'Ordinateur" ) && (result != RIEN) )
					jeuOrdi();
		
		if (result == RIEN)
		{
			int rep = JOptionPane.showConfirmDialog(null,"Voulez vous rejouer ?","Partie finie",JOptionPane.YES_NO_OPTION)	;
			propRej(rep);
		}
	}
	public void propRej(int rep)
	{
		
		if (rep == 0)
		{
			for (int i = 0 ; i < 9 ; i++)
			{
				pans[i].setSymbo(RIEN);
				pans[i].setEtCase(false);
				pans[i].repaint();
				tab[i] = RIEN;
			}
			if (j2.getSymb() == ROND)
				jeuOrdi();
		}
		else
			System.exit(0); 
			
	}
	public void jeu(Cases p)
	{
		if (p.getEtCase() == false)
		{
			p.setEtCase(true);
			
			// Dessine sur le Cases et met tab à jour
			p.setSymbo(j1.getSymb());
			tab[p.getIndice()] = j1.getSymb();
			p.repaint();
			Rejouer(j1);
		}
	}
	public boolean getIsRunning()
	{
		return isRunning;	
	}

	public int ouJouer(int symb)
	{
		int cases = 0,c1,c2,c3;
		int jouer = NON;
		
		while ( (jouer == NON) && (cases < 8) )
		{
			for (cases = 0 ; cases < 8  ; cases++)
			{
				c1 = tab[sol[cases][0]];
				c2 = tab[sol[cases][1]];
				c3 = tab[sol[cases][2]];
				
				if ( (c1 == symb) && ( (c2 == c1) && (c3 == RIEN) ) )
					jouer = sol[cases][2];
				if ( (c1 == symb) && ( (c3 == c1) && (c2 == RIEN) ) )
					jouer = sol[cases][1];
				if ( (c2 == symb) && ( (c2 == c3) && (c1 == RIEN) ) )
					jouer = sol[cases][0];
					
			}
		}
		return jouer;	
	}

	public void gagnant2cps()
	{
		int cases = 0,c1,c2,c3;
		strat = NON;
		
		while ( (strat == NON) && (cases < 8) )
		{
			for (cases = 0 ; cases < 8  ; cases++)
			{
				c1 = tab[sol[cases][0]];
				c2 = tab[sol[cases][1]];
				c3 = tab[sol[cases][2]];
				
				if ( (c1 == j2.getSymb()) && ( (c2 == c3) && (c3 == RIEN) ) )
					strat = sol[cases][2];
				if ( (c1 == j2.getSymb()) && ( (c3 == c2) && (c2 == RIEN) ) )
					strat = sol[cases][1];
				if ( (c2 == j2.getSymb()) && ( (c1 == c3) && (c1 == RIEN) ) )
					strat = sol[cases][0];
			}
		}	
	}

	public int Fin(int symbCur)
	{
		int cases = 0,c1,c2,c3,parc;
		parc = tab[cases++];
		while ( (parc != RIEN) && (cases < 9) )
			parc = tab[cases++];
		
		for (cases = 0 ; cases < 8  ; cases++)
		{
			c1 = tab[sol[cases][0]];
			c2 = tab[sol[cases][1]];
			c3 = tab[sol[cases][2]];
			
			if ( (c1 == symbCur) && ( (c2 == c1) && (c3 == c1) ) )
				return 1;
		}
		
		if (parc == RIEN)
			return 0;
		else
			return 2;
	}
}

