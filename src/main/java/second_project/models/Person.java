package second_project.models;


import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table (name = "person", schema = "project_2" )
public class Person {

    //   LocalDate currentDate = LocalDate.now();
// int maximumBirthYearAllowed = currentDate.getYear() - 14;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Person full name must not be empty")
    @Size(min = 3, message = " Your full name should have more than 2 letters")
    @Column(name = "full_name")
    private String fullName;

    //@NotEmpty(message = " This field must not be empty")
    @Min(value = 1920, message = "Year of birth should not be less than 1920")
    @Column(name = "year_of_birthday")
    private int yearOfBirthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }


    public Person() {
    }

    public Person(int id, String fullName, int yearOfBirthday) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirthday = yearOfBirthday;
    }


    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        book.setOwner(this);
    }
    public void deleteBook (Book book) {
        books.remove(book);
        book.setOwner(null);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }


    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", yearOfBirthday=" + yearOfBirthday +
                '}';
    }
}
