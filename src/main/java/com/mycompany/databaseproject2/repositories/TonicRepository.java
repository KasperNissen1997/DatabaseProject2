package com.mycompany.databaseproject2.repositories;

import com.mycompany.databaseproject2.domains.Tonic;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author KasperNissen1997
 */

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
                .append("name text PRIMARY KEY, ")
                .append("price float")
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    /**
     * Insert a row in the table tonics. 
     * 
     * @param tonic
     */
    public void insertTonic(Tonic tonic) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(name, price) ")
                .append("VALUES ('")
                .append(tonic.getName())
                .append("', ")
                .append(tonic.getPrice())
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    /**
     * Select all tonics from tonics
     * 
     * @return
     */
    public List<Tonic> selectAll() {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME);

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<Tonic> tonics = new ArrayList<Tonic>();

        for (Row r : rs) {
            Tonic tonic = new Tonic(r.getString("name"), r.getFloat("price"));
            tonics.add(tonic);
        }
        return tonics;
    }
    
    /**
     * Searches for a tonic in tonics
     * 
     * @param name Name of the tonic to look for
     * @return 'true' if the mentioned tonic exists, 'false' if it does not
     */
    public boolean containsTonic(String name) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append("name = '")
                .append(name)
                .append("';");

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<Tonic> tonics = new ArrayList<Tonic>();

        for (Row r : rs) {
            if (r.getString("name").equals(name))
                return true;
        }
        
        return false;
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
}
