package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.Session;

public class TonicRepository {
    private Session session;
    
    public static final String TABLE_NAME = "tonics";
    
    public TonicRepository(Session session) {
        this.session = session;
    }
    
    /**
     * Creates the tonics table.
     */
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME)
                .append("(")
                .append("name text PRIMARY KEY);");

        final String query = sb.toString();
        session.execute(query);
    }
    
    
}
