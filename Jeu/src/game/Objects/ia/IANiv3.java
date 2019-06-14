package game.Objects.ia;

import game.Objects.Bateau;
import game.Objects.Coordonnees;
import game.scenes.Jouer;
import java.util.ArrayList;

public class IANiv3 extends IANiv2 {

    protected int[][] statistique;
    protected int total;
    protected ArrayList<SuspectDouble> suspect;
    protected Coordonnees[] reListeOren;
    private ArrayList<Bateau> dejaMort;


    public IANiv3(Jouer j, int m){
        super(j,m);
        statistique= new int[10][10];
        total = 0;
        suspect = new ArrayList<>();
        dejaMort =  new ArrayList<>();
        reListeOren = new Coordonnees[2];

        reListeOren[0]= new Coordonnees(1,0);
        reListeOren[1]= new Coordonnees(0,1);

        String fichier = "/donnes/memoire.txt";
        String[] grilleTempo = readFile(fichier).split("!");
        for (int a = 0; a<10;a++){
            String[] ligneTempo = grilleTempo[a].split(":");
            for (int b = 0; b<10;b++){
                if (statistique[a][b]>0){
                    statistique[a][b] = Integer.parseInt(ligneTempo[b]);
                    total += Integer.parseInt(ligneTempo[b]);
                    if(a%2==0 && b%2==0){
                        statistique[a][b] *=2;
                        total += Integer.parseInt(ligneTempo[b]);
                    }
                }
            }
        }


    }

    public void jouer(){
        Coordonnees p;
        int a=0;
        int b=0;
        int i=0;
        boolean passage= false;
        boolean trouver = false;
        do {

            if (this.suspect.size() == 0) {
                i = (int) (Math.random() * total);
                for (a=0; a<10;a++){
                    for (b=0; b<10;b++){
                        i -= statistique[a][b];
                        if (i<0 && !trouver){
                            trouver = true;
                            if(grilleAdverse[a][b]==0){
                                derTouche.setXY(a,b);
                                passage = true;
                            }
                            total -= statistique[a][b];
                            statistique[a][b] = 0;
                        }
                    }
                }
            } else {
                a = suspect.get(0).getCoordonnee().getX();
                b = suspect.get(0).getCoordonnee().getY();
                derTouche.setXY(a, b);
            }
        }while(!passage && grilleAdverse[derTouche.getX()][derTouche.getY()]!=0 || !ctrl.autorisationTirOrdi(derTouche));
        ctrl.tir();
        grilleAdverse[derTouche.getX()][derTouche.getY()]=1;
        retour();
    }

    /*public void jouerSpe(){
        Coordonnees p;
        int a=0;
        int b=0;
        int i=0;
        k=4;
        boolean trouver = false;
        p = new Coordonnees();
        do {
            if(k == 4) {
                a = 0;
                b = 0;
                k=0;
                if (this.mode == 0) {
                    i = (int) (Math.random() * total);
                    for (; a < 10; a++) {
                        for (; b < 10; b++) {
                            i -= statistique[a][b];
                            if (i < 0 && !trouver && grilleAdverse[a][b] == 0) {
                                trouver = true;
                                derTouche.setXY(a, b);
                            }
                        }
                    }
                } else if (this.mode == 1) {
                    tourneI++;
                    p = derTouche.add(listeOrientation[stable(tourne + tourneI)]);
                } else {
                    derTouche = derTouche.add(listeOrientation[stable(tourne + tourneI)]);
                    p = derTouche;
                    if (depassementGrille(derTouche.getX(), derTouche.getY()) && suspect.size() > 0) {
                        derTouche = suspect.get(0);
                        suspect.remove(0);
                        tourneI = stable(tourneI + 2);
                    } else {
                        mode = 0;
                    }
                }
            }else{
                k++;
            }
        }while(grilleAdverse[a][b]!=0 || !ctrl.autorisationTirOrdi(p,prioritee[k]) || depassementGrille(a,b));
        grilleAdverse[a][b]=1;
    }*/



    public void retour(){
        ArrayList<Coordonnees> touche = aTouche();
        Boolean mort = unMort();
        if(suspect.size()!=0){

            suspect.get(0).incremente();

            if(touche.size()==0 || !depassementGrille(suspect.get(0).getCoordonnee().getX(),suspect.get(0).getCoordonnee().getY())){
                if(!suspect.get(0).isEpuiser()){
                    suspect.get(0).epuisement();
                }else {
                    suspect.remove(0);
                }
            }else if(mort){
                suspect.get(0).suprEnfant();
                suspect.remove(0);
            }else{
                int d =0;
                if(suspect.get(0).getDirection().equals(reListeOren[0])) {
                    d = 1;
                }

                if(depassementGrille(suspect.get(0).getCoordonnee().add(listeOrientation[d]).getX(),suspect.get(0).getCoordonnee().add(listeOrientation[d]).getY())){
                    if (grilleAdverse[suspect.get(0).getCoordonnee().add(listeOrientation[d]).getX()][suspect.get(0).getCoordonnee().add(listeOrientation[d]).getY()]==0){
                        SuspectDouble tempo = new SuspectDouble(suspect.get(0).getCoordonnee().add(listeOrientation[d]),listeOrientation[d]);
                        suspect.add(tempo);
                        suspect.get(0).addEnfant(tempo);
                    }
                }
            }

        }else{
            for (Coordonnees i : touche){
                int t = (int)(Math.random()*2);
                for (int j=t;j<(t+2);j++){
                    int r = stable(j);
                    if(depassementGrille(i.add(listeOrientation[r]).getX(),i.add(listeOrientation[r]).getY())){
                        if (grilleAdverse[i.add(listeOrientation[r]).getX()][i.add(listeOrientation[r]).getY()]==0){
                            suspect.add(new SuspectDouble(i.add(listeOrientation[r]),listeOrientation[r]));
                        }
                    }
                }
            }
        }


    }

    public boolean unMort(){
        for (int i = 0; i < 5; i++) {
                if (!ctrl.getBateauxJoueur(i).getEnVie() && !dejaMort.contains(ctrl.getBateauxJoueur(i))){
                   dejaMort.add(ctrl.getBateauxJoueur(i));
                   return true;
                }
        }
        return false;
    }

    public int stable(int i){
        if (i>1){
            return i-2;
        }
        if (i<0){
            return i+2;
        }
        return i;
    }

}

