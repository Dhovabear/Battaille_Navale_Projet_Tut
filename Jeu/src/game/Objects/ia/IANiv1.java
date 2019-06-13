package game.Objects.ia;

import game.Objects.Coordonnees;
import game.scenes.Jouer;

import java.util.ArrayList;
import java.io.*;

public class IANiv1 {

    protected int[][] grilleAdverse;
    protected Coordonnees derTouche;
    protected Jouer ctrl;
    protected int mode;
    protected int k ;
    //protected int dernierUsage;

    public IANiv1(Jouer j,int m){
        this.ctrl = j;
        this.mode = m;
        grilleAdverse = new int[10][10];
        derTouche = new Coordonnees();
        if (mode == 2){
            for (int a = 0; a<10;a++){
                for (int b = 0; b<10;b++){
                   if(ctrl.getEtatCase(new Coordonnees(a,b))==3){
                       grilleAdverse[a][b]=1;
                   }
                }
            }
        }
    }



    public void jouer(){
        int i;
        int j;
        Coordonnees p;
        do {
            i = (int) (Math.random() * 10);
            j = (int) (Math.random() * 10);
            p = new Coordonnees(i,j);
        }while (grilleAdverse[i][j]!=0 || !ctrl.autorisationTirOrdi(p));
        ctrl.tir();
        grilleAdverse[i][j]=1;
        derTouche.setXY(i,j);
    }

   /* public void jouerSpe(){
        int i;
        int j;
        k = 4;
        Coordonnees p;
        i = (int) (Math.random() * 10);
        j = (int) (Math.random() * 10);
        p = new Coordonnees();
        do {
            if(k == 4){
                i = (int) (Math.random() * 10);
                j = (int) (Math.random() * 10);
                p = new Coordonnees(i,j);
                k = 0;
            }else{
                k++;
                if(k==3){
                    k++;
                }
            }

        }while (grilleAdverse[i][j]!=0 || !ctrl.autorisationTirOrdi(p)|| depassementGrille(i,j));
        ctrl.tir();
        grilleAdverse[i][j]=1;
        derTouche.setXY(i,j);
    }*/

    public void retour(){
        for(int i=0;i<5;i++){
            if(ctrl.getBateauxJoueur(i).touche(derTouche.getX(),derTouche.getY())){
                grilleAdverse[derTouche.getX()][derTouche.getY()]=2;
            }
        }
    }

    public void fin(){
        String fichier = "memoire.txt";
        String[] grilleTempo = readFile(fichier).split("!");

        for (int a = 0; a<10;a++){
            String[] ligneTempo = grilleTempo[a].split(":");
            for (int b = 0; b<10;b++){
                if (grilleAdverse[a][b]>0){
                    grilleAdverse[a][b]--;
                }
                grilleAdverse[a][b] += Integer.parseInt(ligneTempo[b]);
            }
        }

        String retour="";
        for (int a = 0; a<10;a++){
            for (int b = 0; b<10;b++){
                retour += grilleAdverse[a][b] +":";
            }
            retour += "!";
        }

        try {
            FileWriter fw = new FileWriter (fichier);
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter fichierSortie = new PrintWriter (bw);
            fichierSortie.println (retour);
            fichierSortie.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void placement(){
        int compteur = 0;
        do {
            int i = (int) (Math.random() * 10);
            int j = (int) (Math.random() * 10);
            int k = 0;
            int o = 0;
            if (mode != 2) {
                if (i < 5 && j < 5) {
                    o = (int) ((Math.random() * 2) + 1);
                } else if (i > 5 && j < 5) {
                    o = (int) ((Math.random() * 2));
                } else if (i < 5 && j > 5) {
                    o = (int) ((Math.random() * 2) + 2);
                } else {
                    o = (int) ((Math.random() * 2) + 3);
                    if (o == 4) {
                        o = 0;
                    }
                }
            }else{
                o= o+k;
                k++;
                if(k==4){
                    k=0;
                }
            }
            if (ctrl.getGrilleOrdi().addBateau(i,j,o,compteur)) {
                System.out.println(true);
                compteur++;
            }
        }while(compteur < 5);
    }

    public String readFile(String fichier) {
        String chaine = "";

        //lecture du fichier texte
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                chaine += ligne;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return chaine;
    }

    public boolean depassementGrille(int i, int j){
        return ((i>=0 && i<=9) && (j>=0 && j<=9));
    }

    public int stable(int i){
        if (i>3){
            return i-4;
        }
        if (i<0){
            return i+4;
        }
        return i;
    }


}
