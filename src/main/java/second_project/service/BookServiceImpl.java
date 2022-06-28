package second_project.service;

import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import second_project.models.Book;
import second_project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import second_project.repositories.BookRepo;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final EntityManager em;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo, EntityManager em) {
        this.bookRepo = bookRepo;
        this.em = em;
    }


    @Override
    public Book getBookById(int id) {
        Optional<Book> foundBook = bookRepo.findById(id);
        return foundBook.orElse(null);
    }

    @Override
    public List<Book> getAllBooks(boolean sortByYear) {
        if (sortByYear) {
            return bookRepo.findAll(Sort.by("yearOfPublication"));
        } else {
            return bookRepo.findAll();
        }
    }

    @Override
    public Iterable<Book> getAllBooks(Integer pageNumber, Integer itemsOnPage, boolean sortByYear) {

        if (sortByYear) {
            return bookRepo.findAll(PageRequest.of(pageNumber, itemsOnPage, Sort.by("yearOfPublication")));

        } else {
            return bookRepo.findAll(PageRequest.of(pageNumber, itemsOnPage));
        }

    }

//    @Override
//    public Iterable<Book> findPage(int pageNumber, int itemsOnPage) {
//       Pageable pageable =  PageRequest.of(pageNumber-1, itemsOnPage);
//
//        return bookRepo.findAll(pageable);
//    }

    @Override
    @Transactional
    public void addBook(Book book) {
        bookRepo.save(book);
    }

    @Override
    @Transactional
    public void updateBook(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepo.save(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        bookRepo.deleteById(id);
    }

    @Override
    public Person getOwner(int bookId) {
        Session session = em.unwrap(Session.class);
        Book book = bookRepo.findById(bookId).orElse(null);
        return book.getOwner();


//        return    (Person) session.createQuery("select Person from Book join Person on Book.owner.id=Person.id where Book.id=:id")
//                .setParameter("id", bookId).getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void releaseBookFromTheOwner(int bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            book.get().setOwner(null);
            book.get().setTaken_at(null);
        }
        //book.getOwner().deleteBook(book);
    }

    @Override
    @Transactional
    public void setOwnerForBook(int bookId, Person person) {
        Book book = getBookById(bookId);
        book.setOwner(person);
        book.setTaken_at(new Date());

    }

    @Override
    public List<Book> searchBookByTitle(String typedString) {
       return bookRepo.findBookByTitleContainingIgnoreCase(typedString);
    }

    @Override
    public Page<Book> findPage(int pageNumber) {
        Pageable pageable=  PageRequest.of(pageNumber-1, 4 );
        return bookRepo.findAll(pageable);
    }


}
