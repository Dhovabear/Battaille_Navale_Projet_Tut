package game.Objects;

import game.engine.Scene;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;

public class Scoreboard implements Serializable {
    private ArrayList<Score> easyScore;
    private ArrayList<Score> moyenScore;
    private ArrayList<Score> difficileScore;

    public Scoreboard(){

    }

    public Scoreboard(int x){
        easyScore = new ArrayList<Score>();
        moyenScore = new ArrayList<Score>();
        difficileScore = new ArrayList<Score>();

        easyScore.add(new Score(14,"Portugal",4));
        easyScore.add(new Score(0,"Inde",3));
        easyScore.add(new Score(15,"Espagne",2));
        easyScore.add(new Score(9,"Chine",1));
        easyScore.add(new Score(12,"Japon",0));

        moyenScore.add(new Score(2,"Allemagne",4));
        moyenScore.add(new Score(13,"Bresil",3));
        moyenScore.add(new Score(3,"Italie",2));
        moyenScore.add(new Score(7,"Etats-Unis",1));
        moyenScore.add(new Score(5,"France",0));

        difficileScore.add(new Score(16,"Xanix",4));
        difficileScore.add(new Score(19,"Nijtus",3));
        difficileScore.add(new Score(18,"RaptorRouge",2));
        difficileScore.add(new Score(17,"r2r0",1));
        difficileScore.add(new Score(11,"Dovabear",0));

        easyScore.sort(new ScoreComp());
        moyenScore.sort(new ScoreComp());
        difficileScore.sort(new ScoreComp());

        System.out.println("rrrr");
    }

    public static Scoreboard loadScores(){
        XMLDecoder r = null;
        try {
            r = new XMLDecoder(new FileInputStream(new File("scores.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (Scoreboard) r.readObject();
    }

    public void saveScores(){
        try {
            File tst = new File("scores.xml");
            System.out.println(tst.getAbsolutePath());
            XMLEncoder e = new XMLEncoder(new FileOutputStream(tst));
            e.writeObject(this);
            e.flush();
            e.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void printScores(){
        for (Score s: easyScore) {
            System.out.println(s);
        }
        System.out.println("\n\n");
        for (Score s: moyenScore) {
            System.out.println(s);
        }
        System.out.println("\n\n");
        for (Score s: difficileScore) {
            System.out.println(s);
        }
    }

    public int addScore(Score s , int diff){
        ArrayList<Score> listToUse;
        switch (diff){
            case 0:
                easyScore.add(s);
                easyScore.sort(new ScoreComp());
                easyScore = keepTop5(easyScore);
                saveScores();
                return easyScore.indexOf(s);
            case 1:
                moyenScore.add(s);
                moyenScore.sort(new ScoreComp());
                moyenScore = keepTop5(moyenScore);
                saveScores();
                return moyenScore.indexOf(s);
            case 2:
                difficileScore.add(s);
                difficileScore.sort(new ScoreComp());
                difficileScore = keepTop5(difficileScore);
                saveScores();
                return difficileScore.indexOf(s);
            default:
                throw new IllegalStateException("Unexpected value: " + diff);
        }
    }

    public ArrayList<Score> keepTop5(ArrayList<Score> ls) {
        ArrayList<Score> tmp = (ArrayList<Score>) ls.clone();
        ArrayList<Score> tmp2 = new ArrayList<Score>();
        for (int i = 0; i < 5; i++) {
            tmp2.add(tmp.get(i));
        }
        return tmp2;
    }

    public void reorganise(){
        retournerListe(easyScore);
        retournerListe(moyenScore);
        retournerListe(difficileScore);
    }

    private void retournerListe(ArrayList<Score> ls){
        ArrayList<Score> tmp = new ArrayList<Score>();
        for (int i = 0; i < ls.size(); i++) {
            tmp.add(ls.get(ls.size()-i));
        }
        ls = tmp;
    }

    public ArrayList<Score> getEasyScore() {
        return easyScore;
    }

    public void setEasyScore(ArrayList<Score> easyScore) {
        this.easyScore = easyScore;
    }

    public ArrayList<Score> getMoyenScore() {
        return moyenScore;
    }

    public void setMoyenScore(ArrayList<Score> moyenScore) {
        this.moyenScore = moyenScore;
    }

    public ArrayList<Score> getDifficileScore() {
        return difficileScore;
    }

    public void setDifficileScore(ArrayList<Score> difficileScore) {
        this.difficileScore = difficileScore;
    }
}

class ScoreComp implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1 instanceof Score) || !(o2 instanceof Score)){
            return -666;
        }
        Score s1,s2;
        s1 = (Score) o1;
        s2 = (Score) o2;

        if(s1.getScore() == s2.getScore()){
            return 0;
        }else if(s1.getScore() > s2.getScore()){
            return -1;
        }else{
            return 1;
        }
    }
}
