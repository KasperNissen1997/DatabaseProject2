package com.mycompany.databaseproject2.domains;

public class Gin {
    
    private String name;
    private float price;
    private float percentage;
    
    public Gin(String name, float price, float percentage) {
        this.name = name;
        this.price = price;
        this.percentage = percentage;
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
    
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
    
    public float getPercentage() {
        return this.percentage;
    }
}
