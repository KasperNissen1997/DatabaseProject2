package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.mycompany.databaseproject2.domains.Gin;
import com.mycompany.databaseproject2.domains.Tonic;
import static com.mycompany.databaseproject2.repositories.TonicRepository.TABLE_NAME;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Sebastian
 */
public class GinRepository {
    private static final String TABLE_NAME = "gins";
    
    private Session session;
    
    public GinRepository(Session session) {
        this.session = session;
    }
    
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(").append("name text PRIMARY KEY, ").append("price float,").append("percentage float);");

        final String query = sb.toString();
        session.execute(query);
    }
    
    public void insertGin(Gin gin){
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(name, price, percentage) ").append("VALUES ('").append(gin.getName()).append("', ").append(gin.getPrice()).append(", ").append(gin.getPercentage()).append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    public void selectgin(Gin gin){
        
    }

    public boolean containsGin(String name) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append("name = '")
                .append(name)
                .append("';");

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<Gin> tonics = new ArrayList<Gin>();

        for (Row r : rs) {
            if (r.getString("name").equals(name))
                return true;
        }
        
        return false;
    }
}
