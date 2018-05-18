package com.mycompany.databaseproject2.repositories;

import com.mycompany.databaseproject2.domains.Garnish;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * Insert a row in the table garnish. 
     * 
     * @param garnish
     */
    public void insertGarnish(Garnish garnish) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(name) ")
                .append("VALUES (\'")
                .append(garnish.getName())
                .append("\');");

        final String query = sb.toString();
        session.execute(query);
    }
    
    /**
     * Select all garnish' from garnish
     * 
     * @return
     */
    public List<Garnish> selectAll() {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME);

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<Garnish> garnishes = new ArrayList<Garnish>();

        for (Row r : rs) {
            Garnish garnish = new Garnish(r.getString("name"));
            garnishes.add(garnish);
        }
        return garnishes;
    }
    
    /**
     * Delete table.
     * 
     * @param tableName the name of the table to delete.
     */
    public void deleteTable(String tableName) {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(tableName);

        final String query = sb.toString();
        session.execute(query);
    }
    
    public boolean containsTonic(String name) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append("name = '")
                .append(name)
                .append("';");

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<Garnish> tonics = new ArrayList<Garnish>();

        for (Row r : rs) {
            if (r.getString("name").equals(name))
                return true;
        }
        
        return false;
    }
}
