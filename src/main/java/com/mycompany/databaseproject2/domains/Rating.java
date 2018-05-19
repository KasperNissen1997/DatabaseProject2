package com.mycompany.databaseproject2.domains;

import java.util.UUID;

/**
 *
 * @author Sebastian
 */
public class Rating {
    
    String author;
    Combination comb;
    String comment;
    int rating;
    int marks;

    public Rating(String author, Combination comb, String comment, int rating, int marks) {
        this.author = author;
        this.comb = comb;
        this.comment = comment;
        this.rating = rating;
        this.marks = marks;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Combination getComb() {
        return comb;
    }

    public void setComb(Combination comb) {
        this.comb = comb;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    
}
