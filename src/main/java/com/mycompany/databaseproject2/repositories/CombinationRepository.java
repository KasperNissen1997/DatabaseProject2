package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.mycompany.databaseproject2.domains.Combination;

import com.datastax.driver.core.Session;

/**
 * 
 * @author KasperNissen1997
 */
public class CombinationRepository {
    private Session session;
    
    public static final String TABLE_NAME = "combinations";
    
    public CombinationRepository(Session session) {
        this.session = session;
    }
    
    /**
     * Creates the combinations table.
     */
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME)
                .append("(")
                .append("parts tuple<text, text, text> PRIMARY KEY, ")
                .append("score int, ")
                .append("averageScore float, ")
                .append("nrOfRatings counter")
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    /**
     * Insert a row in the table combinations. 
     * 
     * @param combination
     */
    public void insertCombination(Combination comb) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(parts, score, averageScore, nrOfRatings) ")
                .append("VALUES (('")
                .append(comb.getGinName())
                .append("', '")
                .append(comb.getTonicName())
                .append("', '")
                .append(comb.getGarnishName())
                .append("'), ")
                .append(comb.getScore())
                .append(", ")
                .append(comb.getAverageScore())
                .append(", ")
                .append(comb.getNrOfRatings())
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    public void updateRating(Combination comb){
        StringBuilder q1 = new StringBuilder("UPDATE ").append(TABLE_NAME)
                .append(" SET nrOfRatings = nrOfRatings+1 WHERE parts = ")
                .append(comb.getTuple()+";");
        
        session.execute(q1.toString());
        
        StringBuilder q = new StringBuilder("SELECT avg(rating) FROM ").append(RatingRepository.RATING_BY_COMB).append(" WHERE comb = ").append(comb.getTuple()).append(";");
        
        ResultSet rs =session.execute(q.toString());
        float avg = 0;
        for(Row r : rs){
            avg = r.getFloat("rating");
        }
        
        q1 = new StringBuilder("UPDATE ").append(TABLE_NAME).append(" SET averageScore = ").append(Float.toString(avg)).append(" WHERE parts = ").append(comb.getTuple()).append(";");
        
        session.execute(q1.toString());
                
    }
    
    
}
