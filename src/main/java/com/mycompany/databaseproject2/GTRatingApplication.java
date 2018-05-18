package com.mycompany.databaseproject2;

import com.mycompany.databaseproject2.repositories.*;
import com.datastax.driver.core.Session;
import java.util.Scanner;

/**
 *
 * @author Kaspe
 */
public class GTRatingApplication {
    
    static void main(String[] args) {
        GTRatingApplication.runApplication();
    }
    
    static void runApplication() {
        CassandraConnector connector = new CassandraConnector();
        connector.connect("127.0.0.1", null);
        Session session = connector.getSession();
        
        // create the "drinks" keyspace
        KeyspaceRepository StdRep = new KeyspaceRepository(session);
        // create the various domain repositories
        GinRepository ginRep = new GinRepository(session);
        TonicRepository tonicRep = new TonicRepository(session);
        // TODO: still need to add the remaining repositories
        
        // await input
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Lul boi, what next ayyy??");
        
        String line = sc.nextLine();
        
        // branch out, depending on the input given
        while (line != "quit" || line != "stop" || line != "exit") {
            switch (line) {
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
