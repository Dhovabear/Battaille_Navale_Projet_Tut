
/**
* Float position est identique a PointData a la diffèrence 
* que la position est en float.
*@see PositionData
*
*@author Dovabear
*@version 1.0
*
*/

public class FloatPosition{
	float m_x;
	float m_y;

	/**
	* Le constructeur vide qui permet d'initialiser 
	* la position a (0,0)
	*/
	public FloatPosition(){
		m_x = 0f;
		m_y = 0f;
	}

	/**
	* Le constructeur de base qui prend pour paramètre une pos(x,y)
	*@param n_x
	*		valeur que prendra x quand il s'initialisera
	*@param n_y
	*		valeur que prendra y quand il s'initialisera
	*/
	public FloatPosition(float n_x , float n_y){
		m_x = n_x;
		m_y = n_y;
	}

	/**
	* @return la valeur de la position x de l'instance
	*/
	public float getXPos(){return this.m_x;}

	/**
	*@return la valeur de la position y de l'instance
	*/
	public float getYPos(){return this.m_y;}


	/**
	*initialise la position x et y
	*@param n_x
	*		valeur que prendra x quand il s'initialisera
	*@param n_y
	*		valeur que prendra y quand il s'initialisera
	*/
	public void setPos(float n_x , float n_y){
		m_x = n_x;
		m_y = n_y;
	}

	/**
	*initalise la position x
	*@param n_x
	*		valeur que prendra x quand il s'initialisera
	*/
	public void setXPos(float n_x){m_x = n_x;}

	/**
	*initalise la position y
	*@param n_y
	*		valeur que prendra y quand il s'initialisera
	*/
	public void setYPos(float n_y){m_y = n_y;}

}