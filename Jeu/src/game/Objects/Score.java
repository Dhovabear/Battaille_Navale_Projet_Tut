package game.Objects;

import java.io.Serializable;

public class Score implements Serializable {
    private int flagId;
    private String name;
    private int score;

    public Score(){

    }

    public Score(int id , String name , int score){
        this.flagId = id;
        this.name = name;
        this.score = score;
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "flagId=" + flagId +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
