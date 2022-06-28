package second_project.controllers;

import org.springframework.data.domain.Page;
import second_project.models.Book;
import second_project.models.Person;
import second_project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import second_project.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private final BookService bookService;
    private final PersonService personService;



    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

//    @GetMapping("/books")
//    public String showAllBooks(Model model) {
//
////        if ((pageNumber!=null && Integer.parseInt(pageNumber)>=0)
////                ||( booksNumberOnPage!=null && Integer.parseInt(booksNumberOnPage)>0)) {
////        model.addAttribute("books", bookService.findThePage(Integer.parseInt(pageNumber), Integer.parseInt(booksNumberOnPage)));}
////        else {
//        model.addAttribute("books", bookService.getAllBooks());
//    //}
//        return "book/books";
//    }
//    @GetMapping("/books")
//    public String showAllBooks(Model model,@RequestParam(name = "page", required = false) String currentPage,
//                          @RequestParam (name = "books_per_page",required = false) String booksNumberOnPage, @RequestParam (name="sort_by_year", required = false) boolean sortByYear) {
//
//        if ((currentPage!=null && Integer.parseInt(currentPage)>=0)
//                ||( booksNumberOnPage!=null && Integer.parseInt(booksNumberOnPage)>0)) {
//        model.addAttribute("books", bookService.getAllBooks(Integer.parseInt(currentPage), Integer.parseInt(booksNumberOnPage), sortByYear));}
//        else {
//        model.addAttribute("books", bookService.getAllBooks(sortByYear));
//        }
//        return "book/books";
//    }


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
            model.addAttribute("people", personService.getAllPeople());
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
      // Book book = bookService.getBookById(id);
       bookService.releaseBookFromTheOwner(id);
       return "redirect:/books/"+id;
    }
    @PatchMapping ("/set-book-owner/{id}")
    public String setBookOwner(@PathVariable int id, @ModelAttribute("person") Person selectedPerson) {
        System.out.println(selectedPerson.getId());
        bookService.setOwnerForBook(id, selectedPerson);
        return "redirect:/books/"+id;

    }
//    @GetMapping ("/books/search")
//    public String searchBook (Model model) {
//       return "book/search-book";
//    }

    @GetMapping ("/books/execute-searching")
    public String showResultOfSearchingBook (@RequestParam(value = "typedString") String typedString, Model model) {
         List<Book> books=bookService.searchBookByTitle(typedString);
        model.addAttribute("books", books);
        return "book/search-book";
    }

    @GetMapping("/books")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/books/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Book> page = bookService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Book> books = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("books", books);

        return "/book/books-pagination";
    }

}
