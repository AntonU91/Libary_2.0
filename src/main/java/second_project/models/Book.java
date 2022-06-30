package second_project.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Component
@Entity
@Table(name = "book", schema = "project_2")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "title")
    @NotBlank(message = "The title must have at least one non-space character")
    private String title;

    @Column(name = "author")
    @Size(min = 3, message = "The full name of author must have 3 or more letters")
//    @Pattern (regexp = "[A-ZА-Я]\\w+", message = "The field author should have at least 1 character and begins with capital letter")
    private String author;

    @Column(name = "year_of_publication")
    @Min(value = 1, message = "The year of publication must be positive positive digit and has value greater than '0'")
    private int yearOfPublication;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taken_at;

    //    @Transient
//    boolean overdueBookRent;
    public Book() {
    }

    public boolean isBookRentIsOverdue() {
        return new Date().getTime() >= (taken_at.getTime() + (10 * 24 * 60 * 60 * 1000)); // return true notice: allows 10 days for renting book
    }


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;


    public Book(int id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Date getTaken_at() {
        return taken_at;
    }

    public void setTaken_at(Date taken_at) {
        this.taken_at = taken_at;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
        // owner.getBooks().add(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
