package backend;

import backend.models.Book;
import com.google.gson.Gson;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Observable;

class Server extends Observable {
  public static Server server;

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
        case "upper":
          temp = message.input.toUpperCase();
          break;
        case "lower":
          temp = message.input.toLowerCase();
          break;
        case "strip":
          temp = message.input.replace(" ", "");
          break;
      }
      output = "";
      for (int i = 0; i < message.number; i++) {
        output += temp;
        output += " ";
      }
      this.setChanged();
      this.notifyObservers(output);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void resetCatalog() {
    Mongo.catalog.deleteMany(new Document());

    try {
      String bookJson = readFileAsString("src/main/resources/books.json");
      Gson gson = new Gson();

      Book[] catalog = gson.fromJson(bookJson, Book[].class);

      //inserts all books to catalog collection
      for (Book book : catalog) {
        Mongo.catalog.insertOne(book.toDocument());
      }

      System.out.println("Catalog Reset");

    } catch(Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public static String readFileAsString(String file)throws Exception
  {
    return new String(Files.readAllBytes(Paths.get(file)));
  }
}