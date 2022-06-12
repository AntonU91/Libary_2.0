package first_project.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
public class Book {
    private  int id;

    // создать валидатор уникальности названия книги

    @NotBlank (message = "The title must have at least one non-space character")
    private String title;

    @Size(min= 3 , message = "The full name of author must have 3 or more letters")
//    @Pattern (regexp = "[A-ZА-Я]\\w+", message = "The field author should have at least 1 character and begins with capital letter")
   private String author;

    @Min( value = 1, message = "The year of publication must be positive positive digit and has value greater than '0'")
    private int yearOfPublication;

    private int personId;

    public Book() {
    }

    public Book( int id,String title, String author, int yearOfPublication , int personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.personId = personId;
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

    public int getPersonId() {
        return personId;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
