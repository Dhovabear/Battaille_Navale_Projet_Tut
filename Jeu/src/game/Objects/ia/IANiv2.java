
package game.Objects.ia;

import game.Objects.Coordonnees;
import game.scenes.Jouer;
import java.util.ArrayList;

public class IANiv2 extends IANiv1 {

    protected Coordonnees[] listeOrientation;
    protected ArrayList<Suspect> suspect;
    protected ArrayList<Coordonnees> dejaTouche;


    public IANiv2(Jouer j, int m){
        super(j,m);
        suspect = new ArrayList<>();
        dejaTouche = new ArrayList<>();
        listeOrientation = new Coordonnees[4];
        listeOrientation[0]= new Coordonnees(1,0);
        listeOrientation[1]= new Coordonnees(-1,0);
        listeOrientation[2]= new Coordonnees(0,1);
        listeOrientation[3]= new Coordonnees(0,-1);
        derTouche = new Coordonnees();

    }

    public void jouer(){
        Coordonnees p;
        int i;
        int j;
        do {
            if (this.suspect.size() == 0) {
                i = (int) (Math.random() * 10);
                j = (int) (Math.random() * 10);
                derTouche.setXY(i, j);
            } else {
                i = suspect.get(0).getCoordonnee().getX();
                j = suspect.get(0).getCoordonnee().getY();
                derTouche.setXY(i, j);
            }

            if ((!ctrl.autorisationTirOrdi(derTouche)) && suspect.size() != 0) {
                suspect.remove(0);
            }
        }while(grilleAdverse[derTouche.getX()][derTouche.getY()]!=0 || !ctrl.autorisationTirOrdi(derTouche));
        ctrl.tir();
        grilleAdverse[derTouche.getX()][derTouche.getY()]=1;
        retour();
    }



    public void retour(){
        boolean touche = aTouche();
        if(suspect.size()!=0){
            suspect.get(0).incremente();
            if(!touche || !valide(suspect.get(0).getCoordonnee().getX(),suspect.get(0).getCoordonnee().getY())){
                suspect.remove(0);
            }
        }else if (touche){
                for (int j=0;j<4;j++){
                    if(valide(derTouche.add(listeOrientation[j]).getX(),derTouche.add(listeOrientation[j]).getY())){
                           suspect.add(new Suspect(derTouche.add(listeOrientation[j]),listeOrientation[j]));
                    }
                }
        }


    }

    public boolean aTouche(){
        for (int i = 0; i < 5; i++) {
            for (int j=0; j< ctrl.getBateauxJoueur(i).taille;j++){
                if (ctrl.getBateauxJoueur(i).getCases(j).getTouche() && ctrl.getBateauxJoueur(i).getCases(j).equals(derTouche)){
                    return true;
                }
            }
        }
        return false;
    }


}

