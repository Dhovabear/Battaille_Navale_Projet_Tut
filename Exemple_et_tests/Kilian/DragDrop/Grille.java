
import java.util.Arrays;

public class Grille{

	private int m_taille;
	private int m_tailleCellule;

	private int m_xPosition;
	private int m_yPosition;

	private List<GridObject> m_objetsDeLaGrille;

	public Grille(int n_taille , int n_tailleCellule, int n_posX , int n_posY){
		this.m_taille = n_taille;
		this.m_tailleCellule = n_tailleCellule;
		this.m_xPosition = n_posX;
		this.m_yPosition = n_posY;
	}

	/*public void ajouterBateau(Bateau n_bateau , int n_posX , int n_posY){
		if(n_posX > m_taille || n_posY > m_taille){
			return;
		}

		switch (n_bateau.getOrientation()) {
			case HAUT:
				for (int i = 1; i < n_bateau.getTaille() ; i++){
					if()
				}
		}

		this.m_objetsDeLaGrille.add(n_bateau);
	}*/

	public boolean estDisponible(int posX , int posY){
		//On teste si la case est hors des limites de la grille
		if(posX > m_taille || posX < 0 || posY > m_taille || posY < 0)
			return false;


		for ( GridObject obj : m_objetsDeLaGrille) {
			//Pour chaque objet de la grille on regarde si sa
			//position est la mème que celle que l'on verifie
			//si c'est e cas alors la case est dej occuper
			//On retourne donc false
			if(obj.getXPos() == posX && obj.getYPos() == posY)
				return false;

			//on regarde si l'objet en question est un bateau
			//si c'est le cas alors, on regarde aussi ses positions
			//que le reste du bateau occupe
			if(obj instanceof Bateau){
				for (PositionData dat : obj.getPositions() ) {
					if(dat.getXPos() == posX && dat.getYPos() == posY)
						return false;
				}
			}
		}
		return true;
	}

}