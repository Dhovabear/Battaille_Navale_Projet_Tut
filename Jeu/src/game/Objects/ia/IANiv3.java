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

        /*String fichier = "/donnes/memoire.txt";
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
        }*/
        for (int g=0;g<10;g++) {
            for (int h=0;h<10;h++) {
                if (g%2==0 && h%2 == 0){
                    statistique[g][h]=2;
                    total +=2;
                }else{
                    statistique[g][h]=1;
                    total ++;
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
               /* a= (int) (Math.random() * 10);
                b = (int) (Math.random() * 10);
                derTouche.setXY(a, b); */
                passage = false;
                trouver = false;
                i = (int) (Math.random() * total);
                for (a=0; a<10;a++){
                    for (b=0; b<10;b++){
                        i -= statistique[a][b];
                        if (i<0 && !trouver){
                            trouver = true;
                            if(valide(a,b)){
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
                if( !ctrl.autorisationTirOrdi(derTouche)|| !valide(derTouche.getX(),derTouche.getY())){
                    if(!suspect.get(0).isEpuiser()){
                        suspect.get(0).epuisement();
                        suspect.get(0).incremente();
                    }else {
                        suspect.remove(0);
                    }
                }
            }
        }while(!passage  && grilleAdverse[derTouche.getX()][derTouche.getY()]!=0 || !ctrl.autorisationTirOrdi(derTouche));
        ctrl.tir();
        grilleAdverse[derTouche.getX()][derTouche.getY()]=1;
        retour();
    }


    public void retour(){
        boolean touche = aTouche();
        boolean mort = unMort();
        if(suspect.size()!=0){
            if (touche){
                int d =0;
                if(suspect.get(0).getDirection().equals(reListeOren[0])) {
                    d = 1;
                }
                SuspectDouble tempo = new SuspectDouble(derTouche,reListeOren[d]);
                tempo.incremente();

                if(valide(tempo.getCoordonnee().getX(),tempo.getCoordonnee().getY())){
                    suspect.add(tempo);
                    suspect.get(0).addEnfant(tempo);
                }else{
                    tempo.epuisement();
                    tempo.incremente();
                    if (valide(tempo.getCoordonnee().getX(),tempo.getCoordonnee().getY())){
                        suspect.add(tempo);
                        suspect.get(0).addEnfant(tempo);
                    }
                }
            }

            suspect.get(0).incremente();

            if(mort){
                for(SuspectDouble s:suspect.get(0).getEnfants()){
                    suspect.remove(s);
                }
                suspect.remove(0);

            }else if(!touche || !valide(suspect.get(0).getCoordonnee().getX(),suspect.get(0).getCoordonnee().getY())){
                if(!suspect.get(0).isEpuiser()){
                    suspect.get(0).epuisement();
                    suspect.get(0).incremente();
                }else {
                    suspect.remove(0);
                }
            }

        }else if (touche){
                int t = (int)(Math.random()*2);
                for (int j=t;j<(t+2);j++){
                    int r = stable(j);
                    SuspectDouble tempo = new SuspectDouble(derTouche,reListeOren[r]);
                    tempo.incremente();
                    if(valide(tempo.getCoordonnee().getX(),tempo.getCoordonnee().getY())){
                        suspect.add(tempo);

                    }else{
                        tempo.epuisement();
                        tempo.incremente();
                        if (valide(tempo.getCoordonnee().getX(),tempo.getCoordonnee().getY())){
                            suspect.add(tempo);
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
            i=i-2;
        }
        if (i<0){
            i= i+2;
        }
        return i;
    }





}

