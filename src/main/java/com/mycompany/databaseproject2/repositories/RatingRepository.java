/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.databaseproject2.repositories;

import com.datastax.driver.core.Session;

/**
 *
 * @author Kaspe
 */
public class RatingRepository {
    public static final String RATING_BY_USER = "rtbusr";
    public static final String RATING_BY_COMB = "rtbusr";
    
    
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
    
    
}
