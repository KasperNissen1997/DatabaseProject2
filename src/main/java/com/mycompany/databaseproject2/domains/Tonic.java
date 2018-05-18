package com.mycompany.databaseproject2.domains;

public class Tonic {
    
    private String name;
    private float price;
    
    public Tonic(String name, float price) {
        this.name = name;
        this.price = price;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public float getPrice() {
        return this.price;
    }
}
