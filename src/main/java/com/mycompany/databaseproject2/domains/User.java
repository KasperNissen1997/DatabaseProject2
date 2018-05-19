/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.databaseproject2.domains;

import java.util.UUID;

/**
 *
 * @author KasperNissen1997
 */
public class User {
    
    private String name;
    private UUID id;
    
    public User (String name, UUID id) {
        this.name = name;
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return this.id;
    }
}
