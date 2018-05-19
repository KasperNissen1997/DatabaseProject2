package com.mycompany.databaseproject2.domains;

/**
 * 
 * @author KasperNissen1997
 */
public class Combination {
    
    String ginName;
    String tonicName;
    String garnishName;
    int score;
    float averageScore;
    int nrOfRatings;
    
    public Combination(String ginName, String tonicName) {
        this.ginName = ginName;
        this.tonicName = tonicName;
        this.garnishName = null;
        this.score = 0;
        this.averageScore = 0f;
        this.nrOfRatings = 0;
    }
    
    public Combination(String ginName, String tonicName, String garnishName) {
        this.ginName = ginName;
        this.tonicName = tonicName;
        this.garnishName = garnishName;
        this.score = 0;
        this.averageScore = 0f;
        this.nrOfRatings = 0;
    }
    
    public String getTuple(){
        StringBuilder sb = new StringBuilder("('")
                .append(this.ginName).append("', '")
                .append(this.tonicName).append("', '")
                .append(this.garnishName).append("')");
        return sb.toString();
    }
    
    public void setGinName(String ginName) {
        this.ginName = ginName;
    }
    
    public String getGinName() {
        return this.ginName;
    }
    
    public void setTonicName(String tonicName) {
        this.tonicName = tonicName;
    }
    
    public String getTonicName() {
        return this.tonicName;
    }
    
    public void setGarnishName(String garnishName) {
        this.garnishName = garnishName;
    }
    
    public String getGarnishName() {
        return this.garnishName;
    }
    
    public void setScore(int score) {
        this.score = score;
    } 
    
    public int getScore() {
        return this.score;
    }
    
    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
    
    public float getAverageScore() {
        return this.averageScore;
    }
    
    public void setNrOfRatings(int nrOfRatings) {
        this.nrOfRatings = nrOfRatings;
    }
    
    public int getNrOfRatings() {
        return this.nrOfRatings;
    }
}
