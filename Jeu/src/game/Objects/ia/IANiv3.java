/*
import java.util.ArrayList;

public class IANiv3 extends IANiv1 {
    protected int mode = 0;
    protected Coordonnees[] listeOrientation;
    protected int[][] statistique;
    protected final static int[] prioritee = {0,1,3,2,4};
    protected int tourne;
    protected int tourneI;
    protected int total;
    protected ArrayList<Coordonnees> suspect;


    public IANiv3(Jouer j, int m){
        super(j,m);
        statistique= new int[10][10];
        total = 0;



        String fichier = "memoire.txt";
        String[] grilleTempo = readFile(fichier).split("!");
        for (int a = 0; a<10;a++){
            String[] ligneTempo = grilleTempo[a].split(":");
            for (int b = 0; b<10;b++){
                if (statistique[a][b]>0){
                    statistique[a][b] = Integer.parseInt(ligneTempo[b]);
                    total += Integer.parseInt(ligneTempo[b]);
                }
            }
        }

        suspect = new ArrayList<>();
        this.tourneI = 0;
        this.tourne = 0;
        listeOrientation = new Coordonnees[4];
        listeOrientation[0]= new Coordonnees(1,0);
        listeOrientation[1]= new Coordonnees(0,1);
        listeOrientation[2]= new Coordonnees(-1,0);
        listeOrientation[3]= new Coordonnees(0,-1);
        derTouche = new Coordonnees();

    }

    public void jouer(){
        Coordonnees p;
        int a=0;
        int b=0;
        int i=0;
        boolean trouver = false;
        do {
            p = new Coordonnees();
            a=0;
            b=0;
            if (this.mode == 0) {
                i = (int) (Math.random() * total);
                for (; a<10;a++){
                    for (; b<10;b++){
                        i -= statistique[a][b];
                        if (i<0 && !trouver && grilleAdverse[a][b]==0){
                            trouver = true;
                            derTouche.setXY(a,b);
                        }
                    }
                }
            } else if (this.mode == 1) {
                tourneI++;
                p = derTouche.add(listeOrientation[stable(tourne + tourneI)]);
            } else {
                derTouche = derTouche.add(listeOrientation[stable(tourne + tourneI)]);
                p = derTouche;
                if(depassementGrille(derTouche.getX(),derTouche.getY())&& suspect.size()>0){
                    derTouche = suspect.get(0);
                    suspect.remove(0);
                    tourneI = stable(tourneI+2);
                }else{
                    mode = 0;
                }
            }
        }while(grilleAdverse[derTouche.getX()][derTouche.getY()]!=0 || !ctrl.autorisationTirOrdi(p) || depassementGrille(derTouche.getX(),derTouche.getY()));
        grilleAdverse[a][b]=1;
    }

    public void jouerSpe(){
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
    }



    public void retour(){
        int r =0;
        for(int i=0;i<5;i++){
            if(ctrl.getBateauxJoueur(i).touche(derTouche.getX(),derTouche.getY())){
                if (ctrl.getBateauxJoueur(i).getEnVie()){
                    r=1;
                }else{
                    r=2;
                }
            }
        }

        if(r == 1 && mode == 0) {
            mode++;
            tourne = (int) (Math.random() * 4);
            tourneI = 0;
        }else if(r == 1 && mode == 1){
            mode++;
            suspect.add(new Coordonnees( derTouche.add(listeOrientation[stable(tourne+tourneI+2)])));
            derTouche= derTouche.add(listeOrientation[stable(tourne+tourneI)]);
        }else if((r == 2)){
            this.mode = 0;
        }else if (r==0 && mode==2 && suspect.size()>0){
            derTouche = suspect.get(0);
            suspect.remove(0);
            tourneI = stable(tourneI+2);
        }

        if(r == 1 || r == 2){
            grilleAdverse[derTouche.getX()][derTouche.getY()]=2;
        }
    }


}
*/
