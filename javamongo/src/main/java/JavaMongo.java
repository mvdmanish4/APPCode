import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.io.IOException;

public class JavaMongo {
    static Set<Integer> bookset = new HashSet<>(Arrays.asList(0,1,2,3,4,5,7,8,9));
    static Map<Integer, HashSet<Integer>> usermap = Map.of(0, new HashSet<>(),
            1, new HashSet<>(),
            2, new HashSet<>(),
            3, new HashSet<>(),
            4, new HashSet<>());

    public static void main(String[] args) {
        mylms();

    }
    private static void mylms() {
        System.out.println("Welcome to LMS!  -Linou Z.");
        System.out.println("Please enter your USERID or exit now");
        Scanner scan = new Scanner(System.in);
        String userID = scan.next();

        if (userID.equals("exit")) {
            scan.close();
        }
        int uID = 0;
        try {
            uID = Integer.parseInt(userID);
        } catch (NumberFormatException e){
            System.out.println("invalid user ID");
            scan.close();
        }

        if (uID > 4 || uID < 0) {
            System.out.println(uID + " is not a valid user");
        }

        System.out.println("Please Choose Your Service Number: 1. CHECKOUT / 2. RETURN or exit now");
        String serviceID = scan.next();
        if (serviceID.equals("exit")) {
            scan.close();
        }

        int sID = Integer.valueOf(serviceID);
        if (sID != 1 && sID != 2) {
            System.out.println("invalid service request");
            scan.close();
        }

        // checkout service
        if (sID == 1) {
            System.out.println("Please enter the checkout book number or exit now");
            String bookID = scan.next();
            if (bookID.equals("exit")) {
                scan.close();
            }
            int bID = Integer.valueOf(bookID);
            if (!bookset.contains(bID)) {
                if (bID > 9 || bID < 0) {
                    System.out.println("there is no such book " + bID);
                } else {
                    System.out.println(bID + " already checked out");
                }

            } else {
                bookset.remove(bID);
                usermap.get(uID).add(bID);
                System.out.println("checkout successful");
                mylms();
            }

            // return service
            // return other ppl's book is not allowed in LMS ?
        } else if (sID == 2) {
            System.out.println("Please enter the return book number or exit now");
            String bookID = scan.next();
            if (bookID.equals("exit")) {
                scan.close();
            }
            int bID = Integer.valueOf(bookID);
            if (bID > 9 || bID < 0) {
                System.out.println("there is no such book " + bID);
            }
            if (bookset.contains(bID)) {
                System.out.println(bID + " not currently checked out");
                scan.close();
            }
            if (!usermap.get(uID).contains(bID)) {
                System.out.println("You don't have access to return this book");
                scan.close();

            } else {
                usermap.get(uID).remove(bID);
                bookset.add(bID);
                System.out.println("Return Successful");

                // After making appropriate(?) changes, lms returns to the main prompt and waits for the next command
                mylms();

            }

        }

    }

}
