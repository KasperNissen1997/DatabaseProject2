package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.Session;

/**
 *
 * @author Kaspe
 */
public class TonicRepository {
    private Session session;
    
    public TonicRepository(Session session) {
        this.session = session;
    }
}
