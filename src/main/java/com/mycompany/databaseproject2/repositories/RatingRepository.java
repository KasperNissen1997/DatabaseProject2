package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.mycompany.databaseproject2.domains.Combination;
import com.mycompany.databaseproject2.domains.Rating;
import com.mycompany.databaseproject2.domains.User;

/**
 *
 * @author Sebastian
 */
public class RatingRepository {
    public static final String RATING_BY_USER = "rtbusr";
    public static final String RATING_BY_COMB = "rtbcom";
    
    
    Session session;
    
    public RatingRepository(Session session){
        this.session = session;
    }
    
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(RATING_BY_USER).append("(").append("user text, ").append("rating int, ").append("eval text, ").append("comb tuple<text, text, text>, ").append("marks int, ").append("PRIMARY KEY(user, comb)").append(");");
        session.execute(sb.toString());
        
        sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(RATING_BY_COMB).append("(").append("user text, ").append("rating int, ").append("eval text, ").append("comb tuple<text, text, text>, ").append("marks int, ").append("PRIMARY KEY(comb, user)").append(");");
        session.execute(sb.toString());
    }
    
    public ResultSet selectByComb(Combination comb){
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(RATING_BY_COMB).append(" WHERE comb = ('").append(comb.getGinName()).append("', '").append(comb.getTonicName()).append("', '").append(comb.getGarnishName()).append("');");
        final String query = sb.toString();
        ResultSet rs = session.execute(query);
        return rs;
    }
    
    public ResultSet selectByUser(User user){
        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(RATING_BY_USER).append(" WHERE user = ").append(user.getId()).append(";");
        final String query = sb.toString();
        ResultSet rs = session.execute(query);
        return rs;
    }
    
    public String createRating(Rating rating){
        StringBuilder q = new StringBuilder("BEGIN BATCH ");
        
        StringBuilder q1 = new StringBuilder("INSERT INTO ").append(RATING_BY_USER).append("(user, rating, eval,comb, marks) VALUES('")
                .append(rating.getAuthor())
                .append("', "+rating.getRating())
                .append(", '"+rating.getComment())
                .append("', "+rating.getComb().getTuple())
                .append(", "+rating.getMarks())
                .append(");");
        
        StringBuilder q2 = new StringBuilder("INSERT INTO ").append(RATING_BY_COMB).append("(user, rating, eval,comb, marks) VALUES('")
                .append(rating.getAuthor())
                .append("', "+rating.getRating())
                .append(", '"+rating.getComment())
                .append("', "+rating.getComb().getTuple())
                .append(", "+rating.getMarks())
                .append(");");
        
        q1.append(q2);
        return q1.toString();
    }
    
}
