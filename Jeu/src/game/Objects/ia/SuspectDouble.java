package game.Objects.ia;

import game.Objects.Coordonnees;

import java.util.ArrayList;
import java.util.Objects;

public class SuspectDouble {
    private Coordonnees coordonnee;
    private Coordonnees origine;
    private Coordonnees direction;
    private ArrayList<SuspectDouble> enfants;
    private boolean epuiser;




    public SuspectDouble(Coordonnees coordonnee, Coordonnees direction) {
        this.coordonnee = coordonnee;
        this.origine = new Coordonnees(coordonnee);
        this.direction = direction;
        this.enfants = new ArrayList<>();
        epuiser = false;
    }

    public SuspectDouble(Coordonnees coordonnee, Coordonnees direction,boolean epuiser) {
        this.coordonnee =  new Coordonnees(coordonnee);
        this.origine = new Coordonnees(coordonnee);
        this.direction = new Coordonnees(direction);
        this.enfants = new ArrayList<>();
        this.epuiser = epuiser;
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

    public void addEnfant(SuspectDouble enfant){
        enfants.add(enfant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuspectDouble suspect = (SuspectDouble) o;
        return Objects.equals(coordonnee, suspect.coordonnee) &&
                Objects.equals(direction, suspect.direction);
    }

    public void epuisement(){
        if (!epuiser){
            epuiser = true;
            coordonnee = new Coordonnees(origine);
            direction = new Coordonnees(direction.getX()*-1,direction.getY()*-1);
        }
    }

    public boolean isEpuiser() {
        return epuiser;
    }

    public ArrayList<SuspectDouble> getEnfants() {
        return enfants;
    }
}
