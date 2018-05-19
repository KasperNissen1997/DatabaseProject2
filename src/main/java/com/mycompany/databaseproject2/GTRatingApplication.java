package com.mycompany.databaseproject2;

import com.mycompany.databaseproject2.domains.*;
import com.mycompany.databaseproject2.repositories.*;
import com.datastax.driver.core.Session;
import java.util.Scanner;

/**
 *
 * @author Kaspe
 */
public class GTRatingApplication {
    private static Scanner sc = new Scanner(System.in);
    
    static KeyspaceRepository stdRep;
        
    static GinRepository ginRep;
    static TonicRepository tonicRep;
    static GarnishRepository garnishRep;
    
    static CombinationRepository combRep;
    static UserRepository userRep;
    static RatingRepository ratingRep;
    
    public static void main(String[] args) {
        GTRatingApplication.runApplication();
    }
    
    private static void runApplication() {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("127.0.0.1", null);
        Session session = connector.getSession();
        
        initializeRepositories(session);
        
        // create the "drinks" keyspace
        stdRep.createKeyspace("drinks", "SimpleStrategy", 1);
        stdRep.createKeyspace("users", "SimpleStrategy", 1);
        
        stdRep.useKeyspace("drinks");
        ginRep.createTable();
        tonicRep.createTable();
        garnishRep.createTable();
        stdRep.useKeyspace("users");
        /*
        combRep.createTable();
        userRep.createTable();
        ratingRep.createTable();
        */
        
        System.out.println("What would you like to do?:\nInsert (I), Search (S), Log in (L), Exit (exit)");
        
        String line = sc.nextLine();
        
        // branch out, depending on the input given
        while (!line.equals("exit")) {
            switch (line) {
                
                // create a new gin, tonic or garnish
                case "I":
                    System.out.println("What would you like to insert?:\nGin (Gi), Tonic (T), Garnish (Ga), Combination (C), Rating (R)");
                    
                    line = sc.nextLine();
                    
                    switch (line) {
                        case "Gi":
                            stdRep.useKeyspace("drinks");
                            insertGin(ginRep);
                            break;
                            
                        case "T":
                            stdRep.useKeyspace("drinks");
                            insertTonic(tonicRep);
                            break;
                            
                        case "Ga":
                            stdRep.useKeyspace("drinks");
                            insertGarnish(garnishRep);
                            break;
                        
                        case "C":
                            break;
                        
                        case "R":
                            break;
                            
                        default:
                            System.out.println("Unknown input: \"" + line + "\".");
                            break;
                    }
                    break;
                    
                case "S":
                    stdRep.useKeyspace("drinks");
                    
                    System.out.println("Is it a gin, tonic or a garnish you're looking for?");
                    line = sc.nextLine();
                    switch(line) {
                        case "gin":
                            System.out.println("What is the name of the gin?");
                            line = sc.nextLine();
                            if(ginRep.containsGin(line)) { System.out.println("The gin was found in the database!"); }
                            else { System.out.println("The gin was NOT found in the database!"); }
                            break;
                        case "tonic":
                            System.out.println("What is the name of the tonic?");
                            line = sc.nextLine();
                            if(tonicRep.containsTonic(line)) { System.out.println("The tonic was found in the database!"); }
                            else { System.out.println("The tonic was NOT found in the database!"); }
                            break;
                        case "garnish":
                            System.out.println("What is the name of the garnish?");
                            line = sc.nextLine();
                            if(garnishRep.containsGarnish(line)) { System.out.println("The garnish was found in the database!"); }
                            else { System.out.println("The garnish was NOT found in the database!"); }
                            break;
                        default:
                            System.out.println("Unknown input: \"" + line + "\".");
                            break;
                    }
                    break;
                case "L":
                    break;
                case "ayyy, cook some meth dawg":
                    //meth.cookHomie(bestShit);
                    break;
                case "flip a whopper mah boiii":
                    //burgerKing.flip("Whopper");
                    break;
                default:
                    System.out.println("Unknown input: \"" + line + "\".");
            }
            
            System.out.println("Next command, eyyy?");
            line = sc.nextLine();
        }
        
        connector.close();
    }
    
    private static void initializeRepositories(Session session) {
        KeyspaceRepository stdRep = new KeyspaceRepository(session);
        
        // create the various domain repositories
        GinRepository ginRep = new GinRepository(session);
        TonicRepository tonicRep = new TonicRepository(session);
        GarnishRepository garnishRep = new GarnishRepository(session);
        // TODO: still need to add the remaining repositories
    }
    
    private static void insertGin(GinRepository ginRep) {
        System.out.println("What is the name of the gin?");
        String ginName = sc.nextLine();
        System.out.println("What is the price of the gin?");
        String ginPrice = sc.nextLine();
        System.out.println("What is the percentage of the gin?");
        String ginPercentage = sc.nextLine();
        
        System.out.println("Inserting new gin...");
        ginRep.insertGin(new Gin(ginName, Float.valueOf(ginPrice), Float.valueOf(ginPercentage)));
        System.out.println("New gin succesfully inserted!");
    }
    
    private static void insertTonic(TonicRepository tonicRep) {
        System.out.println("What is the name of the tonic?");
        String tonicName = sc.nextLine();
        System.out.println("What is the price of the tonic?");
        String tonicPrice = sc.nextLine();
        
        System.out.println("Inserting new tonic...");
        tonicRep.insertTonic(new Tonic(tonicName, Float.valueOf(tonicPrice)));
        System.out.println("New tonic succesfully inserted!");
    }
    
    private static void insertGarnish(GarnishRepository garnishRep) {
        System.out.println("What is the name of the garnish?");
        String garnishName = sc.nextLine();
        
        System.out.println("Inserting new garnish...");
        garnishRep.insertGarnish(new Garnish(garnishName));
        System.out.println("New garnish succesfully inserted!");
    }
}
