
import java.awt.image.BufferedImage;

public abstract class Bateau extends GridObject {

	private byte m_taille;
	public enum orientation{
		HAUT,
		DROITE,
		BAS,
		GAUCHE;}
	private orientation m_orientationActuelle = orientation.BAS;
	private PositionData[] m_positions;

	public Bateau(byte n_taille , orientation n_orientation , int n_posX , int n_posY){
		super(n_posX,n_posY);
		m_taille = n_taille;
		m_orientationActuelle = n_orientation;
	}

	public Bateau(byte n_taille , orientation n_orientation){
		super();
		m_taille = n_taille;
		m_orientationActuelle = n_orientation;
	}

	public int getTaille(){return this.m_taille;}
	public orientation getOrientation(){return this.m_orientationActuelle;}
	public PositionData[] getPositions(){return this.m_positions;}



}