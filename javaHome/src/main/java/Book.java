/**
 * @program: library management system
 * @description: This is the Book class
 * @author: Xinyong
 * @create: 2019-09-14 00:59
 **/
public class Book {
    public String book_id = null; //This is the unique alphanumeric identifier for each book
    public String book_name = null;
    public String book_author = null;
    public boolean status = true; //This boolean type variable is used to measure whether the book has lent

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
