package com.mycompany.databaseproject2.repositories;

import com.mycompany.databaseproject2.domains.Garnish;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author KasperNissen1997
 */
public class GarnishRepository {
    private Session session;
    
    public static final String TABLE_NAME = "garnish";
    
    public GarnishRepository(Session session) {
        this.session = session;
    }
    
    /**
     * Creates the garnish table.
     */
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME)
                .append("(")
                .append("name text PRIMARY KEY")
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    
}
