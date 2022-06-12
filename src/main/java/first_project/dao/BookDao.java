package first_project.dao;

import first_project.models.Book;
import first_project.models.Person;

import java.util.List;

public interface BookDao {
    public Book getBookById (int id);
    public List<Book> getAllBooks ();
    public void addBook (Book book);
    public void updateBook (int id, Book updatedBook);
    public void deleteBook (int id);
    public Person getOwner(int bookId);
    public void releaseBookFromTheOwner(int id);
    public void setOwnerForBook(int bookId, Person person);

}
