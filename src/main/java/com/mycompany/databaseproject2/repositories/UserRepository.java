package com.mycompany.databaseproject2.repositories;

import com.mycompany.databaseproject2.domains.User;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KasperNissen1997
 */
public class UserRepository {
    private Session session;
    
    public static final String TABLE_NAME = "users";
    
    public UserRepository(Session session) {
        this.session = session;
    }
    
    /**
     * Creates the users table.
     */
    public void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME)
                .append("(")
                .append("name text PRIMARY KEY, ")
                .append("id UUID")
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    /**
     * Insert a row in the table users. 
     * 
     * @param user
     */
    public void insertUser(User user) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME)
                .append("(name, id) ")
                .append("VALUES ('")
                .append(user.getName())
                .append("', ")
                .append(UUIDs.timeBased())
                .append(");");

        final String query = sb.toString();
        session.execute(query);
    }
    
    /**
     * Searches for a user in users
     * 
     * @param name Name of the user to look for
     * @return 'true' if the mentioned user exists, 'false' if it does not
     */
    public boolean containsUser(String name) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append("name = '")
                .append(name)
                .append("';");

        final String query = sb.toString();
        ResultSet rs = session.execute(query);

        List<User> users = new ArrayList<User>();

        for (Row r : rs) {
            if (r.getString("name").equals(name))
                return true;
        }
        
        return false;
    }
}
