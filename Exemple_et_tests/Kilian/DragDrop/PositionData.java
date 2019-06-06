

/**
 * Position Data est une classe permettant de stocker
 * des positions 2D composés d'entier.
 * 
 * @author Dovabear
 * @version 1.0
 */


public class PositionData{
	private int m_x;
	private int m_y;

	/**
	*@param n_x
	*		paramètre qui donne la position x
	*@param n_y
	*		paramètre qui donne la position y
	*/
	public PositionData(int n_x,int n_y){
		this.m_x = n_x;
		this.m_y = n_y;
	}

	//Méthodes get

	/**
	*@return la position x de l'intance
	*/
	public int getXPos(){return this.m_x;}

	/**
	*@return la position y de l'insatnce
	*/
	public int getYPos(){return this.m_y;}

	//Méthodes set

	/**
	*Initialise les positions x et y
	*@param n_x
	*		paramètre qui initialise la position x
	*@param n_y
	*		paramètre qui initialise la position y
	*/
	public void setPos(int n_x , int n_y){
		this.m_x = n_x;
		this.m_y = n_y;}

	/**
	*initialise la position x
	*@param n_x
	*		valeur de la position x a changer
	*/
	public void setXPos(int n_x){this.m_x = n_x;}

	/**
	*initalise la position y
	*@param n_y
	*		valeur de la position y a changer
	*/
	public void setYPos(int n_y){this.m_y = n_y;}
}