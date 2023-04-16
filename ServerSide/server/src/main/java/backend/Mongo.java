package backend;

import backend.models.Book;
import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.MongoWriteException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mongo {
    public static MongoClient mongoClient;
    public static MongoDatabase db;
    public static MongoCollection<Document> catalog;
    public static MongoCollection<Document> users;
    //public static MongoCollection<Document> loans;
    public static void setupDB() {

        String uri = "mongodb+srv://andrewchang:57UaE70mqNJJCwhS@cluster0.8zzggys.mongodb.net/?retryWrites=true&w=majority";

        mongoClient = MongoClients.create(uri);
        db = mongoClient.getDatabase("library");
        catalog = db.getCollection("catalog");
        users = db.getCollection("users");

    }

}
