package com.mycompany.databaseproject2.repositories;

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
                .append("nrOfRatings int")
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
    
    public String updateRating(Combination comb){
        StringBuilder q1 = new StringBuilder("UPDATE ").append(TABLE_NAME)
                .append(" SET nrOfRatings = nrOfRatings+1 WHERE parts = ")
                .append(comb.getTuple()+";");
        StringBuilder q2 = new StringBuilder("UPDATE ").append(RatingRepository.RATING_BY_COMB).append(", ").append(TABLE_NAME)
                .append(" SET ").append(TABLE_NAME).append(".averageScore = avg(").append(RatingRepository.RATING_BY_COMB).append(".rating) WHERE ").append(TABLE_NAME).append(".parts = ").append(comb.getTuple()).append(" AND ").append(RatingRepository.RATING_BY_COMB).append(".comb = ").append(comb.getTuple()).append(";");
        q1.append(q2);
        return q1.toString();
                
    }
    
    
}
