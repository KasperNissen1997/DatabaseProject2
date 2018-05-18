package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.Session;
import com.mycompany.databaseproject2.domains.Gin;

/**
 *
 * @author Kaspe
 */
public class GinRepository {
    private static final String TABLE_NAME = "Gins";
    
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
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(name, price, percentage) ").append("VALUES (").append(gin.name).append(", '").append(gin.price).append("', '").append(gin.percentage).append("');");

        final String query = sb.toString();
        session.execute(query);
    }
    
    public void selectgin(Gin gin){
        
    }
}
