
package game.Objects.ia;

import game.Objects.Coordonnees;
import game.scenes.Jouer;

public class IANiv2 extends IANiv1 {
    protected int mode;
    protected Coordonnees[] listeOrientation;
    protected int tourne;
    protected int tourneI;


    public IANiv2(Jouer j, int m){
        super(j,m);
        this.tourneI = 0;
        this.tourne = 0;
        listeOrientation = new Coordonnees[4];
        listeOrientation[0]= new Coordonnees(1,0);
        listeOrientation[1]= new Coordonnees(0,1);
        listeOrientation[2]= new Coordonnees(-1,0);
        listeOrientation[3]= new Coordonnees(0,-1);
        derTouche = new Coordonnees();
        this.mode = 0;

    }

    public void jouer(){
        Coordonnees p;
        int i;
        int j;
        do {
            p = new Coordonnees();

            if (this.mode == 0) {
                i = (int) (Math.random() * 10);
                j = (int) (Math.random() * 10);
                p.setXY(i,j);
                derTouche.setXY(i, j);
            } else if (this.mode == 1) {
                tourneI++;
                p = derTouche.add(listeOrientation[stable(tourne + tourneI)]);
            } else {
                derTouche = derTouche.add(listeOrientation[stable(tourne + tourneI)]);
                p = derTouche;

            }

            if(!depassementGrille(p.getX(),p.getY()) ){
               if(grilleAdverse[p.getX()][p.getY()]!=0 && mode==2) {
                   mode = 0;
               }
            }
        }while(grilleAdverse[p.getX()][p.getY()]!=0 || !ctrl.autorisationTirOrdi(p));
        ctrl.tir();
        grilleAdverse[p.getX()][p.getY()]=1;
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
        int r =0;
        if (mode !=1) {
           r= aTouche(derTouche);
        }else{
            r= aTouche( derTouche.add(listeOrientation[stable(tourne + tourneI)]));
        }

        if(r == 1 && mode == 0) {
            mode++;
            tourne = (int) (Math.random() * 4);
            tourneI = 0;
        }else if(r == 1 && mode == 1){
            mode++;
            derTouche= derTouche.add(listeOrientation[stable(tourne+tourneI)]);
        }else if((r == 2)||(r==0 && mode==2)){
            this.mode = 0;
        }

        if(r == 1 || r == 2){
            grilleAdverse[derTouche.getX()][derTouche.getY()]=2;
        }
    }

    public int aTouche(Coordonnees c){
        for (int i = 0; i < 5; i++) {
            if (ctrl.getBateauxJoueur(i).touche(c.getX(), c.getY())) {
                if (ctrl.getBateauxJoueur(i).getEnVie()) {
                    return  1;
                } else {
                    return 2;
                }
            }
        }
        return 0;
    }
}

