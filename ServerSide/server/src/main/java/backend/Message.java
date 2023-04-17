package backend;
/*
 * Message to communicate with the client
 * Type: login, signup, checkoutBook, returnBook, holdBook, wishlist
 *
 */
class Message {
  String type;
  String clientName;
  String bookName;

  protected Message() {
    this.type = "";
    this.clientName = "";
    this.bookName = "";
    System.out.println("server-side message created");
  }

  protected Message(String type, String userName, String bookName) {
    this.type = type;
    this.clientName = userName;
    this.bookName = bookName;
    System.out.println("server-side message created");
  }
}