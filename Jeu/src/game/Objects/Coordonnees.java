package game.Objects;

public class Coordonnees{

	private int x;
	private int y;
	private boolean touche; //Utilisé uniquement pour les objets Bateau

	public Coordonnees(){
		this.touche = false;
	}

	public Coordonnees(int x,int y){
		this();
		setXY(x,y);
	}

	public void setXY(int x,int y){
		this.x = x;
		this.y = y;
	}

	public void setTouche(){
		this.touche = true;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public boolean getTouche(){
		return this.touche;
	}
}