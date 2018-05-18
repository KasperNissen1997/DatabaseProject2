package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.Session;

/**
 *
 * @author Kaspe
 */
public class GinRepository {
    private Session session;
    
    public GinRepository(Session session) {
        this.session = session;
    }
    
    
    
}
