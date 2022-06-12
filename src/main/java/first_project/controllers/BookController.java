package first_project.controllers;

import first_project.dao.BookDao;
import first_project.dao.PersonDao;
import first_project.models.Book;
import first_project.models.Person;
import first_project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BookController {
    private final BookService bookService;

   

    @Autowired
    private PersonDao personDao;


    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/books";
    }

    @GetMapping("/books/new")
    public String newBook(@ModelAttribute("newBook") Book book) {
        return "book/add-new-book";
    }

    @PostMapping("/create-new-book")
    public String createNewBook(@ModelAttribute("newBook") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/add-new-book";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String showBookInfo(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        //
        Optional<Person> bookOwner = Optional.ofNullable(bookService.getOwner(id));
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDao.getAllPeople());
        }
        return "book/book-info";
    }

    @GetMapping("/books/{id}/edit")
    public String updateBookInfo( Model model, @PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/update-book";
    }

    @PatchMapping("/books/{id}")
    public String executeUpdatingPersonInfo(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
           // book.setId(id); //todo
            return "book/update-book";}
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/set-book-free/{id}")
    public String makeBookFree(@PathVariable int id) {
        Person person = bookService.getOwner(id);
       bookService.releaseBookFromTheOwner(person.getId());
       return "redirect:/books/"+id;
    }
    @PatchMapping ("/set-book-owner/{id}")
    public String setBookOwner(@PathVariable String id, @ModelAttribute("person") Person selectedPerson) {
        System.out.println(selectedPerson.getId());
        bookService.setOwnerForBook(Integer.parseInt(id), selectedPerson);
        return "redirect:/books/"+id;

    }


}
