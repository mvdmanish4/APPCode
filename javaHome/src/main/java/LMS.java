import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
/**
 * @program: library management system
 * @description: This is the LMS class. This class contains the constructor, three methods, and the main function.
 * @author: Xinyong
 * @create: 2019-09-01 01:03
 **/
public class LMS {

    static String databaseName = "app-xinyongl1";
    static String collectionName1 = "BOOK";
    static String collectionName2 = "BORROWER";
    static MongoCollection<Document> BOOK;
    static MongoCollection<Document> BORROWER;

    public void setUp() {
        MongoHelper.connect(databaseName);
        BOOK = MongoHelper.getCollection(collectionName1);
        BORROWER = MongoHelper.getCollection(collectionName2);
    }

    public void getAllDocuments() {
        MongoHelper.getAllDocuments(BOOK);
        MongoHelper.getAllDocuments(BORROWER);
    }

    /**
     * @Description: This is the check_out method, and based on the status of book availability, there are three results
     * @Param: [mybook, myborrower]
     * @return: void
     * @Author: Xinyong
     * @Date: 2019-09-01
     **/
    public void check_out(String mybook, String myborrower) {
        Document myDoc1 = BOOK.find(Filters.eq("book id", mybook)).first();
        Document myDoc2 = BOOK.find(new BasicDBObject("bookid", mybook).append("book status", true)).first();
        Document myDoc3 = BORROWER.find(Filters.eq("borrower id", myborrower)).first();

        if (myDoc3.toJson() != null) {
            if (myDoc1.toJson() != null) {
                if (myDoc2.toJson() != null) {
                    UpdateResult updateResult = BOOK.updateOne(Filters.eq("book id", mybook), Updates.set("book status", false));
                    System.out.println(myborrower + " has checked out " + mybook);
                } else System.out.println(mybook + " is already checked out by someone");
            } else System.out.println("Book with Id " + mybook + " does not exist");
        } else System.out.println("Borrower with Id " + myborrower + " does not exist");

    }

    /**
     * @Description: This is the return_book method, and based on the status of book availability, there are four results
     * @Param: [mybook, myborrower]
     * @return: void
     * @Author: Xinyong
     * @Date: 2019-09-14
     **/
    public void return_book(String mybook, String myborrower) {
        Document myDoc1 = BOOK.find(Filters.eq("book id", mybook)).first();
        Document myDoc2 = BORROWER.find(Filters.eq("borrower id", myborrower)).first();
        Document myDoc3 = BOOK.find(new BasicDBObject("bookid", mybook).append("book status", false)).first();
        if (myDoc2.toJson() != null) {
            if (myDoc1.toJson() != null) {
                if (myDoc3.toJson() != null) {
                    UpdateResult updateResult = BOOK.updateOne(Filters.eq("book id", mybook), Updates.set("book status", true));
                    System.out.println(myborrower + " has returned " + mybook);
                } else System.out.println(myborrower + " has not currently checked out " + mybook);
            } else System.out.println("Book with Id " + mybook + " does not exist");
        } else System.out.println("Borrower with Id " + myborrower + " does not exist");
    }

    /**
     * @Description: This is the exit_LMS method, it is used to exit the LMS system
     * @Param: [mybook, myborrower]
     * @return: void
     * @Author: Xinyong
     * @Date: 2019-09-02
     **/
    public void exit_LMS(String mybook, String myborrower) {
        System.out.println("Exiting");
    }

    public void reset(MongoDatabase db) {
        UpdateResult updateResult = BOOK.updateOne(Filters.eq("book status", false), new Document("$set", new Document("book status", true)));
        System.out.println("Data has been reset");
    }

    public void books(MongoDatabase db) {
        MongoIterable<String> values = db.listCollectionNames();
        for (String s : values) {
            System.out.println(s);
        }
    }

        /**
         * @Description:
         * @Param: [args]
         * @return: void
         * @Author: Xinyong
         * @Date: 2019-09-14
         **/
        public static void main(String[] args)
        {
            LMS lms = new LMS();
            MongoClient mongoCli = new MongoClient();
            MongoDatabase db = mongoCli.getDatabase(databaseName);
            //lms.setUp();
            MongoHelper.collectionInsertBook(db, "BOOK", "A234", "The 101 Dalmations", "Dodie Smith", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "A675", "The Adventures of Huckleberry Finn", "Mark Twain", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "A212", "Bag of Bones", "Stephen King", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "B671", "Charlie and the Chocolate Factory", "Roald Dahl", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "B534", "Charlotte's Web", "E.B.White", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "B777", "A Christmas Carol", "Charles Dickens", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "B778", "Dracula", "Bram Stoker", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "B812", "A Farewell to Arms", "Ernest Hemingway", null, true);
            MongoHelper.collectionInsertBook(db, "BOOK", "C101", "The Firm", "John Grisham", null, true);
            MongoHelper.collectionInsertBorrower(db, "BORROWER", "L34", "Andrea Selleck", "639-555-1239");
            MongoHelper.collectionInsertBorrower(db, "BORROWER", "L22", "Lucas Hyatt", "408-555-2365");
            MongoHelper.collectionInsertBorrower(db, "BORROWER", "L19", "Carol Leonard", "650-555-8921");
            MongoHelper.collectionInsertBorrower(db, "BORROWER", "L84", "Ayesha Ford", "415-555-2120");
            MongoHelper.collectionInsertBorrower(db, "BORROWER", "L77", "Kenneth Trout", "510-555-1982");

            String command1 = "checkout";
            String command2 = "return";
            String command3 = "exit";

            String command = null;

            boolean flag = false;

            Scanner sc = null;

            while (flag == false)
            //keep running the project until the user type "exit"
            {
                try {
                    sc = new Scanner(System.in);
                    System.out.println("Enter Borrower Id:");
                    String mybook_id = sc.nextLine();
                    System.out.println("Enter Book Id:");
                    String name_id = sc.nextLine();
                    System.out.println("Enter a command:");
                    command = sc.nextLine();
                    if (command.equals(command1))
                        lms.check_out(mybook_id, name_id);
                    else if (command.equals(command2))
                        lms.return_book(mybook_id, name_id);
                    else if (command.equals(command3)) {
                        lms.exit_LMS(mybook_id, name_id);
                        flag = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        }
