package first_project.service;

import first_project.dao.BookDao;
import first_project.models.Book;
import first_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    @Override
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(int id, Book updatedBook) {
        bookDao.updateBook(id, updatedBook);
    }

    @Override
    public void deleteBook(int id) {
        bookDao.deleteBook(id);
    }

    @Override
    public Person getOwner(int bookId) {
        return bookDao.getOwner(bookId);
    }

    @Override
    public void releaseBookFromTheOwner(int id) {
        bookDao.releaseBookFromTheOwner(id);

    }

    @Override
    public void setOwnerForBook(int bookId, Person person) {
        bookDao.setOwnerForBook(bookId, person);

    }


}
