import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @program: library management system
 * @description: This is for the MongoDB database connection
 * @author: Xinyong
 * @create: 2019-09-13 10:33
 **/
public class MongoHelper {
    //static Logger logger = LoggerFactory.getLogger(MongoHelper.class);
    static String ip = "10.80.18.1";
    static MongoClient mongoClient = new MongoClient(ip, 27017);
    static MongoDatabase database;

    public static void connect(String databaseName){
        database = mongoClient.getDatabase(databaseName);
    }

    public static MongoCollection<Document> getCollection(String collectionName){
        if(!collectionExists(collectionName)){
            return null;
        }
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection;
    }
    public static boolean collectionExists(final String collectionName) {
        boolean collectionExists = database.listCollectionNames()
                .into(new ArrayList<String>()).contains(collectionName);
        return collectionExists;
    }
    public static void getAllDocuments(MongoCollection<Document> collection){
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
    public static String getDocumentFirst(MongoCollection<Document> collection){
        Document myDoc = collection.find().first();
        return myDoc.toJson();
    }

    public static void createDocument(MongoCollection<Document> collection, Map<String,Object> map){
        System.out.println("--------------------");
        map.forEach((k,v)->{ System.out.println("Key : " + k + " Value : " + v);});
    }
    public static void insertDocument(MongoCollection<Document> collection,Document doc){
        collection.insertOne(doc);
    }
    public static void insertManyDocument(MongoCollection<Document> collection, List<Document> documents){
        collection.insertMany(documents);
    }
    public static void explicitlyCreateCollection(String collectionName){
        database.createCollection(collectionName,
                new CreateCollectionOptions().capped(false));
    }
    public static long deleteAllDocument(MongoCollection<Document> collection) {
        DeleteResult deleteResult = collection.deleteMany(new Document());
        long count = deleteResult.getDeletedCount();
        return count;
    }
    public static void closeDb() {
        mongoClient.close();
    }
    public static void deleteCollection(MongoCollection<Document> collection) {
        collection.drop();
    }
    public static void collectionsDrop(MongoDatabase db, String collectionName){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.drop();
    }

    public static void retrieveCollections(MongoDatabase db){
        MongoIterable<String> values = db.listCollectionNames();
        for (String s : values) {
            System.out.println(s);
        }
    }
    public static void retrieveDocuments(MongoDatabase db, String collectionName){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        FindIterable<Document> iterDoc = collection.find(); int i = 1;
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }
    public static FindIterable<Document> search(MongoDatabase db, String collectionName, String fieldName, String value){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        BasicDBObject parm = new BasicDBObject(fieldName,value);
        FindIterable<Document> iterDoc = collection.find(parm);
        int i = 1;
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
        return iterDoc;
    }
    public static void deleteDocuments(MongoDatabase db, String collectionName, String title){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.deleteOne(Filters.eq("title", title));
    }
    public static void collectionInsertBook(MongoDatabase db, String collectionName, String book_id, String book_name,String book_author, String book_borrower, boolean book_status){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document document = new Document("book_id", book_id).append("book_name", book_name).append("book_author", book_author).append("book_borrower", book_borrower).append("book_status", book_status);
        collection.insertOne(document);
    }
    public static void collectionInsertBorrower(MongoDatabase db, String collectionName, String borrower_id, String borrower_name,String borrower_phone){
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document document = new Document("borrower_id", borrower_id).append("borrower_name", borrower_name).append("borrower_phone", borrower_phone);
        collection.insertOne(document);
    }

}
