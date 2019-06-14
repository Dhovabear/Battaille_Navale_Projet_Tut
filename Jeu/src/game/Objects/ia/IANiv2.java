
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
        listeOrientation[1]= new Coordonnees(0,1);
        listeOrientation[2]= new Coordonnees(-1,0);
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

            if ((!ctrl.autorisationTirOrdi(derTouche)||grilleAdverse[derTouche.getX()][derTouche.getY()]!=0) && suspect.size() != 0) {
                suspect.remove(0);
            }
        }while(grilleAdverse[derTouche.getX()][derTouche.getY()]!=0 || !ctrl.autorisationTirOrdi(derTouche));
        ctrl.tir();
        grilleAdverse[derTouche.getX()][derTouche.getY()]=1;
        retour();
    }

   /* public void jouerSpe(){
        Coordonnees p;
        int i=0;
        int j=0;
        k =4;
        p = new Coordonnees();
        do {
            if(k == 4) {
                p = new Coordonnees();
                if (this.mode == 0) {
                    i = (int) (Math.random() * 10);
                    j = (int) (Math.random() * 10);
                    derTouche.setXY(i, j);
                } else if (this.mode == 1) {
                    tourneI++;
                    p = derTouche.add(listeOrientation[tourne + tourneI]);
                } else {
                    derTouche = derTouche.add(listeOrientation[tourne + tourneI]);
                    p = derTouche;
                    if (depassementGrille(i, j)) {
                        mode = 0;
                    }
                }
            }else{
                k++;
                if(k==3){
                    k++;
                }
            }
        }while(grilleAdverse[i][j]!=0 || !ctrl.autorisationTirOrdi(p,k) || depassementGrille(i,j));
        grilleAdverse[i][j]=1;
    } */

    public void retour(){
        ArrayList<Coordonnees> touche = aTouche();
        if(suspect.size()!=0){
            suspect.get(0).incremente();
            if(touche.size()==0 || !depassementGrille(suspect.get(0).getCoordonnee().getX(),suspect.get(0).getCoordonnee().getY())){
                suspect.remove(0);
            }
        }else{
            for (Coordonnees i : touche){
                for (int j=0;j<4;j++){
                    if(depassementGrille(i.add(listeOrientation[j]).getX(),i.add(listeOrientation[j]).getY())){
                       if (grilleAdverse[i.add(listeOrientation[j]).getX()][i.add(listeOrientation[j]).getY()]==0){
                           suspect.add(new Suspect(i.add(listeOrientation[j]),listeOrientation[j]));
                       }
                    }
                }
            }
        }


    }

    public ArrayList<Coordonnees> aTouche(){
        ArrayList<Coordonnees> retour = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j=0; j< ctrl.getBateauxJoueur(i).taille;j++){
                if (ctrl.getBateauxJoueur(i).getCases(j).getTouche()){
                    if (!dejaTouche.contains(ctrl.getBateauxJoueur(i).getCases(j))){
                        dejaTouche.add(ctrl.getBateauxJoueur(i).getCases(j));
                        retour.add(ctrl.getBateauxJoueur(i).getCases(j));
                    }
                }
            }
        }
        return retour;
    }
}

