package backend.models;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Book {
    private ObjectId id;
    private String title;
    private String author;
    private String pages;
    private String year;
    private String summary;
    public boolean checkedOut;

    public Book() {}
    public Book(String title, String author, String pages, String year, String summary, boolean checkedOut) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
        this.summary = summary;
        this.checkedOut = checkedOut;
    }

    public Document toDocument() {
        return new Document().append("title", this.title).append("author", this.author).append("pages", this.pages).append("year", this.year).append("summary", this.summary);
    }

    //setters
    public void setId(ObjectId id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPages(String pages) { this.pages = pages; }
    public void setYear(String year) { this.year = year; }
    public void setSummary(String summary) { this.summary = summary; }

    //getters
    public ObjectId getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPages() { return pages; }
    public String getYear() { return year; }
    public String getSummary() { return summary; }

}

