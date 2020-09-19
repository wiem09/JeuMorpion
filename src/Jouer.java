
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Jouer 
{

	public static void main(String[] args) 
	{
		// Création d'une Instance Morpion
		Morpion morp = new Morpion();
		
		morp.setSize(290,290);
		morp.setVisible(true);
	}
}