package com.mycompany.databaseproject2;

import com.mycompany.databaseproject2.domains.*;
import com.mycompany.databaseproject2.repositories.*;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import java.util.Scanner;

/**
 *
 * @author Kaspe
 */
public class GTRatingApplication {
    private static final Scanner sc = new Scanner(System.in);
    
    private static final String KEYSPACE_DRINKS = "drinks";
    private static final String KEYSPACE_USERS = "users";
    
    static KeyspaceRepository stdRep;
        
    static GinRepository ginRep;
    static TonicRepository tonicRep;
    static GarnishRepository garnishRep;
    
    static CombinationRepository combRep;
    static RatingRepository ratingRep;
    static UserRepository userRep;
    
    public static String activeUser;
    
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
        combRep.createTable();
        userRep.createTable();
        //ratingRep.createTable();
       
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
                            insertUser();
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
                            searchUsers();
                            break;
                            
                        default:
                            System.out.println("Unknown input: \"" + line + "\".");
                            break;
                    }
                    break;
                case "L":
                    stdRep.useKeyspace(KEYSPACE_USERS);
                    if (!logIn()) {
                        break;
                    }
                    
                    System.out.println("What would you like to do?:\nInsert (I), Rate (R), View previously rated combinations (V) | Log out (L)");
                    line = sc.nextLine();
                    
                    while (!line.equals("L")) {
                        switch (line) {
                            case "I":
                                System.out.println("What would you like to insert?\nCombination (C), Rating (R)");
                                line = sc.nextLine();

                                switch (line) {
                                    case "C":
                                        insertComb();
                                        break;

                                    case "R":
                                        
                                        break;

                                    default:
                                        System.out.println("Unknown input: \"" + line + "\".");
                                        break;
                                }
                                break;

                            case "R":

                                break;

                            case "V":

                                break;

                            default:
                                System.out.println("Unknown input: \"" + line + "\".");
                                break;
                        }
                        
                        System.out.println("What would you like to do?:\nInsert (I), Rate (R), View previously rated combinations (V) | Log out (L)");
                        line = sc.nextLine();
                    }
                    
                    System.out.println("You have succesfully logged out.");
                    
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
            
            System.out.println("What would you like to do?:\nInsert (I), Search (S), Log in (L), Exit (exit)");
            line = sc.nextLine();
        }
        
        connector.close();
    }
    
    private static void initializeRepositories(Session session) {
        stdRep = new KeyspaceRepository(session);
        
        // create the various domain repositories
        ginRep = new GinRepository(session);
        tonicRep = new TonicRepository(session);
        garnishRep = new GarnishRepository(session);
        combRep = new CombinationRepository(session);
        userRep = new UserRepository(session);
        //ratingRep = new RatingRepository(session);
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
    
    private static void insertComb() {
        Combination comb = null;
        
        stdRep.useKeyspace("drinks");
        
        System.out.println("What is the name of the gin in the combination?");
        String ginName = sc.nextLine();
        if (!ginRep.containsGin(ginName)) { 
            System.out.println("That gin does not exist in the database.\nPlease insert it before using it to create a combination.");
            return;
        }
        
        System.out.println("What is the name of the tonic in the combination?");
        String tonicName = sc.nextLine();
        if (!tonicRep.containsTonic(tonicName)) { 
            System.out.println("That tonic does not exist in the database.\nPlease insert it before using it to create a combination.");
            return;
        }
        
        System.out.println("Does the combination contain a garnish? (Y/N)");
        String containsGarnish = sc.nextLine();
        if (containsGarnish.equals("Y")) {
            System.out.println("What is the name of the garnish in the combination?");
            String garnishName = sc.nextLine();
            if (!garnishRep.containsGarnish(garnishName)) { 
                System.out.println("That garnish does not exist in the database.\nPlease insert it before using it to create a combination.");
                return;
            }
            comb = new Combination(ginName, tonicName, garnishName);
        }
        else if (containsGarnish.equals("N")) {
            comb = new Combination(ginName, tonicName);
        }
        else {
            System.out.println("Unknown input: \"" + containsGarnish + "\".");
        }
        
        stdRep.useKeyspace("users");
        
        System.out.println("Inserting new combination...");
        combRep.insertCombination(comb);
        System.out.println("New combination succesfully inserted!");
    }
    
    private static void insertUser() {
        System.out.println("What will the new user be named?");
        String username = sc.nextLine();
        
        System.out.println("Inserting new user...");
        userRep.insertUser(new User(username, UUIDs.timeBased()));
        System.out.println("New user succesfully inserted!");
    }
    
    private static void searchGins() {
        System.out.println("What is the name of the gin?");
        String ginName = sc.nextLine();
        if (ginRep.containsGin(ginName)) { System.out.println("The gin was found in the database!"); }
        else { System.out.println("The gin was NOT found in the database!"); }
    }
    
    private static void searchTonics() {
        System.out.println("What is the name of the tonic?");
        String tonicName = sc.nextLine();
        if (tonicRep.containsTonic(tonicName)) { System.out.println("The tonic was found in the database!"); }
        else { System.out.println("The tonic was NOT found in the database!"); }
    }
    
    private static void searchGarnish() {
        System.out.println("What is the name of the garnish?");
        String garnishName = sc.nextLine();
        if (garnishRep.containsGarnish(garnishName)) { System.out.println("The garnish was found in the database!"); }
        else { System.out.println("The garnish was NOT found in the database!"); }
    }
    
    private static void searchUsers() {
        System.out.println("What is the name of the user?");
        String username = sc.nextLine();
        if (userRep.containsUser(username)) { System.out.println("The user " + username + " was found in the database!"); }
        else { System.out.println("The user " + username + " was NOT found in the database!"); }
    }
    
    private static boolean logIn() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        
        if (userRep.containsUser(username)) { 
            System.out.println("Succesfully signed in as " + username + "!");
            activeUser = username; 
            return true;
        }
        
        System.out.println("Cannot sign in as " + username + "...");
        System.out.println("User " + username + " does not exist...");
        return false;
    }
}
