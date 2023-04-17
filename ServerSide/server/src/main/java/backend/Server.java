package backend;

import backend.models.Book;
import com.google.gson.Gson;
import org.bson.Document;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;

class Server extends Observable {
  public static Server server;
  public static Mongo mongodb = new Mongo();

  public static void main(String[] args) {
    Mongo.setupDB();
    server = new Server();
    server.resetCatalog();
    server.runServer();

  }

  private void runServer() {
    try {
      setUpNetworking();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }

  private void setUpNetworking() throws Exception {
    @SuppressWarnings("resource")
    ServerSocket serverSock = new ServerSocket(4242);
    while (true) {
      Socket clientSocket = serverSock.accept();
      System.out.println("Connecting to... " + clientSocket);

      ClientHandler handler = new ClientHandler(this, clientSocket);
      this.addObserver(handler);

      Thread t = new Thread(handler);
      t.start();
    }
  }

  protected void processRequest(String input) {
    String output = "Error";
    Gson gson = new Gson();
    Message message = gson.fromJson(input, Message.class);
    try {
      String temp = "";
      switch (message.type) {
        case "login":
          //temp = message.input.toUpperCase();
          break;
        case "signup":
          //temp = message.input.toLowerCase();
          break;
        case "checkoutBook":
          //temp = message.input.replace(" ", "");
          break;
        case "returnBook":
          break;
        case "holdBook":
          break;
      }
      output = "";
//      for (int i = 0; i < message.number; i++) {
//        output += temp;
//        output += " ";
//      }
      this.setChanged();
      this.notifyObservers(output);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void resetCatalog() {
    System.out.println("Resetting catalog...");
    Mongo.catalog.deleteMany(new Document());

    try {
      String bookJson = readFileAsString("src/main/resources/books.json");
      Gson gson = new Gson();

      Book[] catalog = gson.fromJson(bookJson, Book[].class);

      //inserts all books to catalog collection and prints them out
      for (Book book : catalog) {
        Mongo.catalog.insertOne(book);
        addToInventory(book);
        System.out.println(mongodb.findBook(book.getId()));
      }
      System.out.println("Catalog Reset");
      System.out.println(mongodb.inventory);

    } catch(Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public static String readFileAsString(String file)throws Exception
  {
    return new String(Files.readAllBytes(Paths.get(file)));
  }

  public void addToInventory(Book bookCopy) {
    //add another copy to inventory
    mongodb.inventory.put(bookCopy.getTitle(), mongodb.inventory.getOrDefault(bookCopy.getTitle(), 0) + 1);
  }
  public void removeFromInventory(Book bookCopy) {
    //remove a copy from inventory
    if(mongodb.inventory.get(bookCopy.getTitle()) == 0) return; //no negative # of copies
    else mongodb.inventory.put(bookCopy.getTitle(), mongodb.inventory.get(bookCopy.getTitle()) - 1);
  }
}