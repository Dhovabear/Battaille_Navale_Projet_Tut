
import java.util.ArrayList;

public abstract class GridObject{
	private PositionData m_position;

	

	public GridObject(int n_posX , int n_posY){
		this.m_position.setPos(n_posX , n_posY);
	}

	public GridObject(){
		this.m_position.setPos(0,0);
	}

	//Méthodes get
	public int getXPos(){return this.m_position.getXPos();}
	public int getYPos(){return this.m_position.getYPos();}

	//Méthodes set
	public void setPos(int n_posX , int n_posY){this.m_position.setPos(n_posX,n_posY);}
	public void setXPos(int n_value){this.m_position.setXPos(n_value);}
	public void setYPos(int n_value){this.m_position.setYPos(n_value);}


	//Autres Méthodes



}	
