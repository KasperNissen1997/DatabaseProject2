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
    
    private static String KEYSPACE_DRINKS = "drinks";
    private static String KEYSPACE_USERS = "users";
    
    static KeyspaceRepository stdRep;
        
    static GinRepository ginRep;
    static TonicRepository tonicRep;
    static GarnishRepository garnishRep;
    
    static CombinationRepository combRep;
    static RatingRepository ratingRep;
    static UserRepository userRep;
    
    public static void main(String[] args) {
        GTRatingApplication.runApplication();
    }
    
    private static void runApplication() {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("127.0.0.1", null);
        Session session = connector.getSession();
        
        initializeRepositories(session);
        
        stdRep.createKeyspace(KEYSPACE_DRINKS, "SimpleStrategy", 1);
        stdRep.createKeyspace(KEYSPACE_USERS, "SimpleStrategy", 1);
        
        stdRep.useKeyspace(KEYSPACE_DRINKS);
        ginRep.createTable();
        tonicRep.createTable();
        garnishRep.createTable();
        stdRep.useKeyspace(KEYSPACE_USERS);
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
                
                // create a new gin, tonic, garnish or user
                case "I":
                    System.out.println("What would you like to insert?:\nGin (Gi), Tonic (T), Garnish (Ga) | User (U)");
                    
                    line = sc.nextLine();
                    
                    switch (line) {
                        case "Gi":
                            stdRep.useKeyspace(KEYSPACE_DRINKS);
                            insertGin();
                            break;
                            
                        case "T":
                            stdRep.useKeyspace(KEYSPACE_DRINKS);
                            insertTonic();
                            break;
                            
                        case "Ga":
                            stdRep.useKeyspace(KEYSPACE_DRINKS);
                            insertGarnish();
                            break;
                            
                        case "U":
                            stdRep.useKeyspace(KEYSPACE_USERS);
                            break;
                            
                        default:
                            System.out.println("Unknown input: \"" + line + "\".");
                            break;
                    }
                    break;
                    
                case "S":
                    
                    System.out.println("What is it that you are looking for?:\nGin (Gi), Tonic (T), Garnish (Ga) | Combination (C), Rating (R), User (U)");
                    line = sc.nextLine();
                    switch(line) {
                        case "Gi":
                            stdRep.useKeyspace(KEYSPACE_DRINKS);
                            searchGins();
                            break;
                        case "T":
                            stdRep.useKeyspace(KEYSPACE_DRINKS);
                            searchTonics();
                            break;
                        case "Ga":
                            stdRep.useKeyspace(KEYSPACE_DRINKS);
                            searchGarnish();
                            break;
                        case "C":
                            stdRep.useKeyspace(KEYSPACE_USERS);
                            break;
                            
                        case "R":
                            stdRep.useKeyspace(KEYSPACE_USERS);
                            break;
                            
                        case "U":
                            stdRep.useKeyspace(KEYSPACE_USERS);
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
        ginRep = new GinRepository(session);
        tonicRep = new TonicRepository(session);
        garnishRep = new GarnishRepository(session);
        /*
        combRep = new CombinationRepository(session);
        ratingRep = new RatingRepository(session);
        userRep = new UserRepository(session);
        */
    }
    
    private static void insertGin() {
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
    
    private static void insertTonic() {
        System.out.println("What is the name of the tonic?");
        String tonicName = sc.nextLine();
        System.out.println("What is the price of the tonic?");
        String tonicPrice = sc.nextLine();
        
        System.out.println("Inserting new tonic...");
        tonicRep.insertTonic(new Tonic(tonicName, Float.valueOf(tonicPrice)));
        System.out.println("New tonic succesfully inserted!");
    }
    
    private static void insertGarnish() {
        System.out.println("What is the name of the garnish?");
        String garnishName = sc.nextLine();
        
        System.out.println("Inserting new garnish...");
        garnishRep.insertGarnish(new Garnish(garnishName));
        System.out.println("New garnish succesfully inserted!");
    }
    
    private static void searchGins() {
        System.out.println("What is the name of the gin?");
        String ginName = sc.nextLine();
        if(ginRep.containsGin(ginName)) { System.out.println("The gin was found in the database!"); }
        else { System.out.println("The gin was NOT found in the database!"); }
    }
    
    private static void searchTonics() {
        System.out.println("What is the name of the tonic?");
        String tonicName = sc.nextLine();
        if(tonicRep.containsTonic(tonicName)) { System.out.println("The tonic was found in the database!"); }
        else { System.out.println("The tonic was NOT found in the database!"); }
    }
    
    private static void searchGarnish() {
        System.out.println("What is the name of the garnish?");
        String garnishName = sc.nextLine();
        if(garnishRep.containsGarnish(garnishName)) { System.out.println("The garnish was found in the database!"); }
        else { System.out.println("The garnish was NOT found in the database!"); }
    }
}
