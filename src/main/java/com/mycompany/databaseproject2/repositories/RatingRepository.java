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
    
    public void RatingRepository(Session session){
        this.session=session;
    }
    
    public void createTable() {
        StringBuilder f = new StringBuilder("BEGIN BATCH ");
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXIST ").append(RATING_BY_USER).append("(").append("user UUID, ").append("rating int, ").append("eval text, ").append("comb tuple, ").append("marks int, ").append("PRIMARY KEY(user, comb)").append(");");
        f.append(sb.toString());
        
        sb = new StringBuilder("CREATE TABLE IF NOT EXIST ").append(RATING_BY_COMB).append("(").append("user UUID, ").append("rating int, ").append("eval text, ").append("comb tuple, ").append("marks int, ").append("PRIMARY KEY(comb, user)").append(");");
        f.append(sb.toString());
        f.append("END BATCH;");
        final String query = f.toString();
        session.execute(query);
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
        
        StringBuilder q1 = new StringBuilder("INSERT INTO ").append(RATING_BY_USER).append("(user, rating, eval,comb, marks) VALUES(")
                .append(rating.getAuthor())
                .append(", "+rating.getRating())
                .append(", "+rating.getComment())
                .append(", "+rating.getComb())
                .append(", "+rating.getMarks())
                .append(");");
        
        StringBuilder q2 = new StringBuilder("INSERT INTO ").append(RATING_BY_COMB).append("(user, rating, eval,comb, marks) VALUES(")
                .append(rating.getAuthor())
                .append(", "+rating.getRating())
                .append(", "+rating.getComment())
                .append(", "+rating.getComb())
                .append(", "+rating.getMarks())
                .append(");");
        
        q1.append(q2);
        return q1.toString();
    }
    
}
