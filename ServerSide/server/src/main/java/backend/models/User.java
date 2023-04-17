package backend.models;

import java.util.HashSet;

public class User {
    private String username;
    private String password;
    public HashSet<Book> loans;
    public HashSet<Book> holds;
    public HashSet<Book> wishlist;

    public User() {
        this.username = "";
        this.password = "";
        this.loans = new HashSet<>();
        this.holds = new HashSet<>();
        this.wishlist = new HashSet<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loans = new HashSet<>();
        this.holds = new HashSet<>();
        this.wishlist = new HashSet<>();
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }

}
