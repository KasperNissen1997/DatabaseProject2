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
    
    public static void main(String[] args) {
        GTRatingApplication.runApplication();
    }
    
    static void runApplication() {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("127.0.0.1", null);
        Session session = connector.getSession();
        
        // create the "drinks" keyspace
        KeyspaceRepository stdRep = new KeyspaceRepository(session);
        
        // create the various domain repositories
        GinRepository ginRep = new GinRepository(session);
        TonicRepository tonicRep = new TonicRepository(session);
        // TODO: still need to add the remaining repositories
        
        stdRep.createKeyspace("drinks", "SimpleStrategy", 1);
        
        stdRep.useKeyspace("drinks");
        
        ginRep.createTable();
        tonicRep.createTable();
        
        // await input
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Lul boi, what next ayyy??");
        
        String line = sc.nextLine();
        
        // branch out, depending on the input given
        while (line != "quit" || line != "stop" || line != "exit") {
            switch (line) {
                
                // create a new gin, tonic or garnish
                case "insert":
                    stdRep.useKeyspace("drinks");
                    
                    System.out.println("Ayyy mah boi, u tryna insert new stuff?\nIs it a gin, tonic or a maddafakkin garnish eh?");
                    line = sc.nextLine();
                    switch (line) {
                        
                        // insert a new gin
                        case "gin":
                            System.out.println("What is the name of the gin?");
                            String ginName = sc.nextLine();
                            System.out.println("What is the price of the gin?");
                            String ginPrice = sc.nextLine();
                            System.out.println("What is the percentage of the gin?");
                            String ginPercentage = sc.nextLine();
                            System.out.println("Inserting new gin...");
                            ginRep.insertGin(new Gin(ginName, Float.valueOf(ginPrice), Float.valueOf(ginPercentage)));
                            System.out.println("New gin succesfully inserted!");
                            break;
                            
                        // insert a new tonic 
                        case "tonic":
                            System.out.println("What is the name of the tonic?");
                            String tonicName = sc.nextLine();
                            System.out.println("What is the price of the tonic?");
                            String tonicPrice = sc.nextLine();
                            System.out.println("Inserting new tonic...");
                            tonicRep.insertTonic(new Tonic(tonicName, Float.valueOf(tonicPrice)));
                            System.out.println("New tonic succesfully inserted!");
                            break;
                            
                        // insert a new garnish
                        case "garnish":
                            System.out.println("What is the name of the garnish?");
                            String garnishName = sc.nextLine();
                            System.out.println("What is the price of the garnish?");
                            String garnishPrice = sc.nextLine();
                            System.out.println("Inserting new garnish...");
                            // garnishRep.insertGarnish(new Garnish(garnishName));
                            System.out.println("New garnish succesfully inserted!");
                            break;
                           
                        default:
                            System.out.println("Unknown input: \"" + line + "\".");
                            break;
                    }
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
            line = sc.nextLine();
        }
        
        connector.close();
    }
}
