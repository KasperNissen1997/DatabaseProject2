package com.mycompany.databaseproject2.domains;

/**
 * 
 * @author KasperNissen1997
 */
public class Combination {
    
    Gin gin;
    Tonic tonic;
    Garnish garnish;
    int score;
    float averageScore;
    int nrOfRatings;
    
    public Combination(Gin gin, Tonic tonic) {
        this.gin = gin;
        this.tonic = tonic;
        this.garnish = null;
        this.score = 0;
        this.averageScore = 0f;
        this.nrOfRatings = 0;
    }
    
    public Combination(Gin gin, Tonic tonic, Garnish garnish) {
        this.gin = gin;
        this.tonic = tonic;
        this.garnish = garnish;
        this.score = 0;
        this.averageScore = 0f;
        this.nrOfRatings = 0;
    }
    
    public void setGin(Gin gin) {
        this.gin = gin;
    }
    
    public Gin getGin() {
        return this.gin;
    }
    
    public void setTonic(Tonic tonic) {
        this.tonic = tonic;
    }
    
    public Tonic getTonic() {
        return this.tonic;
    }
    
    public void setGarnish(Garnish garnish) {
        this.garnish = garnish;
    }
    
    public Garnish getGarnish() {
        return this.garnish;
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
