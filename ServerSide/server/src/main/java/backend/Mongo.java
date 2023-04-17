package backend;

import backend.models.Book;
import backend.models.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Mongo {
    public static MongoClient mongoClient;
    public static MongoDatabase db;
    public static MongoCollection<Book> catalog;
    public static MongoCollection<User> users;
    //public static MongoCollection<Document> loans;
    public static Map<String, Integer> inventory = new HashMap<String, Integer>();
    public static void setupDB() {

//        String uri = "mongodb+srv://andrewchang:57UaE70mqNJJCwhS@cluster0.8zzggys.mongodb.net/?retryWrites=true&w=majority";
//
//        mongoClient = MongoClients.create(uri);
//        db = mongoClient.getDatabase("library");
//        catalog = db.getCollection("catalog");
//        users = db.getCollection("users");

        //Mapping MongoDB Documents to POJOs
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        //MongoDB Setup
        mongoClient = MongoClients.create("mongodb+srv://andrewchang:57UaE70mqNJJCwhS@cluster0.8zzggys.mongodb.net/?retryWrites=true&w=majority");
        db = mongoClient.getDatabase("library").withCodecRegistry(pojoCodecRegistry);;
        catalog = db.getCollection("catalog", Book.class);
        users = db.getCollection("users", User.class);

        System.out.println("Setting up MongoDB...");

    }

    public Book findBook(ObjectId id) {
        Book b = catalog.find(Filters.eq("_id", id)).first();
        return b;
    }
    public User checkUser(String username, String password){
        System.out.println("Checking user");
        User user;
        try {
            user = users.find(Filters.eq("username", username)).first();
        }catch(Exception e){
            return null; //Check to see if there is an active user
        }
        if(user.getPassword().equals(password)){
            return user;
        }
        return null; //Found it not caught in the try catch block
    }

}
