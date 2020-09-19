

public class Joueur
{
	private int 		_symb;
	private String		_nom;
	
	// Constructeur de Joueur
	// Re�oit le nom et le symbole et les affectent
	public Joueur(int symb, String nom)
	{
		_symb = symb;
		_nom = nom;		
	}
	
	// M�thode qui renvoit le symbole
	public int getSymb()
	{
		return _symb;
	}
	
	// M�thode qui renvoit le nom
	public String getNom()
	{
		return _nom;
	}
}

