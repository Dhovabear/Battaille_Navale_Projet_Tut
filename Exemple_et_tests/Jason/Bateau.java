public class Bateau{

	private Coordonnees[] cases;

	public Bateau(){}

	public void setTaille(int taille){
		//On définit la taille du tableau de coordonnées dont on aura besoin
		cases = new Coordonnees[taille];
	}

	public boolean touche(int x, int y){
		//On vérifie si l'une des Coordonnée du Bateau est touchée 
		for(int i=0;i<this.cases.length;i++){
			if(this.cases[i].getX()==x && this.cases[i].getY()==y){
				this.cases[i].setTouche();
				
				return true;
			}
		}
		return false;
	}

	public void setCoordonnees(int c,int x, int y){
		//On définit les différentes coordonnés du bateau
		this.cases[c].setXY(x,y); 
	}

	//Retourne l'état du bateau, true = "en vie" et false = coulé
	public boolean getEnVie(){
		int verif = 0;
		//On regarde toutes les cases du bateau
		for(int i=0;i<this.cases.length;i++){
			//Si une case du bateau est touchée
			if(this.cases[i].getTouche() == true){
				verif++;
			}
		}
		//Si toutes les cases sont touchées, le bateau est coulé
		if(verif == this.cases.length){
			return false;
		}
		return true; //Le bateau est encore "en vie"
	}
}