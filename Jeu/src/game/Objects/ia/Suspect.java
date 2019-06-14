package game.Objects.ia;

import game.Objects.Coordonnees;

public class Suspect {
    private Coordonnees coordonnee;
    private Coordonnees direction;

    

    public Suspect(Coordonnees coordonnee, Coordonnees direction) {
        this.coordonnee = coordonnee;
        this.direction = direction;
    }

    public void incremente(){
       coordonnee = coordonnee.add(direction);
    }

    public Coordonnees getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coordonnees coordonnee) {
        this.coordonnee = coordonnee;
    }

    public Coordonnees getDirection() {
        return direction;
    }

    public void setDirection(Coordonnees direction) {
        this.direction = direction;
    }
}
